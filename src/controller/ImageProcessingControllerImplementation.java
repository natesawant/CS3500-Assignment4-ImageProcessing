package controller;

import java.io.IOException;
import java.util.Scanner;
import model.OperationsModel;
import model.OperationsModelManager;
import view.ImageProcessingView;

/**
 * This class represents an implementation of a controller for an image processing program.
 */
public final class ImageProcessingControllerImplementation implements ImageProcessingController {

  protected String directory;
  protected Readable readable;
  protected ImageProcessingView view;
  protected OperationsModel manager;

  /**
   * The implementation of the controller that allows for text based input and output.
   * @param directory the root directory that is being worked in.
   * @param view where the controller should output to.
   * @param readable Where the input of the program is coming from.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ImageProcessingControllerImplementation(String directory,
                                                 ImageProcessingView view,
                                                 Readable readable)
          throws IllegalArgumentException {
    if (readable == null) {
      throw new IllegalArgumentException("Readable is null");
    } else if (view == null) {
      throw new IllegalArgumentException("View is null");
    }else {
      this.directory = directory;
      this.readable = readable;
      this.view = view;
      this.manager = new OperationsModelManager();
    }
  }

  /**
   * The implementation of the controller that allows for text based input and output.
   * @param directory the root directory that is being worked in.
   * @param view where the controller should output to.
   * @param model the model that this controller should control.
   * @param readable Where the input of the program is coming from.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ImageProcessingControllerImplementation(String directory,
                                                 ImageProcessingView view,
                                                 OperationsModel model,
                                                 Readable readable)
          throws IllegalArgumentException {
    if (view == null || model == null || readable == null) {
      throw new IllegalArgumentException("Arguments cannot be null!");
    }
    this.directory = directory;
    this.view = view;
    this.manager = model;
    this.readable = readable;
  }

  @Override
  public void initializeProgram() throws IllegalStateException {
    Scanner scan;
    String rawInput;
    String[] arguments;
    try {
      scan = new Scanner(readable);

      while (true) {
        view.renderMessage("Enter operation." + System.lineSeparator());

        rawInput = scan.nextLine();
        arguments = rawInput.split(" ");

        if (arguments.length > 0) {
          switch (arguments[0].toLowerCase()) {
            case "load":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.load(directory + arguments[1], arguments[2]);
                  view.renderMessage("Successfully loaded image." + System.lineSeparator());
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "brighten":
              if (validNumArguments(4, arguments.length)) {
                try {
                  manager.brighten(Integer.parseInt(arguments[1]), arguments[2], arguments[3]);
                  view.renderMessage("Successfully brightened image." + System.lineSeparator());
                } catch (NumberFormatException ex) {
                  view.renderMessage(ex.getMessage() + System.lineSeparator());
                } catch (IllegalArgumentException ex) {
                  view.renderMessage(ex.getMessage() + System.lineSeparator());
                }
              }
              break;
            case "blur":
              if (validNumArguments(4, arguments.length)) {
                try {
                  manager.boxBlur(Integer.parseInt(arguments[1]), arguments[2], arguments[3]);
                  view.renderMessage("Successfully brightened image." + System.lineSeparator());
                } catch (NumberFormatException ex) {
                  view.renderMessage(ex.getMessage() + System.lineSeparator());
                } catch (IllegalArgumentException ex) {
                  view.renderMessage(ex.getMessage() + System.lineSeparator());
                }
              }
              break;
            case "vertical-flip":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.verticalFlip(arguments[1], arguments[2]);
                  view.renderMessage("Successfully flipped image vertically.\n");
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "horizontal-flip":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.horizontalFlip(arguments[1], arguments[2]);
                  view.renderMessage("Successfully flipped image horizontally.\n");
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "value-component":
              try {
                if (validNumArguments(4, arguments.length)) {
                  manager.valueComponent(arguments[1], arguments[2], arguments[3]);
                  view.renderMessage("Successfully converted to component greyscale.\n");
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "save":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.save(directory + arguments[1], arguments[2]);
                  view.renderMessage("Successfully saved image." + System.lineSeparator());
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "q":
            case "quit":
              view.renderMessage("Quitting program." + System.lineSeparator());
              return;
            default:
              view.renderMessage("Non-valid operation." + System.lineSeparator());
              break;
          }
        }
      }
    } catch (Exception ex) {
      throw new IllegalStateException("Cannot read or write. " + ex.getMessage() + "\n");
    }
  }

  /**
   * Ensures that the number of inputs is the expected amount, shows a message if not.
   * @param exp the expected number of arguments.
   * @param act the actual number of arguments.
   * @return if the expected is equal to the actual.
   * @throws IllegalStateException if unable to transmit the message.
   */
  private boolean validNumArguments(int exp, int act) throws IllegalStateException {
    if (exp != act) {
      try {
        view.renderMessage("Must be only " + exp + " arguments. Actual: " + act + "\n");
        return false;
      } catch (IOException ex) {
        throw new IllegalStateException(ex.getMessage() + System.lineSeparator());
      }
    } else {
      return true;
    }
  }
}
