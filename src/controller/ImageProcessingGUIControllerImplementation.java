package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import model.OperationsModel;
import model.OperationsModelManager;
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
import processes.Process;
import processes.RedGrayscale;
import processes.RidgeDetection;
import processes.SaveFile;
import processes.SepiaTone;
import processes.Sharpen;
import processes.ValueGrayscale;
import processes.VerticalFlip;
import view.ImageProcessingGUIView;

/**
 * Represents the controller of a GUI instance of this image processing program.
 */
public class ImageProcessingGUIControllerImplementation implements ImageProcessingGUIController {
  private ImageProcessingGUIView view;
  private OperationsModel manager;
  private String saveName;
  private String extension;
  private Map<String, Function<JFrame, Process>> programCommands;

  /**
   * Constructs a new controller for a GUI application.
   */
  public ImageProcessingGUIControllerImplementation() {

    manager = new OperationsModelManager();
    view = new ImageProcessingGUIView();
    saveName = "saved";
    extension = ".png";
    view.setListener(this);

  }

  @Override
  public void initializeProgram() throws IllegalStateException {

    programCommands = new HashMap<>();
    // Program commands
    programCommands.put("Open", v -> new LoadFile(
            JOptionPane.showInputDialog(v, "Enter filename."), saveName));
    programCommands.put("Save", v -> new SaveFile(saveName + extension,
            saveName));
    programCommands.put("Save As", v -> new SaveFile(JOptionPane.showInputDialog(v, "Enter " +
            "savename."),
            saveName));

    // Image Transforms
    programCommands.put("Flip (Horizontal)", v -> new HorizontalFlip(saveName, saveName));
    programCommands.put("Flip (Vertical)", v -> new VerticalFlip(saveName, saveName));
    programCommands.put("Downscale", v -> new Downscale(
            Integer.parseInt(JOptionPane.showInputDialog(v, "Width")),
            Integer.parseInt(JOptionPane.showInputDialog(v, "Height")), saveName, saveName));

    // Color Filters
    programCommands.put("Adjust Red",
        v -> new AdjustRed(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                saveName, saveName));
    programCommands.put("Adjust Green",
        v -> new AdjustGreen(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                saveName, saveName));
    programCommands.put("Adjust Blue",
        v -> new AdjustBlue(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                saveName, saveName));
    programCommands.put("Adjust Brightness",
        v -> new Brighten(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                saveName, saveName));
    programCommands.put("Invert Colors",
        v -> new InvertColors(saveName, saveName));
    programCommands.put("Sepia Tone",
        v -> new SepiaTone(saveName, saveName));
    programCommands.put("Greyscale (Red)",
        v -> new RedGrayscale(saveName, saveName));
    programCommands.put("Greyscale (Green)",
        v -> new GreenGrayscale(saveName, saveName));
    programCommands.put("Greyscale (Blue)",
        v -> new BlueGrayscale(saveName, saveName));
    programCommands.put("Greyscale (Value)",
        v -> new ValueGrayscale(saveName, saveName));
    programCommands.put("Greyscale (Intensity)",
        v -> new IntensityGrayscale(saveName, saveName));
    programCommands.put("Greyscale (Luma)",
        v -> new LumaGrayscale(saveName, saveName));

    // Image Filters
    programCommands.put("Box Blur", v -> new BoxBlur(saveName, saveName));
    programCommands.put("Gaussian Blur", v -> new GaussianBlur(saveName, saveName));
    programCommands.put("Emboss", v -> new Emboss(saveName, saveName));
    programCommands.put("Sharpen", v -> new Sharpen(saveName, saveName));
    programCommands.put("Ridge Detection", v -> new RidgeDetection(saveName, saveName));

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Process p = programCommands.get(e.getActionCommand()).apply(view);
      p.start(manager);
      p = new SaveFile(saveName + "-" + "temp" + extension, saveName);
      p.start(manager);
      view.setImage(saveName + "-" + "temp" + extension);
    } catch (Exception ex) {
      try {
        view.renderMessage("Error: " + ex.getMessage());
        for (StackTraceElement element : ex.getStackTrace()) {
          System.out.println(element.toString());
        }
      } catch (IOException exception) {
        throw new RuntimeException(exception);
      }
    }
  }
}
