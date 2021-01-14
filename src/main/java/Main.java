import org.apache.commons.collections.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Main {

    public static void main(String[] args) throws AWTException, InterruptedException {
        File folder = null;
        try {
            
//            folder = ImageIO.read(new File("C:/Users/bartl/Documents/angelika/Leczenie_żywieniowe_Pierzak/"));
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream("test.docx");
            folder = new File("C:/Users/bartl/Documents/angelika/Leczenie_żywieniowe_Pierzak/");
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File file: listOfFiles) {
                    BufferedImage image = ImageIO.read(file);
                    String imgText = ScreenshotTextReader.getImgText(image);
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText(imgText);
                }
            }
            document.write(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        Robot robot = new Robot();
//        for (int j = 0; j < 10000; j++) {
//            restAndStartTraining(robot);
//            for (int i = 0; i < 1000; i++) {
//                BufferedImage screenshot = JNAScreenShot.getScreenshot(new Rectangle(1350, 420, 550, 140));
//                String imgText = ScreenshotTextReader.getImgText(screenshot);
//                String last = getLastString(imgText);
//                System.out.println(last);
//                performAction(robot, last);
//                if (imgText.contains("initialized log throwing") || imgText.contains("os for these are: Low Throw")) {
//                    performRandomThrow(robot);
//                    Thread.sleep(700);
//                    continue;
//                }
//                if (last.contains("aren't training")) {
//                    performRandomThrow(robot);
//                    break;
//                }
//                if (last.contains("don't need to rest'")) {
//                    performRandomThrow(robot);
//                    continue;
//                }
//                if (last.isEmpty()) {
//                    performRandomThrow(robot);
//                    continue;
//                }
//                if (imgText.contains("training has ended. Please wait 60 se")) {
//                    Thread.sleep(60000);
//                }
////                if (last.contains("don't need to rest")) {
////                    continue;
////                }

//                Thread.sleep(700);
//            }
//        }

    }

    private static void performRandomThrow(Robot robot) throws InterruptedException {
        int nextThrow = getRandomNextThrow();
        performRandomAction(robot, nextThrow);
        Thread.sleep(700);
    }

    private static void performRandomAction(Robot robot, int nextThrow) {
        if (nextThrow == 1) {
            robot.keyPress(KeyEvent.VK_1); // 1
            robot.keyRelease(KeyEvent.VK_1); // 1
        } else if (nextThrow == 2) {
            robot.keyPress(KeyEvent.VK_2); // 2
            robot.keyRelease(KeyEvent.VK_2); // 2
        } else if (nextThrow == 3) {
            robot.keyPress(KeyEvent.VK_3); // 3
            robot.keyRelease(KeyEvent.VK_3); // 3
        }

    }

    private static int getRandomNextThrow() {
        Random random = new Random();
        int next = random.nextInt(3);
        if (next == 0) {
            return getRandomNextThrow();
        }
        return next;
    }

    private static void restAndStartTraining(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_R);
        robot.keyRelease(KeyEvent.VK_R);
        Thread.sleep(20000);
        pressKeyNTimes(robot, KeyEvent.VK_DOWN, 3);
        pressKeyNTimes(robot, KeyEvent.VK_SPACE, 1);
        pressKeyNTimes(robot, KeyEvent.VK_UP, 4);
        pressKeyNTimes(robot, KeyEvent.VK_DOWN, 1);
    }

    private static void pressKeyNTimes(Robot robot, int key, int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            robot.keyPress(key);
            robot.keyRelease(key);
            Thread.sleep(600);
        }
    }

    private static String getLastString(String imgText) {
        String[] split = imgText.split("\n");
        List<String> next_throw = Stream.of(split)
                .filter(s -> s.contains("next throw") || s.contains("aren't training")
                || s.contains("don't need to rest"))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(next_throw)) {
            return next_throw.get(next_throw.size() - 1);
        }
        return "";
    }

    private static void performAction(Robot robot, String imgText) {
        if (imgText.contains("Low")) {
            robot.keyPress(KeyEvent.VK_1); // 1
            robot.keyRelease(KeyEvent.VK_1); // 1
        } else if (imgText.contains("Normal")) {
            robot.keyPress(KeyEvent.VK_2); // 2
            robot.keyRelease(KeyEvent.VK_2); // 2
        } else if (imgText.contains("High")) {
            robot.keyPress(KeyEvent.VK_3); // 3
            robot.keyRelease(KeyEvent.VK_3); // 3
        }
    }
}