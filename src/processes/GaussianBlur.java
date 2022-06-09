package processes;

public class GaussianBlur extends ApplyKernel {
  public GaussianBlur(String name, String destName) {
    super(name, destName);
  }
  @Override
  public void initKernel() {
    kernel = new double[][]
            {{1.0/16.0, 2.0/16.0, 1.0/16.0},
                    {2.0/16.0, 4.0/16.0, 2.0/16.0},
                    { 1.0/16.0, 2.0/16.0, 1.0/16.0}};
  }
}
