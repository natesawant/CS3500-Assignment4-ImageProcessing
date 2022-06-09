package processes;

public class Emboss extends ApplyKernel {
  public Emboss(String name, String destName) {
    super(name, destName);
  }

  @Override
  public void initKernel() {
    kernel = new double[][]
            {{-2,-1, 0},
                    {-1, 1, 1},
                    { 0, 1, 2}};
  }
}
