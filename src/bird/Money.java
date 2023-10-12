package bird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Money extends JPanel {
    BufferedImage money;
    int x;
    int y;
    int width;
    int height;
    int score;
    boolean flag = true;

    public Money() throws IOException {
        money = ImageIO.read(getClass().getResourceAsStream("/images/money.png"));

        width = money.getWidth();
        height = money.getHeight();
        x = -width;
        y = -height;
    }

    public void init() {
        x = -width;
        y = -height;
        score = 0;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(money, x, y, width, height, null);
    }

    public boolean hitBird(Bird bird) {
        int x1 = x - width / 2;
        int y1 = y - height / 2 - bird.height / 2;
        int y2 = y + height / 2 + bird.height / 2;

        if (bird.x >= x1) {
            if (flag) {
                if (bird.y < y1 || bird.y > y2) {
                    return false;
                } else {
                    score++;
                    flag = false;
                    return true;
                }
            } else {
                return true;
            }
        } else {
            flag = true;
        }
        return false;
    }
}
