package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import model.OperationsModelManager;
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

    if (args.length == 1 && args[0].equals("-text")) {
      // user wants text view
      directory = "";
      view = new ImageProcessingTextView(new OperationsModelManager(), appendable);
      controller = new ImageProcessingControllerImplementation(directory, view, readable);

    } else if (args.length == 2 && args[0].equals("-file")) {

      directory = "../res/";
      String location = args[1];
      try {
        readable = new FileReader(location);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }
      view = new ImageProcessingTextView(new OperationsModelManager(), appendable);
      controller = new ImageProcessingControllerImplementation(directory, view, readable);

    } else {
      controller = new ImageProcessingGUIControllerImplementation();
    }

    controller.initializeProgram();
  }
}
