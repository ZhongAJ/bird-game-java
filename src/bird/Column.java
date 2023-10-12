package bird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Column extends JPanel {
    BufferedImage column;
    int x;
    int y;
    int width;
    int height;
    int distance;//柱子间水平距离
    int gap;//上下间距
    int max,min;
    int count=0;
    public Column(int n) throws IOException{
        column=ImageIO.read(
                getClass().getResourceAsStream("/images/column.png"));
        /**
         * column=ImageIO.read(
         getClass().getResource("../images/column.png"));
         */
        width=column.getWidth();
        height=column.getHeight();
        distance=245;
        gap=144;
        max=420-gap/2;
        min=80+gap/2;
        Random r=new Random();
        x=500+(n-1)*distance;
        y=min+r.nextInt(max)%(max-min+1);
        //r.nextInt(max)是0-max之间的值
    }
    public void step(){
        x--;
        if(x+width/2<=0){
            x=2*distance-width/2;
            Random r=new Random();
            y=min+r.nextInt(max)%(max-min+1);
        }
    }
    public void paint(Graphics g){
        g.drawImage(column, x-width/2, y-height/2,width,height,null);
    }
    public boolean hitBird(Bird b) {
        int x1=x-width/2-b.width/2;

        int x2=x+b.width/2+b.width/2;
        int y1=y-gap/2+b.height/2;
        int y2=y+gap/2-b.height/2;
        if(b.x >=x1&&b.x<=x2){//先检测是否在柱子范围内
            if(b.y<=y1 || b.y>y2){
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    public boolean hitMoney(Bird b){
        int x1=x-44;
        int x2=x;
        int y1=y-44;
        int y2=y+22;
        if(b.x>=x1&&b.x<=x2){
            if(b.y>=y1&&b.y<=y2){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean point(Bird b)
    {
        if(b.x==x+width/2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
//    public boolean point1(Bird b){
//        if(b.x==x-44)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
}
