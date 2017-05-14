import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Vector;
import java.lang.Math;

class Canvas extends JPanel implements Runnable
{
  private BufferedImage m_img;
  private JLabel m_container;
  private Thread m_animator;
  private Algorithm m_algo;
  private OptionsStruct m_os;

  public Canvas()
  {
    setupCanvas(1000, 1000);

    m_container = new JLabel(new ImageIcon());
    m_container.setIcon(new ImageIcon(m_img));
    add(m_container);

    repaint();
  }

  private void setupCanvas(int width, int height)
  {
    m_img = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );

    Graphics2D g = m_img.createGraphics();
    g.setPaint( new Color(255,255,255) );
    g.fillRect( 0,0, m_img.getWidth(), m_img.getHeight() );
  }

  protected void addPixel(Point end)
  {
    Graphics2D g2 = m_img.createGraphics();
    g2.setStroke( new BasicStroke(m_os.pixelSize) );
    g2.setPaint(  new Color(0,0,0) );
    g2.draw(new Line2D.Float( (float)end.x, (float)end.y, (float)end.x, (float)end.y ));
    m_container.setIcon(new ImageIcon(m_img));
  }

  protected void nextPixel()
  {
    for (int j = 0; j < m_os.pointsperiter; j++)
    {
      addPixel(m_algo.Step(m_os));
    }
    repaint();
  }

  public void go(OptionsStruct os) // Start a new sequence.
  {
    switch (os.algorithm)
    {
      case OptionsStruct.ALGO_CLOCKWISE:
        m_algo = new Clockwise();
        break;
      case OptionsStruct.ALGO_NODUPS:
        m_algo = new NoDups();
        break;
      case OptionsStruct.ALGO_NOADJACENT:
        m_algo = new NoAdjacent();
        break;
      case OptionsStruct.ALGO_BARNSLEY:
        m_algo = new Fern();
        break;
      case OptionsStruct.ALGO_VANILLA:
      default:
        m_algo = new Vanilla();
        break;
    }

    m_os = os;
    setupCanvas(os.width, os.height);

    Vector<Point> pts = m_algo.Initialize(os);

    for(int i = 0; i < pts.size(); i++) // Add initial elements
    {
      addPixel( pts.elementAt(i) );
    }

    repaint();
    m_animator = new Thread(this);
    m_animator.start();
  }

  public void run()
  {
    long tm = System.currentTimeMillis();
    while(Thread.currentThread() == m_animator)
    {
      nextPixel();
      try
      {
        tm += m_os.dt;
        Thread.sleep(Math.max(0, tm - System.currentTimeMillis() ) );
      }
      catch (InterruptedException e)
      {
        break;
      }
    }
  }
}
