package processes;

import model.OperationsModel;

/**
 * This class represents the horizontal flip effect on an image.
 */
public class HorizontalFlip implements Process {
  private String name;
  private String destName;

  /**
   * Constructs an object that will flip a given image.
   * @param name the name of the inputted image.
   * @param destName the name of the image output.
   */
  public HorizontalFlip(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.horizontalFlip(this.name, this.destName);
  }
}
