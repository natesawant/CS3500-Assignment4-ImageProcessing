package processes;

import java.awt.Color;

/**
 * Applies a greyscale filter to a given image based off of the luma values of each pixel.
 */
public class LumaGrayscale extends ApplyMultiplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public LumaGrayscale(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = new double[][]
        {{0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}};

    return filter;
  }
}