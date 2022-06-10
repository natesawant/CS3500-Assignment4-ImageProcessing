package processes;

import java.awt.*;

import model.OperationsModel;

public abstract class ApplyFilter implements Process {
  String name;
  String destName;

  /**
   * Constructs a new filter to apply to an image.
   * @param name The name of the inputted image.
   * @param destName The name of the output image.
   */
  public ApplyFilter(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.applyFilter(c -> initFilter(c), this.name,
            this.destName);
  }

  public abstract double[][] initFilter(Color rgb);
}
