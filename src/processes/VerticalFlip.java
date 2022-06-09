package processes;

import model.OperationsModel;
import processes.Process;

public class VerticalFlip implements Process {
  String name;
  String destName;

  public VerticalFlip(String name, String destName) {
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.verticalFlip(this.name, this.destName);
  }
}

