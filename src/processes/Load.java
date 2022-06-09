package processes;

import model.OperationsModel;

/**
 * This class allows the user to load an image into the program.
 */
public class Load implements Process {
  String path;
  String destName;

  /**
   * Constructs an object that will load a desired image.
   * @param path the path of the desired image to load.
   * @param destName the new, internal name of the image.
   */
  public Load(String path, String destName) {
    this.path = path;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.load(this.path, this.destName);
  }
}