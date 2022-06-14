package processes;

import java.awt.*;

/**
 * A filter that isolates the blue channel to make the image grayscale
 */
public class BlueGrayscale extends ApplyMultiplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public BlueGrayscale(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = new double[][]
            {{0.0, 0.0, 1.0},
                    {0.0, 0.0, 1.0},
                    {0.0, 0.0, 1.0}};

    return filter;
  }
}