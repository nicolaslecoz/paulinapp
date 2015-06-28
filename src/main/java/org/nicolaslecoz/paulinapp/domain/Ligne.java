package org.nicolaslecoz.paulinapp.domain;


import javax.persistence.*;

@Entity
public class Ligne {
    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    public long id;

    public String code;
    public String libelle;
    @OneToOne
    public SaisiLigne saisiLigne;

    public Ligne() {
    }
    public Ligne(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public SaisiLigne getSaisiLigne() {
        return saisiLigne;
    }
    public void setSaisiLigne(SaisiLigne saisiLigne) {
        this.saisiLigne = saisiLigne;
    }
}
