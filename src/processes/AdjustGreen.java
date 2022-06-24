package processes;

import java.awt.Color;

/**
 * This class represents the brighten effect on an image.
 */
public class AdjustGreen extends ApplyAdditionFilter {

  private int increment;
  private String name;
  private String destName;

  /**
   * Constructs a Kernel that will apply the brightening effect.
   * @param increment the desired brightness delta.
   * @param name the name of the inputted image.
   * @param destName the name of the output image.
   */
  public AdjustGreen(int increment, String name, String destName) {
    super(name, destName);
    this.increment = increment;
  }

  @Override
  public double[] initFilter(Color rgb) {
    double[] filter = new double[]{0, increment, 0};

    return filter;
  }
}
