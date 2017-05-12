import javax.swing.JFrame;

public class Main
{
  public static void main(String[] argv)
  {
    JFrame f = new MainFrame("Sierpinski");
    f.setTitle("Let's draw dots!");
    f.pack();
    f.setSize(1100,1000);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
