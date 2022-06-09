package processes;

import model.OperationsModel;
import processes.Process;

public class Save implements Process {
  String path;
  String destName;

  public Save(String path, String destName) {
    this.path = path;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.save(this.path, this.destName);
  }
}