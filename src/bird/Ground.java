package bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground {
    BufferedImage ground;
    int x,y;
    int width,height;
    public Ground() throws IOException{
        ground=ImageIO.read(
                getClass().
                        getResourceAsStream("/images/ground.png"));
        /**
         * ground=ImageIO.read(
         getClass().
         getResource("../images/ground.png"));
         */
        x=0;
        y=500;
        width=ground.getWidth();
        height=ground.getHeight();
    }
    public void paint(Graphics g)
    {
        g.drawImage(ground, x, y, width, height,null);
    }
    public void move()
    {
        x--;
        if(x<-111)
        {
            x=0;
        }
    }
    public boolean hitBird(Bird b)
    {
        if(b.y+b.height/2>=500)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
