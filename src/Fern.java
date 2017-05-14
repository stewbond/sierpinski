import java.awt.Point;
import java.util.Vector;
import java.util.Random;

public class Fern extends Algorithm
{
  private Random m_rng;

  protected double a[];
  protected double b[];
  protected double c[];
  protected double d[];
  protected double e[];
  protected double f[];
  protected double p[];
  private double x;
  private double y;

  public Vector<Point> Initialize(OptionsStruct os)
  {
    // Barnsley Fern Constants
    a = new double[]{ 0   ,  0.85,  0.20, -0.15 };
    b = new double[]{ 0   ,  0.04, -0.26,  0.28 };
    c = new double[]{ 0   , -0.04,  0.23,  0.26 };
    d = new double[]{ 0.16,  0.85,  0.22,  0.24 };
    e = new double[]{ 0   ,  0   ,  0   ,  0    };
    f = new double[]{ 0   ,  1.60,  1.60,  0.44 };
    p = new double[]{ 0.01,  0.85,  0.07,  0.07 };

    x =0.0;  y = 0.0;

    m_rng = new Random();

    Vector<Point> ret = new Vector<Point>();
    ret.add( Translate(x, y, os)  );
    return ret;
  }

  public Point Step(OptionsStruct os)
  {
    int r = RandomSelect();


    double nextX = x*a[r] + y*b[r] + e[r];
    double nextY = x*c[r] + y*d[r] + f[r];

    x = nextX;
    y = nextY;

    return Translate (x, y, os);
  }

  private Point Translate(double _x, double _y, OptionsStruct os)
  {
    // xmin = -2.25; --> x = 0
    // xmax = 2.75;  --> x = os.width
    // ymin = 0;     --> y = os.height
    // ymax = 10;    --> y = 0

    Point pt = new Point();
    pt.x = (int)((_x + 2.25) * (os.width / 5.0));
    pt.y = (int)((10.0 - _y) * (os.height / 10.0));
    return pt;
  }

  private int RandomSelect()
  {
    double rand = m_rng.nextDouble();

    double total = 0.0;
    for (int select = 0; select < 4; ++select)
    {
      total += p[select];
      if (rand <= total)
        return select;
    }
    return 3; // This should never happen
  }
}
