package processes;

import model.OperationsModel;
import processes.Process;

public class Load implements Process {
  String path;
  String destName;

  public Load(String path, String destName) {
    this.path = path;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.load(this.path, this.destName);
  }
}