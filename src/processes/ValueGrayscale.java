package processes;

import java.awt.*;

public class ValueGrayscale extends ApplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public ValueGrayscale(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    int max = Math.max(Math.max(rgb.getRed(), rgb.getGreen()), rgb.getBlue());
    double[][] filter = new double[3][3];
    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter[0].length; j++) {
        filter[i][j] = max;
      }
    }

    return filter;
  }
}