package controller;

import java.util.HashMap;
import java.util.Map;

import model.Image;

public interface ImageProcessingController {
  /**
   * Initializes the image processing program. Allows for the user to input commands to perform
   * operations on specific images.
   */
  public void initializeProgram();
}
