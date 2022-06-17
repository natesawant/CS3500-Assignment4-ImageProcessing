package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.OperationsModel;
import model.OperationsModelManager;
import processes.AdjustBlue;
import processes.AdjustGreen;
import processes.AdjustRed;
import processes.BlueGrayscale;
import processes.BoxBlur;
import processes.Brighten;
import processes.Emboss;
import processes.GaussianBlur;
import processes.GreenGrayscale;
import processes.HorizontalFlip;
import processes.IntensityGrayscale;
import processes.LoadFile;
import processes.LumaGrayscale;
import processes.Process;
import processes.InvertColors;
import processes.RedGrayscale;
import processes.RidgeDetection;
import processes.SaveFile;
import processes.SepiaTone;
import processes.Sharpen;
import processes.ValueGrayscale;
import processes.VerticalFlip;

/**
 * Processes an image and saves all the available image processes.
 */
public class ApplyAllProcesses {

  /**
   * Processes an image and saves all the available image processes.
   * @param args the last arg must be the file name without .ppm. Optionally, the first argument
   *             can be the directory that the file is stored in. Ex: res igm.
   */
  public static void main(String[] args) {
    Map<String, Function<String, Process>> processes = new HashMap<>();
    final String dir;
    final String importExt;
    final String exportExt;
    final String name;
    long startTime = System.currentTimeMillis();
    long time;

    if (args.length == 3) {
      // directory
      dir = args[0];

      // image + ext
      String[] splitFileName = args[1].split("\\.");
      if (splitFileName.length != 2) {
        throw new IllegalArgumentException("Missing file extension.");
      }
      name = splitFileName[0];
      importExt = splitFileName[1];

      exportExt = args[2].replace(".", "");
    } else if (args.length == 2) {
      // directory
      dir = args[0];

      // image + ext
      String[] splitFileName = args[1].split("\\.");
      if (splitFileName.length != 2) {
        throw new IllegalArgumentException("Missing file extension.");
      }
      name = splitFileName[0];
      importExt = splitFileName[1];
      exportExt = splitFileName[1];
    } else if (args.length == 1) {
      // directory
      dir = "";

      // image + ext
      String[] splitFileName = args[1].split(".");
      name = splitFileName[0];
      importExt = splitFileName[1];
      exportExt = splitFileName[1];
    } else {
      throw new IllegalArgumentException("Must have directory and file or just file.");
    }

    System.out.println();
    System.out.println("Directory: " + dir);
    System.out.println("File name: " + name + "." + importExt);
    System.out.println("Export File Type: " + "." + exportExt);
    System.out.println();

    processes.put("load", s -> new LoadFile(dir + "/" + name + "." + importExt, name + "-" + s));
    processes.put("save", s -> new SaveFile(dir + "/" + name + "-" + s + "." + exportExt, name +
            "-copy"));
    processes.put("vertical-flip", s -> new VerticalFlip(name, name + "-" + s));
    processes.put("horizontal-flip", s -> new HorizontalFlip(name, name + "-" + s));
    processes.put("brighten", s -> new Brighten(100,name, name + "-" + s));
    processes.put("darken", s -> new Brighten(-100,name, name + "-" + s));
    processes.put("more-blue", s -> new AdjustBlue(100, name, name + "-" + s));
    processes.put("more-green", s -> new AdjustGreen(100, name, name + "-" + s));
    processes.put("more-red", s -> new AdjustRed(100, name, name + "-" + s));
    processes.put("less-blue", s -> new AdjustBlue(-100, name, name + "-" + s));
    processes.put("less-green", s -> new AdjustGreen(-100, name, name + "-" + s));
    processes.put("less-red", s -> new AdjustRed(-100, name, name + "-" + s));
    processes.put("red-grayscale", s -> new RedGrayscale(name, name + "-" + s));
    processes.put("green-grayscale", s -> new GreenGrayscale(name, name + "-" + s));
    processes.put("blue-grayscale", s -> new BlueGrayscale(name, name + "-" + s));
    processes.put("value-grayscale", s -> new ValueGrayscale(name, name + "-" + s));
    processes.put("intensity-grayscale", s -> new IntensityGrayscale(name, name + "-" + s));
    processes.put("luma-grayscale", s -> new LumaGrayscale(name, name + "-" + s));
    processes.put("sepia-tone", s -> new SepiaTone(name, name + "-" + s));
    processes.put("inverted-colors", s -> new InvertColors(name, name + "-" + s));
    processes.put("box-blur", s -> new BoxBlur(name, name + "-" + s));
    processes.put("gaussian-blur", s -> new GaussianBlur(name, name + "-" + s));
    processes.put("sharpen", s -> new Sharpen(name, name + "-" + s));
    processes.put("emboss", s -> new Emboss(name, name + "-" + s));
    processes.put("ridge-detection", s -> new RidgeDetection(name, name + "-" + s));

    OperationsModel m = new OperationsModelManager();

    new LoadFile(dir + "/" + name + "." + importExt, name).start(m);
    new LoadFile(dir + "/" + name + "." + importExt, name + "-save").start(m);

    int count = 0;
    int total = processes.size();
    for (String processKey : processes.keySet()) {
      count++;
      System.out.println("Current Process: " + count + "/" + total);
      System.out.println("Initiating process '" + processKey + "'.");
      System.out.println("...............");
      time = System.currentTimeMillis();
      processes.get(processKey).apply("copy").start(m);
      processes.get("save").apply(processKey).start(m);
      System.out.println("Completed and saved process '"
              + processKey + "' at " + dir + "/" + name + "-" + processKey + "." + exportExt);
      System.out.println("Time to process: " + (System.currentTimeMillis() - time) + " ms.");
      System.out.println();
    }

    System.out.println();
    System.out.println("COMPLETED ALL PROCESSES.");
    System.out.println("TOTAL PROCESS TIME: " + (System.currentTimeMillis() - startTime + " ms."));
  }
}