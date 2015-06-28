package org.nicolaslecoz.paulinapp.tool.detectcorner;


import org.nicolaslecoz.paulinapp.domain.Coordonnee;

import java.awt.image.BufferedImage;

import static org.nicolaslecoz.paulinapp.configuration.Conf.MAX_WIDTH;
import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;

import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.*;

public class StupidDetectCornerTool {
    private static int MARGE_PX = (int)SCALE_CM * 3;
    private static double SEUIL_TRAIL = 1.2 * SCALE_CM;

    static public Coordonnee detectHeaderLeftCorner(BufferedImage bim) {
        for (int curseurV = 0; curseurV < (int) SCALE_CM * 10; curseurV++) {
            int currentNbPixelTrait = 0;
            int recordNbPixelTrait = 0;

            for (int curseurH = 0; curseurH < MARGE_PX; curseurH++) {
                int currentRGB = bim.getRGB(curseurH, curseurV);

                if (isBlackPixel(currentRGB)) {
                    currentNbPixelTrait++;
                } else {
                    currentNbPixelTrait = 0;
                }
                if (currentNbPixelTrait > recordNbPixelTrait) {
                    recordNbPixelTrait= currentNbPixelTrait;
                    if (recordNbPixelTrait > (int) SEUIL_TRAIL) {
                        return new Coordonnee(curseurH - recordNbPixelTrait, curseurV);
                    }
                }
            }
        }
        return null;
    }

    static public Coordonnee detectHeaderRightCorner(BufferedImage bim) {
        for (int curseurV = 0; curseurV < (int) SCALE_CM * 10; curseurV++) {
            int currentNbPixelTrait = 0;
            int recordNbPixelTrait = 0;

            for (int curseurH = MAX_WIDTH - 1; curseurH > MAX_WIDTH - MARGE_PX; curseurH--) {
                int currentRGB = bim.getRGB(curseurH, curseurV);

                if (isBlackPixel(currentRGB)) {
                    currentNbPixelTrait++;
                } else {
                    currentNbPixelTrait = 0;
                }
                if (currentNbPixelTrait > recordNbPixelTrait) {
                    recordNbPixelTrait= currentNbPixelTrait;
                    if (recordNbPixelTrait > (int) SEUIL_TRAIL) {
                        return new Coordonnee(curseurH + recordNbPixelTrait, curseurV);
                    }
                }
            }
        }
        return null;
    }
}
