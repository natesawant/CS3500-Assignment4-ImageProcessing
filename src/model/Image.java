package model;

import java.awt.Color;

/**
 * This interface represents an image, composed of a 2D array of colors.
 */
public interface Image {
  /**
   * Retrieves the pixel at a given location.
   * @param x the x coordinate, from 0 to the max width.
   * @param y the y coordinate, from 0 to the max height.
   * @return the color that is at the specified cell.
   * @throws IllegalArgumentException if the position is not valid.
   */
  public Color getPixel(int x, int y) throws IllegalArgumentException;

  /**
   * Gets the width of the image.
   * @return the width of the image.
   */
  public int getWidth();

  /**
   * Gets the height of the image.
   * @return the height of the image.
   */
  public int getHeight();

  /**
   * Gets the max value of the image.
   * @return the height of the image.
   */
  public int getMaxValue();
}
