package processes;

import java.awt.Color;

/**
 * Applies a greyscale filter to a given image based on the maximum color value of each pixel.
 */
public class ValueGrayscale extends ApplyAdditionFilter {

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
  public double[] initFilter(Color rgb) {
    int max = Math.max(Math.max(rgb.getRed(), rgb.getGreen()), rgb.getBlue());
    double[] filter = {max - rgb.getRed(),max - rgb.getGreen(),max - rgb.getBlue()};

    return filter;
  }
}