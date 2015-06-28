package org.nicolaslecoz.paulinapp.tool.detectline;


import org.nicolaslecoz.paulinapp.domain.Coordonnee;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;

import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.*;

public class DetectVerticalLineTool {
    static private int PAS_PX = 10;
    static private int MARGE_PX = (int) (1 * SCALE_CM);
    static private int TRAIT_PX = (int) (3 * SCALE_CM);

    private boolean detectVerticalBlackPixel(BufferedImage bim, int x, int y) {
        List<Integer> listeRGB = new ArrayList<>();

        for (int i = -2; i < 2; i++) {
            listeRGB.add(bim.getRGB(x, y + i));
        }
        for (int rgb : listeRGB) {
            if (isBlackPixel(rgb)) {
                return true;
            }
        }
        return false;
    }

    private boolean findLine(BufferedImage bim, int x, int y) {

        for (int i = 0; i < TRAIT_PX; i++) {
            if (!detectVerticalBlackPixel(bim, x + i, y)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> parse(BufferedImage bim, Coordonnee leftCorner) {
        List<Integer> result = new ArrayList<>();

        for (int curseurV = leftCorner.y - PAS_PX; curseurV < bim.getHeight(); curseurV++) {
            if (findLine(bim, leftCorner.x + MARGE_PX, curseurV)) {
                result.add(curseurV);
                curseurV += PAS_PX;
            }
        }
        return result;
    }

}
