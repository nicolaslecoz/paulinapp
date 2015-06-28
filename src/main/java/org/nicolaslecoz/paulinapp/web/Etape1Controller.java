package org.nicolaslecoz.paulinapp.web;

import static org.nicolaslecoz.paulinapp.configuration.Conf.DOC_RESOLUTION;
import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;
import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.calculerAngleRotation;
import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.rotateImage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;
import org.nicolaslecoz.paulinapp.domain.Coordonnee;
import org.nicolaslecoz.paulinapp.domain.EtatApplication;
import org.nicolaslecoz.paulinapp.domain.Page;
import org.nicolaslecoz.paulinapp.repository.ApplicationRepository;
import org.nicolaslecoz.paulinapp.tool.detectcorner.DetectCornerTool;
import org.nicolaslecoz.paulinapp.tool.detectline.DetectHorizontalLineTool;
import org.nicolaslecoz.paulinapp.tool.detectline.DetectVerticalLineTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/etape1")
public class Etape1Controller {

    @Autowired
    ApplicationRepository repository;

	public List<Path> findPdfFile(String nomRepertoire) {
		List<Path> result = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(nomRepertoire), "*.pdf")) {
			for (Path entry : stream) {
				result.add(entry);
			}
		} catch (DirectoryIteratorException | IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@RequestMapping(value = "/enregister-repertoire-travail", method = RequestMethod.POST)
	public String enregisterPdf(@RequestParam String nomRepertoire) throws Exception {
        EtatApplication ea = new EtatApplication();

        ea.repertoireTravail = nomRepertoire;
        ea = repository.save(ea);
        Session.idEtatApplication = ea.id;

		run(nomRepertoire);
		return "redirect:/etape2/lister-fiche";
	}

	private void workOnOnePage(String nomRepertoire, Path pdfFile, int indexPage, PDPage pdPage, Page page) throws IOException {
		BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_INT_RGB, DOC_RESOLUTION);

		String filename = pdfFile.getFileName().toString() + "-" + indexPage + ".png";

		page.nomImage = filename;

		Coordonnee leftCorner = DetectCornerTool.detectHeaderLeftCorner(bim);
		Coordonnee rightCorner = DetectCornerTool.detectHeaderRightCorner(bim);

		double angle = calculerAngleRotation(leftCorner, rightCorner);

		bim = rotateImage(bim, angle, leftCorner);
		ImageIOUtil.writeImage(bim, nomRepertoire + "/" + filename, DOC_RESOLUTION);

		List<Integer> listeLigneVerticale = new DetectVerticalLineTool().parse(bim, leftCorner);

		int indexFirstLine = 4;
		if (indexPage == 1) {
			indexFirstLine = 3;
		}
		List<Integer> listeLigneHorizontale = new DetectHorizontalLineTool((int) (4 * SCALE_CM)).parse(bim, listeLigneVerticale.get(indexFirstLine));
	}

    private int calculerNbPageAGenerer(List<Path> listeFichierPdf) throws IOException {
        int result = 0;

        for (Path pdfFile : listeFichierPdf) {
            PDDocument document = PDDocument.loadNonSeq(pdfFile.toFile(), null);
            List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();

            result += pdPages.size();
        }
        return result;
    }

    public void run(String nomRepertoire) {
        EtatApplication ea = repository.findOne(Session.idEtatApplication);

        ea.enCoursGenerationImage = true;
        try {
            List<Path> listeFichierPdf = findPdfFile(nomRepertoire);

            ea.nbPageAGenerer = calculerNbPageAGenerer(listeFichierPdf);

            for (Path pdfFile : listeFichierPdf) {
                try (PDDocument document = PDDocument.loadNonSeq(pdfFile.toFile(), null)) {
                    List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
                    int indexPage = 0;

                    for (PDPage pdPage : pdPages) {
                        Page page = new Page();


                        ea.listePage.add(page);
                        long startTime = new Date().getTime();
                        indexPage++;
                        try {
                            workOnOnePage(nomRepertoire, pdfFile, indexPage, pdPage, page);
                            page.generationOk = true;
                        } catch (Exception e) {
                            page.exceptionGeneration = e;
                            page.generationOk = false;
                        }
                        ea.nbPageGenerer++;
                        long endTime = new Date().getTime();

                        page.tempsDeGeneration = endTime - startTime;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ea.enCoursGenerationImage = false;
        }
    }
}
