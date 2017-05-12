import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;

public class LeftPane extends JPanel
{
  private JButton    m_go;

  public LeftPane(ActionListener _parent)
  {
    m_go     = new JButton("Start");
    m_go.addActionListener(_parent);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(m_go);
  }
}
