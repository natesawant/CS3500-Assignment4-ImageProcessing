import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;

public class OutputAll {
  public static void main(String[] args) {
    OperationsModel m = new OperationsModelManager();
    String path = "res/igm";
    String ext = ".ppm";
    String base = "igm";
    String op;

    m.load(path + ext, base);
    m.save(path + "-copy" + ext, base);

    op = "-vertical";
    m.verticalFlip(base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "-horizontal";
    m.horizontalFlip(base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "-bright";
    m.brighten(100, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "-dark";
    m.brighten(-100, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "red";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "green";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "blue";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "intensity";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "luma";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "value";
    m.valueComponent(op, base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "ridge";
    m.ridgeDetection(base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "sharp";
    m.sharpen(base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    op = "blur";
    m.boxBlur(3,base, base + op);
    m.save(path + op + ext, base + op);
    System.out.println("Done with " + op);

    System.out.println("Completed processing.");
  }
}
