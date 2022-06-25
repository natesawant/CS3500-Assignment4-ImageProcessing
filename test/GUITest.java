import org.junit.Test;

import controller.ImageGUI;
import view.ImageProcessingGUI;

/**
 * Tests the functionality of the GUI.
 */
public class GUITest {

  ImageProcessingGUI g;

  @Test
  public void test() {
    ImageGUI c = new GUIControllerMock();
    c.initializeProgram();

  }
}
