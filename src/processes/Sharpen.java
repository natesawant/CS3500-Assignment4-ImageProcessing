package processes;

/**
 * This class allows the user to apply a sharpen filter to an image.
 */
public class Sharpen extends ApplyKernel {

  /**
   * Constructs a kernel that will create an image with the sharpen filter applied.
   * @param name the name of the source image.
   * @param destName the name of the image output.
   */
  public Sharpen(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel =
            new double[][]{{ 0,-1, 0},
                    {-1, 5,-1},
                    { 0,-1, 0}};
  }
}
