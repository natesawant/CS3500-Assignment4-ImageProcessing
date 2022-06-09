package processes;

public class BoxBlur extends ApplyKernel {
  public BoxBlur(String name, String destName) {
    super(name, destName);
  }
  @Override
  public void initKernel() {
    kernel = new double[][]
            {{1.0/9.0,1.0/9.0,1.0/9.0},
                    {1.0/9.0,1.0/9.0,1.0/9.0},
                    {1.0/9.0,1.0/9.0,1.0/9.0}};
  }
}
