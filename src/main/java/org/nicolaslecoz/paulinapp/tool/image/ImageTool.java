package org.nicolaslecoz.paulinapp.tool.image;

import org.nicolaslecoz.paulinapp.domain.Coordonnee;
import org.nicolaslecoz.paulinapp.domain.SaisiLigne;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class ImageTool {

    public int DEFAULT_SEUIL_PIXEL_PAR_CELLULE = 150;

    public int seuilPixelParCellule = DEFAULT_SEUIL_PIXEL_PAR_CELLULE;

    public boolean isCaseCoche(BufferedImage bim, Coordonnee[] _case) {
        int nbPixelBlack = 0;
        int margePx = 5;

        for (int y = _case[0].y + margePx; y < _case[1].y - margePx; y++) {
            for (int x = _case[0].x + margePx; x < _case[1].x - margePx; x++) {
                if (isBlackPixel(bim.getRGB(x, y))) {
                    nbPixelBlack++;
                }
            }
        }
        if (nbPixelBlack > seuilPixelParCellule) {
            return true;
        }
        return false;
    }

        /*
    on travaille sur une bande de 3x1 CM on doit détecter un trait horizontal de 1 cm, on analyse de la droite vers la gauche
     */

    static public boolean isBlackPixel(int rgb) {
        int red = (rgb >> 16) & 0x0ff;
        int green = (rgb >> 8) & 0x0ff;
        int blue = rgb & 0x0ff;

        int seuil = 50;

        return (red < seuil) || (green < seuil) || (blue < seuil);
    }


    public SaisiLigne buildSaisieLigne(int ligne, BufferedImage bim, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale) {
        SaisiLigne saisiLigne = new SaisiLigne();

        if (isCaseCoche(bim, buildCoordonnee(ligne, 1, listeLigneVerticale, listeLigneHorizontale))) {
            saisiLigne.activePratique = true;
        }

        saisiLigne.listeMoisActivite = buildSaisieMois(ligne, bim, listeLigneVerticale, listeLigneHorizontale);

        return saisiLigne;
    }

    public List<Month> buildSaisieMois(int ligne, BufferedImage bim, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale) {
        List<Month> listeMois = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            int nbColonne = 6;

            if (isCaseCoche(bim, buildCoordonnee(ligne, i + nbColonne, listeLigneVerticale, listeLigneHorizontale))) {
                listeMois.add(Month.of(i + 1));
            }
        }
        return listeMois;
    }

    public Coordonnee[] buildCoordonnee(int ligne, int colonne, List<Integer> listeLigneVerticale, List<Integer> listeLigneHorizontale) {
        Coordonnee[] coordonnees = new Coordonnee[2];

        coordonnees[0] = new Coordonnee(listeLigneHorizontale.get(colonne), listeLigneVerticale.get(ligne));
        coordonnees[1] = new Coordonnee(listeLigneHorizontale.get(colonne + 1), listeLigneVerticale.get(ligne + 1));
        return coordonnees;
    }

    static public void printCase(BufferedImage bim, Coordonnee[] _case) {
        for (int y = _case[0].y; y < _case[1].y; y++) {
            for (int x = _case[0].x; x < _case[1].x; x++) {
                if (isBlackPixel(bim.getRGB(x, y))) {
                    System.out.print("-");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    static public double calculerAngleRotation(Coordonnee leftCorner, Coordonnee rightCorner) {
        double quotient = leftCorner.y - rightCorner.y;
        double diviseur = Math.sqrt(Math.pow(leftCorner.x - rightCorner.x, 2) + Math.pow(leftCorner.y - rightCorner.y, 2));
        double sinus = quotient / diviseur;

        return Math.sinh(sinus);
    }

    static public BufferedImage rotateImage(BufferedImage bim, double angle, Coordonnee pointDeRotation) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, pointDeRotation.x, pointDeRotation.y);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bim, null);
    }

    public ImageTool() {
    }

    public ImageTool(int seuil) {
        this.seuilPixelParCellule = seuil;
    }
}
