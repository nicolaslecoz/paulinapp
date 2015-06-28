package org.nicolaslecoz.paulinapp.domain;


import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MoisPossible {
    public String nom;
    public Month type;

    public MoisPossible(String nom, Month type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Month getType() {
        return type;
    }

    public void setType(Month type) {
        this.type = type;
    }

    static public List<MoisPossible> construireListeMoisPossible() {
        List<MoisPossible> listeMoisPossible = new ArrayList<>();

        listeMoisPossible.add(new MoisPossible("Janvier", Month.JANUARY));
        listeMoisPossible.add(new MoisPossible("Février", Month.FEBRUARY));
        listeMoisPossible.add(new MoisPossible("Mars", Month.MARCH));
        listeMoisPossible.add(new MoisPossible("Avril", Month.APRIL));
        listeMoisPossible.add(new MoisPossible("Mai", Month.MAY));
        listeMoisPossible.add(new MoisPossible("Juin", Month.JUNE));
        listeMoisPossible.add(new MoisPossible("Juillet", Month.JULY));
        listeMoisPossible.add(new MoisPossible("Aout", Month.AUGUST));
        listeMoisPossible.add(new MoisPossible("Septembre", Month.SEPTEMBER));
        listeMoisPossible.add(new MoisPossible("Octobre", Month.OCTOBER));
        listeMoisPossible.add(new MoisPossible("Novembre", Month.NOVEMBER));
        listeMoisPossible.add(new MoisPossible("Décembre", Month.DECEMBER));

        return listeMoisPossible;
    }
}
