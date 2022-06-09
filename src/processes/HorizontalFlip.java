package processes;

import model.OperationsModel;
import processes.Process;

public class HorizontalFlip implements Process {
  String name;
  String destName;

  public HorizontalFlip(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.horizontalFlip(this.name, this.destName);
  }
}
