package org.nicolaslecoz.paulinapp.domain;

import javax.persistence.*;
import java.time.Duration;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaisiLigne {
    @Id @GeneratedValue(strategy=GenerationType.TABLE)
    public long id;
    public boolean activePratique;
    @Transient /* FIXME TODO */
    public List<Month> listeMoisActivite = new ArrayList<>( );
    public double nombreDeFoisParMois;
    public Integer dureeHeure;
    public Integer dureeMinute;
    public String champLibre;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean isActivePratique() {
        return activePratique;
    }
    public void setActivePratique(boolean activePratique) {
        this.activePratique = activePratique;
    }
    public List<Month> getListeMoisActivite() {
        return listeMoisActivite;
    }
    public void setListeMoisActivite(List<Month> listeMoisActivite) {
        this.listeMoisActivite = listeMoisActivite;
    }
    public double getNombreDeFoisParMois() {
        return nombreDeFoisParMois;
    }
    public void setNombreDeFoisParMois(double nombreDeFoisParMois) {
        this.nombreDeFoisParMois = nombreDeFoisParMois;
    }
    public String getChampLibre() {
        return champLibre;
    }
    public void setChampLibre(String champLibre) {
        this.champLibre = champLibre;
    }

    public Integer getDureeHeure() {
        return dureeHeure;
    }

    public void setDureeHeure(Integer dureeHeure) {
        this.dureeHeure = dureeHeure;
    }

    public Integer getDureeMinute() {
        return dureeMinute;
    }

    public void setDureeMinute(Integer dureeMinute) {
        this.dureeMinute = dureeMinute;
    }
}
