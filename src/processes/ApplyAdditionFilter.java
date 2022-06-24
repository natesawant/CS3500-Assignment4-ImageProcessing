package processes;

import java.awt.Color;

import model.OperationsModel;

/**
 * Applies a 3x3 matrix filter to an image.
 */
public abstract class ApplyAdditionFilter implements Process {
  private String name;
  private String destName;

  /**
   * Constructs a new filter to apply to an image.
   * @param name The name of the inputted image.
   * @param destName The name of the output image.
   */
  public ApplyAdditionFilter(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.applyAdditionFilter(c -> initFilter(c), this.name,
            this.destName);
  }

  /**
   * Generates a new 3x3 filter matrix based on the current pixel.
   * @param rgb the rgb pixel.
   * @return the filter to be applied to the pixel.
   */
  public abstract double[] initFilter(Color rgb);
}
