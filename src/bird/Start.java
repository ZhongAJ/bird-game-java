package bird;
import java.io.IOException;
import javax.swing.JFrame;

public class Start {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        JFrame frame=new JFrame();
        BackGround bg=new BackGround();
        frame.add(bg); // add()方法为祖父类Container类中的方法
        frame.setTitle("飞翔的小鸟");
        frame.setSize(432,644);// 方法从window类中继承
        frame.setVisible(true);// 方法从window类中继承，使window界面可见 ，window类从Component中继承
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.show();//此方法从JDK1.5之后开始以后就由Component.setVisible取代
        bg.action();
    }
}
