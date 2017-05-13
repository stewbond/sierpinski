import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MainFrame extends JFrame implements ActionListener
{
  private LeftPane  leftPanel;
  private Canvas    canvas;

  public MainFrame(String name)
  {
    super(name);

    leftPanel  = new LeftPane(this);
    canvas     = new Canvas();

    this.add( leftPanel  , BorderLayout.LINE_START);
    this.add( canvas     , BorderLayout.CENTER);
  }

  public void actionPerformed(ActionEvent event)
  {
    canvas.go(leftPanel.getCanvasWidth(),
              leftPanel.getCanvasHeight(),
              leftPanel.getStartPoints(),
              leftPanel.getPixelSize(),
              leftPanel.getPointsPerIter(),
              leftPanel.getIter()
              );
  }
}
