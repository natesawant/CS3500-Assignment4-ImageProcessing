package processes;

import model.OperationsModel;

/**
 * This class represents the vertical flip effect on an image.
 */
public class VerticalFlip implements Process {
  private String name;
  private String destName;

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
  public void start(OperationsModel m) {
    m.verticalFlip(this.name, this.destName);
  }
}

