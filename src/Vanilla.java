import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class Vanilla extends Algorithm
{
  Vector<Point> m_startPoints;
  Point         m_lastPoint;
  Random        m_rng;

  public Vanilla()
  {
    m_startPoints = new Vector<Point>();
    m_lastPoint   = new Point();
    m_rng         = new Random();
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
    int i = java.lang.Math.abs(m_rng.nextInt()) % m_startPoints.size();
    m_lastPoint = new Point( (m_startPoints.elementAt(i).x - m_lastPoint.x) / 2 + m_lastPoint.x,
                             (m_startPoints.elementAt(i).y - m_lastPoint.y) / 2 + m_lastPoint.y);

    return m_lastPoint;
  }
}
