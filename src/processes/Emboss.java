package processes;

/**
 * This class represents the embossing effect on an image.
 */
public class Emboss extends ApplyKernel {

  /**
   * Constructs a kernel that will emboss an image.
   * @param name the name of the inputted image.
   * @param destName the name of the image output.
   */
  public Emboss(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel = new double[][]
            {{-2,-1, 0},
                    {-1, 1, 1},
                    { 0, 1, 2}};
  }
}
