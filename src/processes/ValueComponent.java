package processes;

import java.awt.*;

import model.OperationsModel;

/**
 * This class allows the user to create an image based on different values of each pixel.
 */
public class ValueComponent implements Process {
  String value;
  String name;
  String destName;

  /**
   * Constructs an object that will generate a new image based on different pixel values.
   * @param value the desired value to be based off of.
   * @param name the name of the inputted image.
   * @param destName the name of the image output.
   */
  public ValueComponent(String value, String name, String destName) {
    this.value = value;
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.valueComponent(this.value, this.name, this.destName);
  }
}
