package org.nicolaslecoz.paulinapp.tool.detectline;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.nicolaslecoz.paulinapp.configuration.Conf.SCALE_CM;

import static org.nicolaslecoz.paulinapp.tool.image.ImageTool.*;

public class DetectHorizontalLineTool {
    static private int PAS_PX = (int) (0.3 * SCALE_CM);
    static private int MARGE_PX = (int) (1 * SCALE_CM);
    static private int TRAIT_PX = (int) (0.3 * SCALE_CM);

    private int startX;

    private boolean detectHorizontalBlackPixel(BufferedImage bim, int x, int y) {
        if (isBlackPixel(bim.getRGB(x, y))) {
            return true;
        }
        return false;
    }

    private boolean findLine(BufferedImage bim, int x, int y) {
        for (int i = 0; i < TRAIT_PX; i++) {
            if (!detectHorizontalBlackPixel(bim, x, y + i)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> parse(BufferedImage bim, int y) {
        List<Integer> result = new ArrayList<>();

        for (int x = startX; x < bim.getWidth(); x++) {
            if (findLine(bim, x, y)) {
                result.add(x);
                x += PAS_PX;
            }
        }
        return result;
    }

    public DetectHorizontalLineTool(int startX) {
        this.startX = startX;
    }


}
