import model.OperationsModel;


public class ModelMock implements OperationsModel {

  StringBuilder log;

  public ModelMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void load(String path, String name) throws IllegalArgumentException {
    this.log.append("Attempt to load:\nPath: ").append(path)
            .append("\nDesired name: ").append(name);
  }

  @Override
  public void save(String path, String name) throws IllegalArgumentException {
    this.log.append("Attempt to save:\nPath: ").append(path)
            .append("\nDesired name: ").append(name);
  }

  @Override
  public void valueComponent(String component, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Attempt to get component: ").append(component)
            .append("\nImage name: ").append(name)
            .append("\nOutput name: ").append(destName);
  }

  @Override
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Attempt horizontal flip of image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void verticalFlip(String name, String destName) throws IllegalArgumentException {
    this.log.append("Attempt vertical flip of image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Attempt to change brightness by: ").append(increment)
            .append("\nTo image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void boxBlur(int radius, String name, String destName) throws IllegalArgumentException {
    this.log.append("Attempt to apply box blur of radius: ").append(radius)
            .append("\nTo image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void sharpen(String name, String destName) throws IllegalArgumentException {
    this.log.append("Attempt to apply sharpen.")
            .append("\nTo image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void ridgeDetection(String name, String destName) throws IllegalArgumentException {
    this.log.append("Attempt to apply ridge-detection.")
            .append("\nTo image: ").append(name)
            .append("\nTo output: ").append(destName);
  }

  @Override
  public void applyKernel(double[][] kernel, String name, String destName)
          throws IllegalArgumentException {
    this.log.append("Attempt to apply kernel of size ")
            .append(kernel.length).append(" by ").append(kernel[0].length)
            .append("\nTo image: ").append(name).append("\nTo output: ").append(destName);
  }
}
