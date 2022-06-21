import org.junit.Test;

import model.OperationsModel;
import model.OperationsModelManager;

public class ECTest {

  @Test
  public void test() {
    OperationsModel m = new OperationsModelManager();
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(2, 2, "test", "testDown");
    m.save("testDown.ppm", "testDown");
  }
}
