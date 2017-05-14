public class OptionsStruct
{
  public int   width;
  public int   height;
  public int   startPoints;
  public float pixelSize;
  public int   pointsperiter;
  public int   dt;
  public int   algorithm;

  public final static int ALGO_VANILLA   = 0;
  public final static int ALGO_NODUPS    = 1;
  public final static int ALGO_CLOCKWISE = 2;
  public final static int ALGO_NOADJACENT= 3;
  public final static int ALGO_BARNSLEY  = 4;

  public OptionsStruct()
  {
    width  = 1000;
    height = 1000;
    startPoints = 3;
    pixelSize = 1.0f;
    pointsperiter = 10;
    dt = 10;
    algorithm = ALGO_VANILLA;
  }
}
