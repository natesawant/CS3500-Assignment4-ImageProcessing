import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JFrame;

import controller.ImageProcessingGUIController;

/**
 * Mocks how the controller sends information to the view, by sending the actions to a log to be
 * queried and tested.
 */
public class GUIControllerMock implements ImageProcessingGUIController {
  private Appendable log;
  private Map<String, Function<JFrame, String>> programCommands;

  /**
   * Constructs a new mock controller.
   * @param log the log to append to.
   */
  public GUIControllerMock(Appendable log) {
    this.log = log;
  }

  @Override
  public void initializeProgram() throws IllegalStateException {
    programCommands = new HashMap<>();
    // Program commands
    programCommands.put("Open", v -> "Open");
    programCommands.put("Save", v -> "Save");
    programCommands.put("Save As", v -> "Save As");

    // Image Transforms
    programCommands.put("Flip (Horizontal)", v -> "Flip (Horizontal)");
    programCommands.put("Flip (Vertical)", v -> "Flip (Vertical)");
    programCommands.put("Downscale", v -> "Downscale");

    // Color Filters
    programCommands.put("Adjust Red",
            v -> "Adjust Red");
    programCommands.put("Adjust Green",
            v -> "Adjust Green");
    programCommands.put("Adjust Blue",
            v -> "Adjust Blue");
    programCommands.put("Adjust Brightness",
            v -> "Adjust Brightness");
    programCommands.put("Invert Colors",
            v -> "Invert Colors");
    programCommands.put("Sepia Tone",
            v -> "Sepia Tone");
    programCommands.put("Greyscale (Red)",
            v -> "Greyscale (Red)");
    programCommands.put("Greyscale (Green)",
            v -> "Greyscale (Green)");
    programCommands.put("Greyscale (Blue)",
            v -> "Greyscale (Blue)");
    programCommands.put("Greyscale (Value)",
            v -> "Greyscale (Value)");
    programCommands.put("Greyscale (Intensity)",
            v -> "Greyscale (Intensity)");
    programCommands.put("Greyscale (Luma)",
            v -> "Greyscale (Luma)");

    // Image Filters
    programCommands.put("Box Blur", v -> "Box Blur");
    programCommands.put("Gaussian Blur", v -> "Gaussian Blur");
    programCommands.put("Emboss", v -> "Emboss");
    programCommands.put("Sharpen", v -> "Sharpen");
    programCommands.put("Ridge Detection", v -> "Ridge Detection");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      log.append("Action performed: ").append(e.getActionCommand());
    } catch (IOException s) {
      //
    }
  }
}
