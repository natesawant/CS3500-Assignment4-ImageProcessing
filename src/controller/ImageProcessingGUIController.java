package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.*;

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

public class ImageProcessingGUIController implements ImageProcessingController {
  private ImageProcessingGUIView view;
  private OperationsModel manager;
  private String saveName;
  private String extension;

  public ImageProcessingGUIController() {
    saveName = "saved";
    extension = ".png";
  }

  @Override
  public void initializeProgram() throws IllegalStateException {
    manager = new OperationsModelManager();
    view = new ImageProcessingGUIView();

    Map<String, Function<JFrame, Process>> programCommands = new HashMap<>();
    // Program commands
    programCommands.put("Open", v -> new LoadFile(JOptionPane.showInputDialog(v, "Enter filename."), saveName));
    programCommands.put("Save", v -> new SaveFile(saveName + extension,
            saveName));
    programCommands.put("Save As", v -> new SaveFile(JOptionPane.showInputDialog(v, "Enter " +
            "savename."),
            saveName));

    Map<String, Function<JFrame, Process>> imageTransforms = new HashMap<>();
    // Image Transforms
    imageTransforms.put("Flip (Horizontal)", v -> new HorizontalFlip(saveName, saveName));
    imageTransforms.put("Flip (Vertical)", v -> new VerticalFlip(saveName, saveName));
    imageTransforms.put("Downscale", v -> new Downscale(Integer.parseInt(JOptionPane.showInputDialog(v, "Width")), Integer.parseInt(JOptionPane.showInputDialog(v, "Height")), saveName, saveName));

    Map<String, Function<JFrame, Process>> colorFilters = new HashMap<>();
    // Color Filters
    colorFilters.put("Adjust Red",
            v -> new AdjustRed(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                    saveName, saveName));
    colorFilters.put("Adjust Green",
            v -> new AdjustGreen(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                    saveName, saveName));
    colorFilters.put("Adjust Blue",
            v -> new AdjustBlue(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                    saveName, saveName));
    colorFilters.put("Adjust Brightness",
            v -> new Brighten(Integer.parseInt(JOptionPane.showInputDialog(v, "Increment")),
                    saveName, saveName));
    colorFilters.put("Invert Colors",
            v -> new InvertColors(saveName, saveName));
    colorFilters.put("Sepia Tone",
            v -> new SepiaTone(saveName, saveName));
    colorFilters.put("Greyscale (Red)",
            v -> new RedGrayscale(saveName, saveName));
    colorFilters.put("Greyscale (Green)",
            v -> new GreenGrayscale(saveName, saveName));
    colorFilters.put("Greyscale (Blue)",
            v -> new BlueGrayscale(saveName, saveName));
    colorFilters.put("Greyscale (Value)",
            v -> new ValueGrayscale(saveName, saveName));
    colorFilters.put("Greyscale (Intensity)",
            v -> new IntensityGrayscale(saveName, saveName));
    colorFilters.put("Greyscale (Luma)",
            v -> new LumaGrayscale(saveName, saveName));

    Map<String, Function<JFrame, Process>> imageFilters = new HashMap<>();
    // Image Filters
    imageFilters.put("Box Blur", v -> new BoxBlur(saveName, saveName));
    imageFilters.put("Gaussian Blur", v -> new GaussianBlur(saveName, saveName));
    imageFilters.put("Emboss", v -> new Emboss(saveName, saveName));
    imageFilters.put("Sharpen", v -> new Sharpen(saveName, saveName));
    imageFilters.put("Ridge Detection", v -> new RidgeDetection(saveName, saveName));

    for (String name : programCommands.keySet()) {
      view.addToFileMenu(addFunctionButton(name, programCommands.get(name)));
    }

    for (String name : imageTransforms.keySet()) {
      view.addToImageTransforms(addFunctionButton(name, imageTransforms.get(name)));
    }

    for (String name : colorFilters.keySet()) {
      view.addToColorFilters(addFunctionButton(name, colorFilters.get(name)));
    }

    for (String name : imageFilters.keySet()) {
      view.addToImageFilters(addFunctionButton(name, imageFilters.get(name)));
    }
  }

  private JMenuItem addFunctionButton(String name, Function<JFrame, Process> func) throws IllegalArgumentException {
    if (func == null) {
      throw new IllegalArgumentException("Function cannot be null");
    }

    JMenuItem item = new JMenuItem(new AbstractAction(name) {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Process p = func.apply(view);
          p.start(manager);
          p = new SaveFile(saveName + "-" + "temp" + extension, saveName);
          p.start(manager);
          view.setImage(ImageIO.read(new File(saveName + "-" + "temp" + extension)));
        } catch (Exception ex) {
          try {
            view.renderMessage("Error: " + ex);
          } catch (IOException exception) {
            throw new RuntimeException(exception);
          }
        }
      }
    });

    return item;
  }


}
