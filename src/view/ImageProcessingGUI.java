package view;

import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This interface represents a GUI view of the image processing program.
 */
public interface ImageProcessingGUI extends ImageProcessingView {

  /**
   * Sets the current program's image focus to a given image.
   * @param filename the desired image to add.
   */
  void setImage(String filename);

  void setListener(ActionListener listener);
}
