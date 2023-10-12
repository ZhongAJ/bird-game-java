package bird;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    BufferedImage[] images;
    BufferedImage bird;
    int x;
    int y;
    int height;
    int width;
    double qj;//倾角
    int index;
    double v0, v, t;
    // 初始速度，实时速度，时间间隔
    double g, s;
    // 重力加速度，运动距离
    public Bird() throws IOException{
        images=new BufferedImage[8];
        for(int i=0;i<images.length;i++)
        {
            images[i]=ImageIO.read(
                    getClass().
                            getResourceAsStream("/images/"+i+".png")
            );
            /**
             * images[i]=ImageIO.read(
             getClass().
             getResource("../images/"+i+".png")
             );
             */
        }
        bird=images[0];
        x=132;
        y=280;
        height=bird.getHeight();
        width=bird.getWidth();

        v0 = 4;
        // 初始速度为20
        g = 1;
        // 重力加速度为4.5（因为9.8太快这里不适合，所以改小了）
        t = 0.25;
        // 时间间隔为0.25秒
        s = 0;
        // 初始运动距离
        v = v0;
        // 把初始速度给v（实时速度）
        index=0;
    }

    public void fly() {
        // 小鸟飞的方法
        s = v * t - g * t * t / 2;
        // 垂直的运动距离
        v = v - g * t;
        // 实时速度，这是物理公式，实时速度等于初始速度-加速度*时间
        y = (int) (y - s);
        // 算出小鸟的y坐标
        qj = Math.atan(s/8);
        // 算出倾角
        if(y+height/2>=500)
        {
            y=500-height/2;
        }
    }
    public void step()
    {
        index++;
        bird=images[index/8%8];
    }
    public void initFly()
    {
        v=v0;

    }
    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.rotate(qj, x, y);
        g2d.drawImage(bird, x-width/2, y-height/2, width, height, null);
        g2d.rotate(-qj, x, y);
    }
}
