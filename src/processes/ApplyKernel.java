package processes;

import model.OperationsModel;

public abstract class ApplyKernel implements Process {
  double[][] kernel;
  String name;
  String destName;

  public ApplyKernel(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    initKernel();
    m.applyKernel(this.kernel, this.name, this.destName);
  }

  public abstract void initKernel();
}
