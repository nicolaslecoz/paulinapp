package org.nicolaslecoz.paulinapp.web;

import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.nicolaslecoz.paulinapp.domain.*;
import org.nicolaslecoz.paulinapp.repository.ApplicationRepository;
import org.nicolaslecoz.paulinapp.tool.detectcorner.DetectCornerTool;
import org.nicolaslecoz.paulinapp.tool.detectline.DetectHorizontalLineTool;
import org.nicolaslecoz.paulinapp.tool.detectline.DetectVerticalLineTool;
import org.nicolaslecoz.paulinapp.tool.image.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ApplicationRepository repository;

	private Page buildData(BufferedImage bim, int seuil, TypePage typePage) throws Exception {
		Coordonnee leftCorner = DetectCornerTool.detectHeaderLeftCorner(bim);

		List<Integer> listeLigneVerticale = new DetectVerticalLineTool().parse(bim, leftCorner);

		int indexFirstLine = 3;

		List<Integer> listeLigneHorizontale = new DetectHorizontalLineTool((int) (4 * SCALE_CM)).parse(bim, listeLigneVerticale.get(indexFirstLine));

        ImageTool imageTool = new ImageTool(seuil);

        if (typePage == TypePage.P1) {
            return buildPage1(bim, listeLigneVerticale, listeLigneHorizontale, imageTool);
        } else if (typePage == TypePage.P2) {
            return buildPage2(bim, listeLigneVerticale, listeLigneHorizontale, imageTool);
        } else if (typePage == TypePage.P3) {
            return buildPage3(bim, listeLigneVerticale, listeLigneHorizontale, imageTool);
        }
		return null;
	}

    private Page buildPage3(BufferedImage bim, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale, ImageTool imageTool) {
        Page page3 = new Page();

        page3.getLigneByCode("620").saisiLigne = imageTool.buildSaisieLigne(3, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("630").saisiLigne = imageTool.buildSaisieLigne(4, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("640").saisiLigne = imageTool.buildSaisieLigne(5, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("650").saisiLigne = imageTool.buildSaisieLigne(6, bim, listeLigneVerticale, listeLigneHorizontale);

        page3.getLigneByCode("660").saisiLigne = imageTool.buildSaisieLigne(8, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("670").saisiLigne = imageTool.buildSaisieLigne(9, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("680").saisiLigne = imageTool.buildSaisieLigne(10, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("690").saisiLigne = imageTool.buildSaisieLigne(11, bim, listeLigneVerticale, listeLigneHorizontale);
        page3.getLigneByCode("710").saisiLigne = imageTool.buildSaisieLigne(12, bim, listeLigneVerticale, listeLigneHorizontale);

        return page3;
    }

    private Page buildPage2(BufferedImage bim, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale, ImageTool imageTool) {
        Page page2 = new Page();

        page2.getLigneByCode("340").saisiLigne = imageTool.buildSaisieLigne(3, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("350").saisiLigne = imageTool.buildSaisieLigne(4, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("360").saisiLigne = imageTool.buildSaisieLigne(5, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("370").saisiLigne = imageTool.buildSaisieLigne(6, bim, listeLigneVerticale, listeLigneHorizontale);

        page2.getLigneByCode("390").saisiLigne = imageTool.buildSaisieLigne(8, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("400").saisiLigne = imageTool.buildSaisieLigne(9, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("410").saisiLigne = imageTool.buildSaisieLigne(10, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("420").saisiLigne = imageTool.buildSaisieLigne(11, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("430").saisiLigne = imageTool.buildSaisieLigne(12, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("440").saisiLigne = imageTool.buildSaisieLigne(13, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("450").saisiLigne = imageTool.buildSaisieLigne(14, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("460").saisiLigne = imageTool.buildSaisieLigne(15, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("470").saisiLigne = imageTool.buildSaisieLigne(16, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("480").saisiLigne = imageTool.buildSaisieLigne(17, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("490").saisiLigne = imageTool.buildSaisieLigne(18, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("500").saisiLigne = imageTool.buildSaisieLigne(19, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("510").saisiLigne = imageTool.buildSaisieLigne(20, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("520").saisiLigne = imageTool.buildSaisieLigne(21, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("530").saisiLigne = imageTool.buildSaisieLigne(22, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("540").saisiLigne = imageTool.buildSaisieLigne(23, bim, listeLigneVerticale, listeLigneHorizontale);

        page2.getLigneByCode("070").saisiLigne = imageTool.buildSaisieLigne(25, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("080").saisiLigne = imageTool.buildSaisieLigne(26, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("090").saisiLigne = imageTool.buildSaisieLigne(27, bim, listeLigneVerticale, listeLigneHorizontale);

        page2.getLigneByCode("550").saisiLigne = imageTool.buildSaisieLigne(28, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("560").saisiLigne = imageTool.buildSaisieLigne(29, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("570").saisiLigne = imageTool.buildSaisieLigne(30, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("580").saisiLigne = imageTool.buildSaisieLigne(31, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("590").saisiLigne = imageTool.buildSaisieLigne(32, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("600").saisiLigne = imageTool.buildSaisieLigne(33, bim, listeLigneVerticale, listeLigneHorizontale);
        page2.getLigneByCode("610").saisiLigne = imageTool.buildSaisieLigne(34, bim, listeLigneVerticale, listeLigneHorizontale);

        return page2;
    }

    private Page buildPage1(BufferedImage bim, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale, ImageTool imageTool) {
        Page page1 = new Page();

        page1.getLigneByCode("010").saisiLigne = imageTool.buildSaisieLigne(2, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("020").saisiLigne = imageTool.buildSaisieLigne(3, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("030").saisiLigne = imageTool.buildSaisieLigne(4, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("040").saisiLigne = imageTool.buildSaisieLigne(5, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("050").saisiLigne = imageTool.buildSaisieLigne(6, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("060").saisiLigne = imageTool.buildSaisieLigne(7, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("115").saisiLigne = imageTool.buildSaisieLigne(8, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("125").saisiLigne = imageTool.buildSaisieLigne(9, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("135").saisiLigne = imageTool.buildSaisieLigne(10, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("140").saisiLigne = imageTool.buildSaisieLigne(11, bim, listeLigneVerticale, listeLigneHorizontale);

        page1.getLigneByCode("150").saisiLigne = imageTool.buildSaisieLigne(13, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("160").saisiLigne = imageTool.buildSaisieLigne(14, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("180").saisiLigne = imageTool.buildSaisieLigne(15, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("200").saisiLigne = imageTool.buildSaisieLigne(16, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("210").saisiLigne = imageTool.buildSaisieLigne(17, bim, listeLigneVerticale, listeLigneHorizontale);

        page1.getLigneByCode("220").saisiLigne = imageTool.buildSaisieLigne(19, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("235").saisiLigne = imageTool.buildSaisieLigne(20, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("250").saisiLigne = imageTool.buildSaisieLigne(21, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("260").saisiLigne = imageTool.buildSaisieLigne(22, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("270").saisiLigne = imageTool.buildSaisieLigne(23, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("280").saisiLigne = imageTool.buildSaisieLigne(24, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("295").saisiLigne = imageTool.buildSaisieLigne(25, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("310").saisiLigne = imageTool.buildSaisieLigne(26, bim, listeLigneVerticale, listeLigneHorizontale);
        page1.getLigneByCode("320").saisiLigne = imageTool.buildSaisieLigne(27, bim, listeLigneVerticale, listeLigneHorizontale);

        return page1;
    }

    private TypePage getTypePage(String filename) {
        if (filename.endsWith("-1.png")) {
            return TypePage.P1;
        } else if (filename.endsWith("-2.png")) {
            return TypePage.P2;
        } else if (filename.endsWith("-3.png")) {
            return TypePage.P3;
        }
        return null;
    }

	@RequestMapping("/")
	public String accueil(String image, @RequestParam(defaultValue = "250", required = false) int seuil, Model model) throws Exception {
        EtatApplication ea = repository.findOne(Session.idEtatApplication);
        String filename = ea.repertoireTravail + "/" + image;

        BufferedImage bim = ImageIO.read(new File(filename));

		Page page = buildData(bim, seuil, getTypePage(filename));

        page.nomImage = image;
        page.seuil = seuil;
		model.addAttribute("nomImage", image);
        model.addAttribute("imageBase64", buildImageBase64(bim));

		model.addAttribute("page", page);

        model.addAttribute("listeMoisPossible", MoisPossible.construireListeMoisPossible());
        model.addAttribute("pagePrev", Page.getPagePrecedente(ea.listePage, image));
        model.addAttribute("pageSuiv", Page.getPageSuivante(ea.listePage, image));
		return "image";
	}

    @RequestMapping("/enregistrer")
    public String accueil(@ModelAttribute(value="page") Page page, Model model) throws Exception {
        EtatApplication ea = repository.findOne(Session.idEtatApplication);
        String filename = ea.repertoireTravail + "/" + page.nomImage;

        BufferedImage bim = ImageIO.read(new File(filename));

        model.addAttribute("nomImage", page.nomImage);
        model.addAttribute("imageBase64", buildImageBase64(bim));
        model.addAttribute("listeMoisPossible", MoisPossible.construireListeMoisPossible());
        model.addAttribute("pagePrev", Page.getPagePrecedente(ea.listePage, filename));
        model.addAttribute("pageSuiv", Page.getPageSuivante(ea.listePage, filename));
        return "image";
    }

    private String buildImageBase64(BufferedImage bim) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bim, "png", Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.UTF_8.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

}