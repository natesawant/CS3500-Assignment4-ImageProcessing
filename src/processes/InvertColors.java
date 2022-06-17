package processes;

import java.awt.Color;

/**
 * Inverts the colors of an image. Inverts by subtracting from the max value.
 */
public class InvertColors extends ApplyMultiplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public InvertColors(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = {{(double) (255 - rgb.getRed()) / rgb.getRed(), 0, 0},
      {0, (double) (255 - rgb.getGreen()) / rgb.getGreen(), 0},
      {0,0,(double) (255 - rgb.getBlue()) / rgb.getBlue()}};

    return filter;
  }
}