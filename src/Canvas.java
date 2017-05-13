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
import java.util.Random;
import java.lang.Math;

class Canvas extends JPanel implements Runnable
{
  private BufferedImage m_img;
  private JLabel m_container;
  private Vector<Point> m_startPoints;
  private Point m_lastPoint;
  private Random m_rng;
  private Thread m_animator;
  private int   m_dt;
  private float m_pixelsize;
  private int   m_pixelsperiter;

  public Canvas()
  {
    m_rng = new Random();
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
    g2.setStroke( new BasicStroke(m_pixelsize) );
    g2.setPaint(  new Color(0,0,0) );
    g2.draw(new Line2D.Float( (float)end.x, (float)end.y, (float)end.x, (float)end.y ));
    m_container.setIcon(new ImageIcon(m_img));
  }

  protected void nextPixel()
  {
    for (int j = 0; j < m_pixelsperiter; j++)
    {
      int i = java.lang.Math.abs(m_rng.nextInt()) % m_startPoints.size();
      m_lastPoint = new Point( (m_startPoints.elementAt(i).x - m_lastPoint.x) / 2 + m_lastPoint.x,
                               (m_startPoints.elementAt(i).y - m_lastPoint.y) / 2 + m_lastPoint.y);
      addPixel(m_lastPoint);
    }
    repaint();
  }

  public void go(OptionsStruct os)
  {
    setupCanvas(os.width, os.height);
    m_pixelsize     = os.pixelSize;
    m_pixelsperiter = os.pointsperiter;
    m_dt            = os.dt;

    m_startPoints = new Vector<Point>();

    if (os.startPoints == 3)
    {
      m_startPoints.add(new Point((m_img.getWidth()-1)/2, 0                   ));
      m_startPoints.add(new Point(0                     , m_img.getHeight()-1 ));
      m_startPoints.add(new Point(m_img.getWidth()-1    , m_img.getHeight()-1 ));
    }
    else
    {
      double radius = Math.min(m_img.getWidth(), m_img.getHeight() ) / 2.0;
      Vector<Point> points = generatePoints(os.startPoints, radius);
      for (int i = 0; i < points.size(); i++)
      {
        Point point = points.elementAt(i);
        m_startPoints.add(new Point( (int)(point.x + radius) ,
                                     (int)(point.y + radius) ));
      }
    }

    m_lastPoint = new Point(m_rng.nextInt() % m_img.getWidth()  ,
                            m_rng.nextInt() % m_img.getHeight() );
    addPixel(m_lastPoint);

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
        tm += m_dt;
        Thread.sleep(Math.max(0, tm - System.currentTimeMillis() ) );
      }
      catch (InterruptedException e)
      {
        break;
      }
    }
  }

  private Vector<Point> generatePoints(int number, double radius)
  {
    Vector<Point> points = new Vector<Point>();
    for (int i = 0; i < number; i++)
    {
      double angle = i * 2 * Math.PI / (double)number; // Ensure this is floating point
      points.add(new Point( (int)(Math.sin(angle) * (double)radius),
                            (int)(Math.cos(angle) * (double)radius )));
    }
    return points;
  }
}
