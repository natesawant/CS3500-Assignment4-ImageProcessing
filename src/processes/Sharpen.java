package processes;

public class Sharpen extends ApplyKernel {
  public Sharpen(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel =
            new double[][]{{ 0,-1, 0},
                    {-1, 5,-1},
                    { 0,-1, 0}};
  }
}
