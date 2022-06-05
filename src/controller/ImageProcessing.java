package controller;

import java.io.InputStreamReader;

import util.ImageUtil;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

public class ImageProcessing {
  /**
   * Main method to start the ImageProcessing program.
   * @param args
   */
  public static void main(String[] args) {
    ImageProcessingController controller;
    Readable readable;
    Appendable appendable;
    String directory;
    ImageProcessingView view;

    readable = new InputStreamReader(System.in);
    appendable = System.out;
    view = new ImageProcessingTextView(System.out);

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
