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
    // Default option values:
    m_dt            = 1;
    m_pixelsize     = 1.0f;
    m_pixelsperiter = 10;

    m_rng = new Random();
    setupCanvas(1000, 1000);

    m_container = new JLabel(new ImageIcon());
    m_container.setIcon(new ImageIcon(m_img));

    add(m_container);

    repaint(); // Maybe put this earlier
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

  public void go(int width, int height, int vertices, float pixelsize, int pointperiter, int dt)
  {
    setupCanvas(width, height);
    m_pixelsize = pixelsize;
    m_pixelsperiter = pointperiter;
    m_dt = dt;

    m_startPoints = new Vector<Point>();

    if (vertices == 3)
    {
      m_startPoints.add(new Point((m_img.getWidth()-1)/2, 0                   ));
      m_startPoints.add(new Point(0                     , m_img.getHeight()-1 ));
      m_startPoints.add(new Point(m_img.getWidth()-1    , m_img.getHeight()-1 ));
    }
    else if (vertices == 5)
    {
      m_startPoints.add(new Point((int)((double)m_img.getWidth() * 0.19098301), (int)((double)m_img.getHeight() * 0.55901668)) );
      m_startPoints.add(new Point((int)((double)m_img.getWidth() * 0.80901699), (int)((double)m_img.getHeight() * 0.55901668)) );
      m_startPoints.add(new Point(m_img.getWidth()             ,                (int)((double)m_img.getHeight() * 0.19098301)) );
      m_startPoints.add(new Point((int)((double)m_img.getWidth() * 0.5)       , 0        ) );
      m_startPoints.add(new Point(0                            , (int)((double)m_img.getHeight() * 0.19098301)) );
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
}
