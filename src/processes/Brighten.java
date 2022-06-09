package processes;

import model.OperationsModel;

/**
 * This class represents the brighten effect on an image.
 */
public class Brighten implements Process {

  int increment;
  String name;
  String destName;

  /**
   * Constructs a Kernel that will apply the brightening effect.
   * @param increment the desired brightness delta.
   * @param name the name of the inputted image.
   * @param destName the name of the output image.
   */
  public Brighten(int increment, String name, String destName) {
    this.increment = increment;
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.brighten(this.increment, this.name, this.destName);
  }
}
