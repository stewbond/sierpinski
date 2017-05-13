import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class Clockwise extends NoDups
{
  public Clockwise()
  {
    super();
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
}
