package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.OperationsModel;
import model.OperationsModelManager;
import processes.BoxBlur;
import processes.Brighten;
import processes.Emboss;
import processes.GaussianBlur;
import processes.HorizontalFlip;
import processes.InvertColors;
import processes.LoadFile;
import processes.Process;
import processes.RidgeDetection;
import processes.SaveFile;
import processes.SepiaTone;
import processes.Sharpen;
import processes.ValueComponent;
import processes.VerticalFlip;
import view.ImageProcessingView;

/**
 * This class represents an implementation of a controller for an image processing program.
 */
public final class ImageProcessingControllerImplementation implements ImageProcessingController {

  protected String directory;
  protected Readable readable;
  protected ImageProcessingView view;
  protected OperationsModel manager;

  protected Map<String, Function<Scanner, Process>> validCommands;

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
    } else {
      this.directory = directory;
      this.readable = readable;
      this.view = view;
      this.manager = new OperationsModelManager();
      initValidCommands();
    }
  }

  private void initValidCommands() {
    validCommands = new HashMap<String, Function<Scanner, Process>>();

    validCommands.put("load", s -> new LoadFile(directory + "/" + s.next(), s.next()));
    validCommands.put("save", s -> new SaveFile(directory + "/" + s.next(), s.next()));
    validCommands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
    validCommands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
    validCommands.put("brighten", s -> new Brighten(s.nextInt(), s.next(), s.next()));
    validCommands.put("value-component", s -> new ValueComponent(s.next(), s.next(), s.next()));
    validCommands.put("box-blur", s -> new BoxBlur(s.next(), s.next()));
    validCommands.put("emboss", s -> new Emboss(s.next(), s.next()));
    validCommands.put("gaussian-blur", s -> new GaussianBlur(s.next(), s.next()));
    validCommands.put("ridge-detection", s -> new RidgeDetection(s.next(), s.next()));
    validCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    validCommands.put("sepia-tone", s -> new SepiaTone(s.next(), s.next()));
    validCommands.put("invert", s -> new InvertColors(s.next(), s.next()));
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
    initValidCommands();
  }

  @Override
  public void initializeProgram() throws IllegalStateException {
    Scanner scan;
    String rawInput;
    String[] arguments;
    try {
      scan = new Scanner(readable);

      while (true) {
        view.renderMessage("Enter operation or 'help' to see commands." + System.lineSeparator());

        while (scan.hasNext()) {
          Process process;
          String in = scan.next();
          if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
            view.renderMessage("Quitting program." + System.lineSeparator());
            return;
          } else if (in.equalsIgnoreCase("help")) {
            for (String key : validCommands.keySet()) {
              view.renderMessage(key + System.lineSeparator());
            }
          } else {
            Function<Scanner, Process> cmd =
                    validCommands.getOrDefault(in, null);
            if (cmd == null) {
              view.renderMessage("Invalid command." + System.lineSeparator());
            } else {
              view.renderMessage("Processing..." + System.lineSeparator());
              process = cmd.apply(scan);
              try {
                long deltaTime = System.currentTimeMillis();
                process.start(manager);
                view.renderMessage("Process complete." + System.lineSeparator());
                deltaTime = System.currentTimeMillis() - deltaTime;
                view.renderMessage("Took " + deltaTime + " milliseconds." + System.lineSeparator());
              } catch (IllegalArgumentException ex) {
                view.renderMessage("Error: " + ex.getMessage() + System.lineSeparator());
              }
            }
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
