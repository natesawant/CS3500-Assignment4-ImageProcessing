package processes;

import model.OperationsModel;
import processes.Process;

public class Brighten implements Process {
  int increment;
  String name;
  String destName;

  public Brighten(int increment, String name, String destName) {
    this.increment = increment;
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.brighten(this.increment, this.name, this.destName);
  }
}
