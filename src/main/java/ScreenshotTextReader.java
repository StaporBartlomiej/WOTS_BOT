import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

class ScreenshotTextReader {

    private static final Tesseract INSTANCE = new Tesseract();

    static {
        INSTANCE.setDatapath("C:/Users/bartl/IdeaProjects/wots/tessdata");
        INSTANCE.setLanguage("pol");
    }

    static String getImgText(BufferedImage bufferedImage) {
        try {
            return INSTANCE.doOCR(bufferedImage);
        } catch (TesseractException e) {
            e.getMessage();
            return "Error while reading image";
        }
    }
}
