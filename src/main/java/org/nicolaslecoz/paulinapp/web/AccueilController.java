package org.nicolaslecoz.paulinapp.web;

import org.nicolaslecoz.paulinapp.Application;
import org.nicolaslecoz.paulinapp.domain.EtatApplication;
import org.nicolaslecoz.paulinapp.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccueilController {

    @Autowired
    ApplicationRepository repository;

	@RequestMapping("/")
	public String accueil(Model model) throws Exception {
        Iterable<EtatApplication> itEtatApplication = repository.findAll();
        List<EtatApplication> listeEtatApplication = new ArrayList<>();

        for (EtatApplication ea : itEtatApplication) {
            listeEtatApplication.add(ea);
        }
        if (listeEtatApplication.size() == 1) {
            model.addAttribute("etatApplication", listeEtatApplication.get(0));
        } else if (listeEtatApplication.size() > 1) {
            throw new IllegalStateException();
        }
        return "accueil";
	}

    @RequestMapping("/effacer-db")
    public String effacerBadeDeDonnee(Model model) throws Exception {
        repository.deleteAll();
        return "redirect:/";
    }

}
