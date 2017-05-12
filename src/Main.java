import javax.swing.JFrame;

public class Main
{
  public static void main(String[] argv)
  {
    JFrame f = new MainFrame("Serpinksy");
    f.setTitle("let's build a serpinsky triangle!");
    f.pack();
    f.setSize(1100,1000);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
