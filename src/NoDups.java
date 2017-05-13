import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class NoDups extends Algorithm
{
  protected Vector<Point> m_startPoints;
  protected Point         m_lastPoint;
  protected Random        m_rng;
  protected int           m_lastSelection;

  public NoDups()
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
    while( nextSelection == m_lastSelection )
    {
      nextSelection = Math.abs( m_rng.nextInt() ) % m_startPoints.size();
    }

    m_lastSelection = nextSelection;
    m_lastPoint = new Point( (m_startPoints.elementAt(nextSelection).x - m_lastPoint.x) / 2 + m_lastPoint.x,
                             (m_startPoints.elementAt(nextSelection).y - m_lastPoint.y) / 2 + m_lastPoint.y);

    return m_lastPoint;
  }
}
