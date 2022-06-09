package processes;

public class RidgeDetection extends ApplyKernel {
  public RidgeDetection(String name, String destName) {
    super(name, destName);
  }
  @Override
  public void initKernel() {
    kernel =
            new double[][]{{-1,-1,-1},
                    {-1, 8,-1},
                    {-1,-1,-1}};
  }
}
