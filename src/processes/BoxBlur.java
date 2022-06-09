package processes;

/**
 * This class represents the box blur effect on an image.
 */
public class BoxBlur extends ApplyKernel {

  /**
   * Constructs a Kernel that will apply the box blur effect.
   * @param name The name of the inputted image.
   * @param destName The output image.
   */
  public BoxBlur(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel = new double[][]
            {{1.0/9.0,1.0/9.0,1.0/9.0},
                    {1.0/9.0,1.0/9.0,1.0/9.0},
                    {1.0/9.0,1.0/9.0,1.0/9.0}};
  }
}
