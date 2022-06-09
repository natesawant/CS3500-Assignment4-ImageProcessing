package processes;

import model.OperationsModel;

/**
 * This class represents the vertical flip effect on an image.
 */
public class VerticalFlip implements Process {
  String name;
  String destName;

  /**
   * Constructs an object that will vertically flip a given image.
   * @param name the name of the inputted image.
   * @param destName the name of the image output.
   */
  public VerticalFlip(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.verticalFlip(this.name, this.destName);
  }
}

