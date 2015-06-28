package org.nicolaslecoz.paulinapp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EtatApplication {
    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    public long id;

    public String repertoireTravail;
    public boolean enCoursGenerationImage = false;
    public int nbPageAGenerer = 1;
    public int nbPageGenerer = 0;

    @OneToMany
    public List<Page> listePage = new ArrayList<>();
}
