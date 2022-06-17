package processes;

import java.awt.Color;

/**
 * Applies a greyscale filter to a given image based off of the red values of each pixel.
 */
public class RedGrayscale extends ApplyMultiplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public RedGrayscale(String name, String destName) {
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