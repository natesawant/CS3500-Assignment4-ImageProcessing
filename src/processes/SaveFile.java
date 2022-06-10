package processes;

import model.OperationsModel;

/**
 * This class allows the user to save an image into the program's filesystem.
 */
public class SaveFile implements Process {
  String path;
  String destName;

  /**
   * Constructs an object that will save a desired image.
   * @param path the path of the desired image to save.
   * @param destName the new name of the image.
   */
  public SaveFile(String path, String destName) {
    this.path = path;
    this.destName = destName;
  }

  @Override
  public void start(OperationsModel m) {
    m.save(this.path, this.destName);
  }
}