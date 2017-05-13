import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public abstract class Algorithm
{
  public abstract Vector<Point> Initialize(OptionsStruct os);
  public abstract Point Step(OptionsStruct os);

  protected Vector<Point> genericPolygon(OptionsStruct os)
  {
    Vector<Point> startPoints = new Vector<Point>();

    if (os.startPoints == 3) // not equilateral, Let's use the whole screen for this case.
    {
      startPoints.add(new Point((os.width-1)/2, 0           ));
      startPoints.add(new Point(0             , os.height-1 ));
      startPoints.add(new Point( os.width-1   , os.height-1 ));
    }
    else
    {
      double radius = Math.min(os.width, os.height ) / 2.0;
      Vector<Point> points = generatePoints(os.startPoints, radius);
      for (int i = 0; i < points.size(); i++)
      {
        Point point = points.elementAt(i);
        startPoints.add(new Point( (int)(point.x + radius) ,
                                   (int)(point.y + radius) ));
      }
    }

    return startPoints;
  }

  private Vector<Point> generatePoints(int number, double radius)
  {
    Vector<Point> points = new Vector<Point>();
    for (int i = 0; i < number; i++)
    {
      double angle = i * 2 * Math.PI / (double)number; // Ensure this is floating point
      points.add(new Point( (int)(Math.sin(angle) * radius),
                            (int)(Math.cos(angle) * radius )));
    }
    return points;
  }
}
