package bird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackGround extends JPanel {
    /**
     *
     */
    int x;
    int y;
    int width=432;
    int height=644;
    BufferedImage bg,startimg,endimg;
    Money money01, money02;
    Bird bird;
    Ground ground;
    boolean again=false;
    boolean startgame,gameover;
    Column  c1=new Column(1);
    Column  c2=new Column(2);

    public void action() throws InterruptedException
    {
        MouseAdapter adapter=new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if(!gameover)
                {
                    startgame=true;
                    bird.initFly();
                }
                if(gameover){
                    startgame=false;
                    count=0;
                    m=0;
                    money01.init();
                    money02.init();
                    bird.initFly();
                    gameover=false;
                    try {
                        bird=new Bird();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        c1=new Column(1);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        c2=new Column(2);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        };

        this.addMouseListener(adapter);


        while(true)
        {

            if(!gameover){
                ground.move();

                bird.step();

            }
            if(startgame&&!gameover)
            {
                bird.fly();
                c1.step();
                c2.step();
                money01.x = c1.x - money01.width / 2;
                money01.y = c1.y - money01.height / 2;
                money02.x = c2.x - money02.width / 2;
                money02.y = c2.y - money02.height / 2;
            }
//            if(c1.point1(bird)||c2.point1(bird)){
//                m++;
//            }
            m = money01.score + money02.score;
            if(c1.point(bird)||c2.point(bird)){
                count++;
            }
            if(ground.hitBird(bird)||c1.hitBird(bird)||c2.hitBird(bird))
            {
                gameover=true;
            }

            Thread.sleep(8);
            repaint();
        }
    }

    public BackGround() throws IOException{
        //ImageIO.read读取图片
        //getClass当前类的路径
        //getResource路径下的资源
        //../代表是当前路径的上一级路径
        // /代表下一级路径
        startgame=false;
        gameover=false;
        bird=new Bird();
        ground=new Ground();
        money01 = new Money();
        money02 = new Money();
//        money=ImageIO.read(
//                getClass().
//                        getResourceAsStream("/images/money.png"));
        bg=ImageIO.read(
                getClass().
                        getResourceAsStream("/images/bg.png"));
        startimg=ImageIO.read(
                getClass().
                        getResourceAsStream("/images/start.png"));
        endimg=ImageIO.read(
                getClass().
                        getResourceAsStream("/images/gameover.png"));
        /**
         * money=ImageIO.read(
         getClass().
         getResource("../images/money.png"));
         bg=ImageIO.read(
         getClass().
         getResource("../images/bg.png"));
         startimg=ImageIO.read(
         getClass().
         getResource("../images/start.png"));
         endimg=ImageIO.read(
         getClass().
         getResource("../images/gameover.png"));
         */
        width=bg.getWidth();
        height=bg.getHeight();
    }
    int count=0;
    int m=0;
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg, x, y, 432, 644,null);
        c1.paint(g);
        c2.paint(g);
        bird.paint(g);
        if(!c1.hitMoney(bird)&&c1.x>=bird.x){
            //g.drawImage(money, c1.x-22, c1.y-22, 44, 44,null);
        }
        if(!c2.hitMoney(bird)&&c2.x>=bird.x){
            //g.drawImage(money, c2.x-22, c2.y-22, 44, 44,null);
        }

        if(!startgame)
            g.drawImage(startimg, x, y, 432, 644,null);
//		if(gameover)
//			g.drawImage(endimg, x, y, 432, 644,null);
        if(c1.hitBird(bird)||c2.hitBird(bird)){
            g.drawImage(endimg, x, y, 432, 644,null);
        }
        if(ground.hitBird(bird)){
            g.drawImage(endimg, x, y, 432, 644,null);
        }

        if (!money01.hitBird(bird)) {
            money01.paint(g);
        }
        if (!money02.hitBird(bird)) {
            money02.paint(g);
        }

        ground.paint(g);
        Font f1=new Font("宋体",Font.BOLD,25);
        g.setFont(f1);
        g.setColor(Color.black);
        g.drawString("关数："+count+"",0,20);
        Font f2=new Font("宋体",Font.BOLD,25);
        g.setFont(f2);
        g.setColor(Color.black);
        g.drawString("金币："+m+"",0,45);
    }

}