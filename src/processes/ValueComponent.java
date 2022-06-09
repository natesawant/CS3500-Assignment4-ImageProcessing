package processes;

import model.OperationsModel;
import processes.Process;

public class ValueComponent implements Process {
  String value;
  String name;
  String destName;

  public ValueComponent(String value, String name, String destName) {
    this.value = value;
    this.name = name;
    this.destName = destName;
  }

  @Override
  public void go(OperationsModel m) {
    m.valueComponent(this.value, this.name, this.destName);
  }
}
