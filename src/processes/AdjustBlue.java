package processes;

import java.awt.*;

import model.OperationsModel;

/**
 * This class represents the brighten effect on an image.
 */
public class AdjustBlue extends ApplyFilter {

  int increment;
  String name;
  String destName;

  /**
   * Constructs a Kernel that will apply the brightening effect.
   * @param increment the desired brightness delta.
   * @param name the name of the inputted image.
   * @param destName the name of the output image.
   */
  public AdjustBlue(int increment, String name, String destName) {
    super(name, destName);
    this.increment = increment;
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = new double[][]
            {{1, 0, 0},
                    {0, 1, 0},
                    {0, 0, (double) (rgb.getBlue() + increment) / rgb.getBlue()}};

    return filter;
  }
}
