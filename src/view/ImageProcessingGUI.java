package view;

import java.awt.Image;

import javax.swing.JMenuItem;

/**
 * This interface represents a GUI view of the image processing program.
 */
public interface ImageProcessingGUI extends ImageProcessingView {

  /**
   * Sets the current program's image focus to a given image.
   * @param img the desired image to add.
   */
  void setImage(Image img);

  /**
   * Adds a given JMenuItem to the 'File' dropdown menu.
   * @param item the desired item to add.
   */
  void addToFileMenu(JMenuItem item);

  /**
   * Adds a given JMenuItem to the 'Image Transform' dropdown menu.
   * @param item the desired item to add.
   */
  void addToImageTransforms(JMenuItem item);

  /**
   * Adds a given JMenuItem to the 'Color Filter' dropdown menu.
   * @param item the desired item to add.
   */
  void addToColorFilters(JMenuItem item);

  /**
   * Adds a given JMenuItem to the 'Image Filter' dropdown menu.
   * @param item the desired item to add.
   */
  void addToImageFilters(JMenuItem item);

}
