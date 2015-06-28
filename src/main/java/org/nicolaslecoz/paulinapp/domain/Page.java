package org.nicolaslecoz.paulinapp.domain;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Page {
    @Id  @GeneratedValue(strategy=GenerationType.TABLE)
    public long id;

    @Transient
    public BufferedImage imageAssocie;
    public int seuil;
    public String nomImage;
    @OneToMany
    public List<Ligne> listeLigne = new ArrayList<>();
    public long tempsDeGeneration;
    public boolean generationOk;
    public boolean validationOk;
    public Exception exceptionGeneration;

    public static Page getPagePrecedente(List<Page> listPage, String nomPageCourante) {
        for (int i = 0; i < listPage.size(); i++) {
            if (nomPageCourante.equals(listPage.get(i).nomImage)) {
                if (i > 0) {
                    return listPage.get(i - 1);
                }
            }
        }
        return null;
    }

    public static Page getPageSuivante(List<Page> listPage, String nomPageCourante) {
        for (int i = 0; i < listPage.size(); i++) {
            if (nomPageCourante.equals(listPage.get(i).nomImage)) {
                if ((i + 1) < listPage.size()) {
                    return listPage.get(i + 1);
                }
            }
        }
        return null;
    }

    public Ligne getLigneByCode(String code) {
        for (Ligne l : listeLigne) {
            if (l.code.equals(code)) {
                return l;
            }
        }
        return null;
    }

    public List<Ligne> getListeLigne() {
        return listeLigne;
    }
    public void setListeLigne(List<Ligne> listeLigne) {
        this.listeLigne = listeLigne;
    }
    public BufferedImage getImageAssocie() {
        return imageAssocie;
    }
    public void setImageAssocie(BufferedImage imageAssocie) {
        this.imageAssocie = imageAssocie;
    }
    public String getNomImage() {
        return nomImage;
    }
    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }
    public int getSeuil() {
        return seuil;
    }
    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }
}
