package controller;

import model.OperationsModel;
import model.OperationsModelManager;
import processes.BoxBlur;
import processes.Brighten;
import processes.Emboss;
import processes.GaussianBlur;
import processes.HorizontalFlip;
import processes.Load;
import processes.Process;
import processes.RidgeDetection;
import processes.Save;
import processes.SepiaTone;
import processes.Sharpen;
import processes.ValueComponent;
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

    OperationsModel m = new OperationsModelManager();
    String path;
    String ext = ".ppm";
    String base;
    String op;
    Process p;

    if (args.length > 1) {
      base = args[1];
      path = args[0] + "/" + base;
    }
    else if (args.length > 0) {
      path = "";
      base = args[0];
    } else {
      throw new IllegalArgumentException("Must provide at least the file name (without .ppm)");
    }

    p = new Load(path + ext, base);
    p.start(m);


    p = new Save(path + "-copy" + ext, base);
    p.start(m);

    op = "vertical";
    p = new VerticalFlip(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "horizontal";
    p = new VerticalFlip(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "horizontalvertical";
    p = new HorizontalFlip(base, base + op);
    p.start(m);
    p = new VerticalFlip(base + op, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "bright";
    p = new Brighten(100, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "dark";
    p = new Brighten(-100, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "red";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "green";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "blue";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "intensity";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "luma";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "value";
    p = new ValueComponent(op, base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "ridge";
    p = new RidgeDetection(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "sharp";
    p = new Sharpen(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "box";
    p = new BoxBlur(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "gaussian";
    p = new GaussianBlur(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "emboss";
    p = new Emboss(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    op = "sepia";
    p = new SepiaTone(base, base + op);
    p.start(m);
    p = new Save(path + op + ext, base + op);
    p.start(m);
    System.out.println("Done with " + op);

    System.out.println("Completed processing.");
  }
}
