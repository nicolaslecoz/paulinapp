package org.nicolaslecoz.paulinapp.tool.detectcorner;


import static org.nicolaslecoz.paulinapp.configuration.Conf.MAX_WIDTH;
import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;

import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.*;

import java.awt.image.BufferedImage;

import org.nicolaslecoz.paulinapp.domain.Coordonnee;

public class DetectCornerTool {
    private static int MARGE_PX = (int)SCALE_CM * 3;
    private static double SEUIL_TRAIL = 1.2 * SCALE_CM;

    static private Coordonnee verifierSiOnATrouveLeTrait(int currentNbPixelTrait, int recordNbPixelTrait, int curseurH, int curseurV) {
        if (currentNbPixelTrait > recordNbPixelTrait) {
            recordNbPixelTrait= currentNbPixelTrait;
            if (recordNbPixelTrait > (int) SEUIL_TRAIL) {
                return new Coordonnee(curseurH - recordNbPixelTrait, curseurV);
            }
        }
        return null;
    }

    static public Coordonnee detectHeaderLeftCorner(BufferedImage bim) {
        for (int curseurV = 10; curseurV < (int) SCALE_CM * 10; curseurV++) {
            int currentNbPixelTrait = 0;
            int recordNbPixelTrait = 0;
            int deviationPx = 0;
            int sensDeviation = 1;

            for (int curseurH = 0; curseurH < MARGE_PX; curseurH++) {
                int currentRGB = bim.getRGB(curseurH, curseurV + deviationPx * sensDeviation);

                if (isBlackPixel(currentRGB)) {
                    currentNbPixelTrait++;
                } else if (currentNbPixelTrait > 2) {
                    if (deviationPx == 0) {
                        if (isBlackPixel(bim.getRGB(curseurH, curseurV + 1))) {
                            deviationPx++;
                            sensDeviation = 1;
                            currentNbPixelTrait++;
                        } else if (isBlackPixel(bim.getRGB(curseurH, curseurV - 1))) {
                            deviationPx++;
                            sensDeviation = -1;
                            currentNbPixelTrait++;
                        }
                    } else if (isBlackPixel(bim.getRGB(curseurH, curseurV + 1 * sensDeviation))) {
                            deviationPx++;
                            currentNbPixelTrait++;
                    } else {
                        currentNbPixelTrait = 0;
                    }
                } else {
                        currentNbPixelTrait = 0;
                }
                Coordonnee result = verifierSiOnATrouveLeTrait(currentNbPixelTrait, recordNbPixelTrait, curseurH, curseurV);
                if (result != null) {
                    return result;
                }
            }

        }
        return null;
    }

    static public Coordonnee detectHeaderRightCorner(BufferedImage bim) {
        for (int curseurV = 0; curseurV < (int) SCALE_CM * 10; curseurV++) {
            int currentNbPixelTrait = 0;
            int recordNbPixelTrait = 0;
            int deviationPx = 0;
            int sensDeviation = 1;

            for (int curseurH = MAX_WIDTH - 1; curseurH > MAX_WIDTH - MARGE_PX; curseurH--) {
                int currentRGB = bim.getRGB(curseurH, curseurV + deviationPx * sensDeviation);

                if (isBlackPixel(currentRGB)) {
                    currentNbPixelTrait++;
                } else if (currentNbPixelTrait > 2) {
                    if (deviationPx == 0) {
                        if (isBlackPixel(bim.getRGB(curseurH, curseurV + 1))) {
                            deviationPx++;
                            sensDeviation = 1;
                            currentNbPixelTrait++;
                        } else if (isBlackPixel(bim.getRGB(curseurH, curseurV - 1))) {
                            deviationPx++;
                            sensDeviation = -1;
                            currentNbPixelTrait++;
                        }
                    } else if (isBlackPixel(bim.getRGB(curseurH, curseurV + 1 * sensDeviation))) {
                        deviationPx++;
                        currentNbPixelTrait++;
                    } else {
                        currentNbPixelTrait = 0;
                    }
                } else {
                    currentNbPixelTrait = 0;
                }
                Coordonnee result = verifierSiOnATrouveLeTrait(currentNbPixelTrait, recordNbPixelTrait, curseurH, curseurV);
                if (result != null) {
                    return result;
                }
            }

        }
        return null;
    }

}
