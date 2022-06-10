package processes;

import java.awt.*;

public class SepiaTone extends ApplyFilter {

  /**
   * Constructs a new filter to apply to an image.
   *
   * @param name     The name of the inputted image.
   * @param destName The name of the output image.
   */
  public SepiaTone(String name, String destName) {
    super(name, destName);
  }

  @Override
  public double[][] initFilter(Color rgb) {
    double[][] filter = new double[][]
            {{0.393, 0.769, 0.189},
                    {0.349, 0.686, 0.168},
                    {0.272, 0.534, 0.131}};

    return filter;
  }
}