import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.*;

import controller.ImageGUI;
import processes.AdjustBlue;
import processes.AdjustGreen;
import processes.AdjustRed;
import processes.BlueGrayscale;
import processes.BoxBlur;
import processes.Brighten;
import processes.Downscale;
import processes.Emboss;
import processes.GaussianBlur;
import processes.GreenGrayscale;
import processes.HorizontalFlip;
import processes.IntensityGrayscale;
import processes.InvertColors;
import processes.LoadFile;
import processes.LumaGrayscale;
import processes.RedGrayscale;
import processes.RidgeDetection;
import processes.SaveFile;
import processes.SepiaTone;
import processes.Sharpen;
import processes.ValueGrayscale;
import processes.VerticalFlip;
import view.ImageProcessingGUI;

public class GUIControllerMock implements ImageGUI {
  StringBuilder log;
  private Map<String, Function<JFrame, String>> programCommands;

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
    Process p = programCommands.get(e.getActionCommand()).apply();
    log.append("Action performed: ").append(e.getActionCommand());
  }
}
