package processes;

import java.awt.*;

public class Red extends ApplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public Red(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = new double[][]
            {{1.0, 0.0, 0.0},
                    {1.0, 0.0, 0.0},
                    {1.0, 0.0, 0.0}};

    return filter;
  }
}