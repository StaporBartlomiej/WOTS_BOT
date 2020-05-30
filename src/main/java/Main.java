import org.apache.commons.collections.CollectionUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        for (int j = 0; j < 10000; j++) {
            restAndStartTraining(robot);
            for (int i = 0; i < 1000; i++) {
                BufferedImage screenshot = JNAScreenShot.getScreenshot(new Rectangle(1350, 420, 550, 140));
                String imgText = ScreenshotTextReader.getImgText(screenshot);
                String last = getLastString(imgText);
                System.out.println(last);
                if (last.contains("wrong throwin") || last.contains("aren't training")) {
                    break;
                }

                performAction(robot, last);
                Thread.sleep(700);
            }
        }

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
                .filter(s -> s.contains("next throw") || s.contains("aren't training"))
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