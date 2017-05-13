import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class Algorithm
{
  Vector<Point> m_startPoints;
  Point         m_lastPoint;
  Random        m_rng;

  public Algorithm()
  {
    m_rng = new Random();
    m_startPoints = new Vector<Point>();
    m_lastPoint = new Point();
  }

  public Vector<Point> Initialize(OptionsStruct os)
  {
    m_startPoints = new Vector<Point>();

    if (os.startPoints == 3) // not equilateral, Let's use the whole screen for this case.
    {
      m_startPoints.add(new Point((os.width-1)/2, 0           ));
      m_startPoints.add(new Point(0             , os.height-1 ));
      m_startPoints.add(new Point( os.width-1   , os.height-1 ));
    }
    else
    {
      double radius = Math.min(os.width, os.height ) / 2.0;
      Vector<Point> points = generatePoints(os.startPoints, radius);
      for (int i = 0; i < points.size(); i++)
      {
        Point point = points.elementAt(i);
        m_startPoints.add(new Point( (int)(point.x + radius) ,
                                     (int)(point.y + radius) ));
      }
    }

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
