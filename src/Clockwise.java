import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class Clockwise extends Algorithm
{
  Vector<Point> m_startPoints;
  Point         m_lastPoint;
  Random        m_rng;
  int           m_lastSelection;

  public Clockwise()
  {
    m_startPoints    = new Vector<Point>();
    m_lastPoint      = new Point();
    m_lastSelection  = 42;
    m_rng            = new Random();
  }

  public Vector<Point> Initialize(OptionsStruct os)
  {
    m_startPoints = genericPolygon(os);

    m_lastPoint = new Point(m_rng.nextInt() % os.width  ,
                            m_rng.nextInt() % os.height );

    Vector<Point> ret = new Vector<Point>(m_startPoints);
    ret.add(m_lastPoint);
    return ret;
  }

  public Point Step(OptionsStruct os)
  {
    int nextSelection = Math.abs( m_rng.nextInt() ) % m_startPoints.size() ;
    while( nextSelection == m_lastSelection ||
           nextSelection == (m_lastSelection + 1)%m_startPoints.size() )
    {
      nextSelection = Math.abs( m_rng.nextInt() ) % m_startPoints.size();
    }

    m_lastSelection = nextSelection;
    m_lastPoint = new Point( (m_startPoints.elementAt(nextSelection).x - m_lastPoint.x) / 2 + m_lastPoint.x,
                             (m_startPoints.elementAt(nextSelection).y - m_lastPoint.y) / 2 + m_lastPoint.y);

    return m_lastPoint;
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
