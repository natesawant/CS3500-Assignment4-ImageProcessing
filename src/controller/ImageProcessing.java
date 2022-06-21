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
    view = new ImageProcessingTextView(new OperationsModelManager(), appendable);

    if (args.length == 1) {

      directory = args[0];
    } else if ((args.length == 2) && (args[0].equals("-file"))) {
      directory = "../res/";
      String location = args[1];
      try {
        readable = new FileReader(location);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }
    } else if (args.length == 3) {
      directory = args[0];
      String location = args[1];
      try {
        readable = new FileReader(location);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }
    } else {
      directory = "";
    }
    controller = new ImageProcessingControllerImplementation(directory, view, readable);


    if (args.length == 1 && args[0].equals("-gui")) {
      controller = new ImageProcessingGUIController();
    }

    controller.initializeProgram();
  }
}
