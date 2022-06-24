package processes;

import model.OperationsModel;

/**
 * This class applies a given kernel matrix to an image.
 */
public abstract class ApplyKernel implements Process {
  protected double[][] kernel;
  private String name;
  private String destName;

  /**
   * Constructs a new kernel to apply to an image.
   * @param name The name of the inputted image.
   * @param destName The name of the output image.
   */
  public ApplyKernel(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    initKernel();
    m.applyKernel(this.kernel, this.name, this.destName);
  }

  /**
   * Sets the kernel matrix for the desired operation.
   */
  public abstract void initKernel();
}
