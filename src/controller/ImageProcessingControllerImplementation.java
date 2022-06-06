package controller;

import java.io.IOException;
import java.util.Scanner;
import model.OperationsModel;
import model.OperationsModelManager;
import view.ImageProcessingView;

public final class ImageProcessingControllerImplementation implements ImageProcessingController {

  protected String directory;
  protected Readable readable;
  protected ImageProcessingView view;
  protected OperationsModel manager;
  public ImageProcessingControllerImplementation(String directory, ImageProcessingView view,
                                                 Readable readable) throws IllegalArgumentException {
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

  @Override
  public void initializeProgram() {
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
                  manager.load(arguments[1], arguments[2]);
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
                  manager.blur(Integer.parseInt(arguments[1]), arguments[2], arguments[3]);
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
                  view.renderMessage("Successfully flipped image vertically." + System.lineSeparator());
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "horizontal-flip":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.horizontalFlip(arguments[1], arguments[2]);
                  view.renderMessage("Successfully flipped image horizontally." + System.lineSeparator());
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "value-component":
              try {
                if (validNumArguments(4, arguments.length)) {
                  manager.valueComponent(arguments[1], arguments[2], arguments[3]);
                  view.renderMessage("Successfully converted to component greyscale." + System.lineSeparator());
                }
              } catch (IllegalArgumentException ex) {
                view.renderMessage(ex.getMessage() + System.lineSeparator());
              }
              break;
            case "save":
              try {
                if (validNumArguments(3, arguments.length)) {
                  manager.save(arguments[1], arguments[2]);
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
      throw new IllegalStateException("Cannot read or write. " + ex.getMessage() + System.lineSeparator());
    }
  }

  private boolean validNumArguments(int exp, int act) {
    if (exp != act) {
      try {
        view.renderMessage("Must be only " + exp + " arguments. Actual: " + act + System.lineSeparator());
        return false;
      } catch (IOException ex) {
        throw new IllegalStateException(ex.getMessage() + System.lineSeparator());
      }
    } else {
      return true;
    }
  }
}
