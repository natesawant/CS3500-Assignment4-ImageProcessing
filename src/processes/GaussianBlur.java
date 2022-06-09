package processes;

/**
 * This class represents the gaussian blur effect on an image.
 */
public class GaussianBlur extends ApplyKernel {

  /**
   * Constructs a kernel that will perform a gaussian blur on an image.
   *
   * @param name     the name of the inputted image.
   * @param destName the name of the image output.
   */
  public GaussianBlur(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel = new double[][]
        {{1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0},
                {2.0 / 16.0, 4.0 / 16.0, 2.0 / 16.0},
                {1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0}};
  }
}
