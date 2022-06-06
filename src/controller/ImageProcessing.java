package controller;

import java.io.InputStreamReader;

import view.ImageProcessingTextView;
import view.ImageProcessingView;

/**
 * Class that allows for image processing through text input.
 */
public class ImageProcessing {
  /**
   * Main method to start the ImageProcessing program.
   * @param args optional argument to set the directory that the root is.
   */
  public static void main(String[] args) {
    ImageProcessingController controller;
    Readable readable;
    Appendable appendable;
    String directory;
    ImageProcessingView view;

    readable = new InputStreamReader(System.in);
    appendable = System.out;
    view = new ImageProcessingTextView(appendable);

    if (args.length>0) {
      directory = args[0];
    }
    else {
      directory = "";
    }

    controller = new ImageProcessingControllerImplementation(directory, view, readable);
    controller.initializeProgram();
  }
}
