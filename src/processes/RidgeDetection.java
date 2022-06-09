package processes;

/**
 * This class represents the ridge detection effect on an image.
 */
public class RidgeDetection extends ApplyKernel {

  /**
   * Constructs a kernel that will create an image with ridge detection applied.
   *
   * @param name     the name of the source image.
   * @param destName the name of the image output.
   */
  public RidgeDetection(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel =
            new double[][]
            {{-1, -1, -1},
              {-1, 8, -1},
              {-1, -1, -1}};
  }
}
