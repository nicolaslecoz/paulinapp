package org.nicolaslecoz.paulinapp.web;


import org.nicolaslecoz.paulinapp.domain.EtatApplication;
import org.nicolaslecoz.paulinapp.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/etape2")
public class Etape2Controller {

    @Autowired
    ApplicationRepository repository;

    private List<String> findImageFile(String repertoireTravail) {
        List<String> result = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(repertoireTravail), "*.png")) {
            for (Path entry : stream) {
                result.add(entry.toFile().getName());
            }
        } catch (DirectoryIteratorException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @RequestMapping("/lister-fiche")
    public String listerFichierImage(Model model) {
        EtatApplication ea = repository.findOne(Session.idEtatApplication);

        model.addAttribute("listeImage", findImageFile(ea.repertoireTravail));
        model.addAttribute("listePage", ea.listePage);
        model.addAttribute("enCoursGenerationImage", ea.enCoursGenerationImage);
       
        int pourcentageAvancement = (int) (100 * ea.nbPageGenerer / ea.nbPageAGenerer);
        
        model.addAttribute("pourcentageAvancement", pourcentageAvancement);

        return "lister-fiche";
    }
}
