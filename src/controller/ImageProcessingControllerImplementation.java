package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Image;
import util.ImageUtil;
import view.ImageProcessingView;

public final class ImageProcessingControllerImplementation implements ImageProcessingController {
  protected Map<String, Image> loaded = new HashMap<String, Image>();
  protected String directory;
  protected Readable readable;
  protected ImageProcessingView view;
  public ImageProcessingControllerImplementation(String directory,
                                                 ImageProcessingView view, Readable readable) throws IllegalArgumentException {
    if (readable == null) {
      throw new IllegalArgumentException("Readable is null");
    } else if (view == null) {
      throw new IllegalArgumentException("View is null");
    }else {
      this.directory = directory;
      this.readable = readable;
      this.view = view;
    }
  }

  @Override
  public void initializeProgram() {
    Scanner scan;
    String rawInput = "";
    String[] arguments;
    try {
      scan = new Scanner(readable);

      while (true) {
        rawInput = scan.nextLine();
        arguments = rawInput.split(" ");

        if (arguments.length > 0) {
          switch (arguments[0]) {
            case "load":
              if (validNumArguments(3, arguments.length)) {
                load(arguments[1], arguments[2]);
              }
              break;
            case "brighten":
              if (validNumArguments(4, arguments.length)) {
                //brighten(arguments[1], arguments[2], arguments[3]);
              }
              break;
            case "vertical-flip":
              if (validNumArguments(3, arguments.length)) {
                //vertical-flip(arguments[1], arguments[2]);
              }
              break;
            case "horizontal-flip":
              if (validNumArguments(3, arguments.length)) {
                //horizontal-flip(arguments[1], arguments[2]);
              }
              break;
            case "value-component":
              // value-component component fromName toName
              if (validNumArguments(4, arguments.length)) {
                //value-component(arguments[1], arguments[2], arguments[3]);
              }
              break;
            case "save":
              if (validNumArguments(3, arguments.length)) {
                //save(arguments[1], arguments[2]);
              }
              break;
            default:
              view.renderMessage("Non-valid operation.");
              break;
          }
        }
      }
    } catch (Exception ex) {
      throw new IllegalStateException("Cannot read or write.");
    }
  }

  private void load(String filepath, String name) {
    try {
      try {
        Image img = ImageUtil.convertPPM(filepath);
        loaded.put(name, img);
        view.renderMessage("Image successfully loaded as: " + name);
      } catch (IllegalArgumentException ex) {
        view.renderMessage("Filepath not valid.");
      }
    } catch (IOException ex) {
      throw new IllegalStateException(ex.getMessage());
    }
  }

  private boolean validNumArguments(int exp, int act) {
    if (exp != act) {
      try {
        view.renderMessage("Must be only " + exp + " arguments. Actual: " + act);
        return false;
      } catch (IOException ex) {
        throw new IllegalStateException(ex.getMessage());
      }
    } else {
      return true;
    }
  }
}
