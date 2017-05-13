public class OptionsStruct
{
  public int   width;
  public int   height;
  public int   startPoints;
  public float pixelSize;
  public int   pointsperiter;
  public int   dt;

  public OptionsStruct()
  {
    width  = 1000;
    height = 1000;
    startPoints = 3;
    pixelSize = 1.0f;
    pointsperiter = 10;
    dt = 10;
  }
}
