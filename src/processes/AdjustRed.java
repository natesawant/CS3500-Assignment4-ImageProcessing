package processes;

import java.awt.*;

/**
 * This class represents the brighten effect on an image.
 */
public class AdjustRed extends ApplyAdditionFilter {

  int increment;
  String name;
  String destName;

  /**
   * Constructs a Kernel that will apply the brightening effect.
   * @param increment the desired brightness delta.
   * @param name the name of the inputted image.
   * @param destName the name of the output image.
   */
  public AdjustRed(int increment, String name, String destName) {
    super(name, destName);
    this.increment = increment;
  }

  @Override
  public double[] initFilter(Color rgb) {
    double[] filter = new double[]
            {increment, 0, 0};

    return filter;
  }
}
