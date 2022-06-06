package model;

/**
 *
 */
public interface OperationsModel {
  /**
   * Load an image from the specified path and refer it to henceforth in the program by the
   * given image name.
   * @param path the filepath to the image.
   * @param name the name that will be used to refer to the image.
   * @throws IllegalArgumentException if the filepath is not valid.
   */
  void load(String path, String name) throws IllegalArgumentException;

  /**
   * Save the image with the given name to the specified path which should include the name of
   * the file.
   * @param path the filepath to the image.
   * @param name the name of the image that will be saved.
   * @throws IllegalArgumentException if name of the image is not valid.
   */
  void save(String path, String name) throws IllegalArgumentException;

  /**
   * Create a greyscale image with the red-component of the image with the given name, and refer
   * to it henceforth in the program by the given destination name. Similar commands for green,
   * blue, value, luma, intensity components should be supported.
   * @param component the component that will be used for the greyscale.
   * @param name the name of the image that processed.
   * @param destName the new name of the processed image.
   * @throws IllegalArgumentException if name of the image or component is not valid.
   */
  void valueComponent(String component, String name, String destName) throws IllegalArgumentException;

  /**
   * Flip an image horizontally to create a new image, referred to henceforth by the given
   * destination name.
   * @param name the name of the image that processed.
   * @param destName the new name of the processed image.
   * @throws IllegalArgumentException if name of the image is not valid.
   */
  void horizontalFlip(String name, String destName) throws IllegalArgumentException;

  /**
   * Flip an image vertically to create a new image, referred to henceforth by the given
   * destination name.
   * @param name the name of the image that processed.
   * @param destName the new name of the processed image.
   * @throws IllegalArgumentException if name of the image is not valid.
   */
  void verticalFlip(String name, String destName) throws IllegalArgumentException;

  /**
   * brighten the image by the given increment to create a new image, referred to henceforth be
   * the given destination name. May be positive (brightening) or negative (darkening).
   * @param increment the amount to brighten or darken by.
   * @param name the name of the image that processed.
   * @param destName the new name of the processed image.
   * @throws IllegalArgumentException if name of the image is not valid.
   */
  void brighten(int increment, String name, String destName) throws IllegalArgumentException;

  /**
   * Blurs the image by sampling and average the nearby pixels.
   * @param radius the number of pixels in each direction it samples.
   * @param name the name of the image that processed.
   * @param destName the new name of the processed image.
   * @throws IllegalArgumentException if name of the image is not valid.
   */
  void blur(int radius, String name, String destName) throws IllegalArgumentException;
}