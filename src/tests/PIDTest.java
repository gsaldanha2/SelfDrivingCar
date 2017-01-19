package tests;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Gregory on 10/29/16.
 */
public class PIDTest {

    private final float SPEED = 0.1f;
    private BufferedImage image_ = new BufferedImage(1000, 1000, BufferedImage.TYPE_3BYTE_BGR);
    private Graphics g2d = image_.createGraphics();
    private float x_ = 0, y_ = 1;
    private float rotation_ = 0;
    private Line2D.Float lines_[];
    private float max = 0.8f;

    public static void main(String[] args) {
        PIDTest pidTest = new PIDTest();
        pidTest.init();
        pidTest.drawLine();
        pidTest.run(0.2f, 3f, 0.01f);
        pidTest.save();
    }

    public void init() {
        lines_ = new Line2D.Float[] {new Line2D.Float(0, 0, 200, 200), new Line2D.Float(200, 200, 358, 420), new Line2D.Float(358, 420, 600, 540), new Line2D.Float(600, 540, 880, 530), new Line2D.Float(880, 530, 1000, 490)};
    }

    private void save() {
        try {
            ImageIO.write(image_, "PNG", new File("data.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(float alpha, float beta, float delta) {
        float cte = 0;
        float total_cte = 0;
        int index = 0;

        while (index < lines_.length) {
            float diff_cte = -cte;

            //compute cte
            float dx = lines_[index].x2 - lines_[index].x1;
            float dy = lines_[index].y2 - lines_[index].y1;
            float rx = x_ - lines_[index].x1;
            float ry = y_ - lines_[index].y1;

            cte = (float) ((ry * dx - rx * dy) / Math.sqrt(dx * dx + dy * dy));
            float u = (rx * dx + ry * dy) / (dx * dx + dy * dy);
            if (u > 1) {
                index++;
            }

            diff_cte += cte;
            total_cte += cte;
            float steer = -alpha * cte - beta * diff_cte - delta * total_cte;
            move(steer);
            draw((int) x_, (int) y_);
            System.out.println(x_ + ", " + y_ + ", steer: " + steer + ", cte: " + cte);
        }
    }

    private void move(float steer) {
        if (steer > max) {
            steer = max;
        } else if (steer < -max) {
            steer = -max;
        }
        rotation_ += steer;
        x_ += SPEED * Math.cos(rotation_);
        y_ += SPEED * Math.sin(rotation_);
    }

    public void drawLine() {
        g2d.setColor(Color.RED);
        for (int i = 0; i < lines_.length; i++) {
            g2d.drawLine((int) lines_[i].x1, (int) lines_[i].y1, (int) lines_[i].x2, (int) lines_[i].y2);
        }
    }

    public void draw(int x, int y) {
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x, y, 1, 1);
    }

}
