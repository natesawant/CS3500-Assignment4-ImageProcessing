package controller;

/**
 * The controller that allows for input to process the images using specific operations.
 */
public interface ImageProcessingController {
  /**
   * Initializes the image processing program. Allows for the user to input commands to perform
   * operations on specific images.
   */
  public void initializeProgram() throws IllegalStateException;
}
