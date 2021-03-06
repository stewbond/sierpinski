import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class LeftPane extends JPanel
{
  JLabel     delayLbl;          JTextField         delayBox;
  JLabel     ptspiLbl;          JTextField         ptspiBox;
  JLabel     pointLbl;          JTextField         pointBox;
  JLabel     rotatLbl;          JTextField         rotatBox;
  JLabel     widthLbl;          JTextField         widthBox;
  JLabel     heigtLbl;          JTextField         heigtBox;
  JLabel     pixelLbl;          JTextField         pixelBox;
  JLabel     algorLbl;          JComboBox<String>  algorBox;
  JButton    m_go;

  public LeftPane(ActionListener _parent)
  {
    String[] algorithms = {"Vanilla","No Duplicates","Clockwise","No Adjacents", "No Adjacent Ex", "Barnsley Fern"};
    delayLbl = new JLabel("Iteration period (ms)");
    delayBox = new JTextField("10");
    ptspiLbl = new JLabel("Points per iteration");
    ptspiBox = new JTextField("10");
    pointLbl = new JLabel("Number of anchor points");
    pointBox = new JTextField("3");
    rotatLbl = new JLabel("Rotation (deg)");
    rotatBox = new JTextField("0");
    widthLbl = new JLabel("Canvas width (pixels)");
    widthBox = new JTextField("1000");
    heigtLbl = new JLabel("Canvas height (pixels)");
    heigtBox = new JTextField("1000");
    pixelLbl = new JLabel("Pixel size");
    pixelBox = new JTextField("1.0");
    algorLbl = new JLabel("Algorithm");
    algorBox = new JComboBox<String>(algorithms);
    m_go     = new JButton("Start");

    m_go.addActionListener(_parent);

    Dimension maxSize = delayBox.getMaximumSize();
    maxSize.height = 25;
    delayLbl.setMaximumSize(maxSize);       delayBox.setMaximumSize(maxSize);
    ptspiLbl.setMaximumSize(maxSize);       ptspiBox.setMaximumSize(maxSize);
    pointLbl.setMaximumSize(maxSize);       pointBox.setMaximumSize(maxSize);
    rotatLbl.setMaximumSize(maxSize);       rotatBox.setMaximumSize(maxSize);
    widthLbl.setMaximumSize(maxSize);       widthBox.setMaximumSize(maxSize);
    heigtLbl.setMaximumSize(maxSize);       heigtBox.setMaximumSize(maxSize);
    pixelLbl.setMaximumSize(maxSize);       pixelBox.setMaximumSize(maxSize);
    algorLbl.setMaximumSize(maxSize);       algorBox.setMaximumSize(maxSize);

    algorBox.setSelectedIndex(0);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(delayLbl);    this.add(delayBox);
    this.add(ptspiLbl);    this.add(ptspiBox);
    this.add(pointLbl);    this.add(pointBox);
    this.add(rotatLbl);    this.add(rotatBox);
    this.add(widthLbl);    this.add(widthBox);
    this.add(heigtLbl);    this.add(heigtBox);
    this.add(pixelLbl);    this.add(pixelBox);
    this.add(algorLbl);    this.add(algorBox);

    this.add(m_go);
  }

  public OptionsStruct getOptions()
  {
    OptionsStruct os = new OptionsStruct();
    try
    {
      os.width         = Integer.parseInt( widthBox.getText() );
      os.height        = Integer.parseInt( heigtBox.getText() );
      os.startPoints   = Integer.parseInt( pointBox.getText() );
      os.rotation      = Float.parseFloat( rotatBox.getText() );
      os.pixelSize     = Float.parseFloat( pixelBox.getText() );
      os.pointsperiter = Integer.parseInt( ptspiBox.getText() );
      os.dt            = Integer.parseInt( delayBox.getText() );
      os.algorithm     = algorBox.getSelectedIndex();
    }
    catch(NumberFormatException execp) {  }
    return os;
  }
}
