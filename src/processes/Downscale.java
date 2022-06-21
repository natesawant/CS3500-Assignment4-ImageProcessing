package processes;

import model.OperationsModel;

/**
 * This class represents an object that can downscale an image by a given increment.
 */
public class Downscale implements Process {
  String path;
  String destName;
  int toWidth;
  int toHeight;

  /**
   * Constructs an object that will downscale a given image.
   * @param toWidth the new width of the image.
   * @param toHeight the new height of the image.
   * @param path the path of the desired image to save.
   * @param destName the new name of the image.
   */
  public Downscale(int toWidth, int toHeight, String path, String destName) {
    this.path = path;
    this.destName = destName;
    this.toWidth = toWidth;
    this.toHeight = toHeight;
  }

  @Override
  public void start(OperationsModel m) {
    m.applyDownscaling(this.toWidth, this.toHeight, path, destName);
  }
}
