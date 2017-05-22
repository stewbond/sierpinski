import javax.swing.JFrame;

public class Main
{
  public static void main(String[] argv)
  {
    JFrame f = new MainFrame("Sierpinski");
    f.setTitle("The Chaos Game");
    f.pack();
    f.setSize(1240,1035);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
