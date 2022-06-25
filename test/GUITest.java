import org.junit.Test;

import controller.ImageProcessingGUIController;
import view.ImageProcessingGUI;

/**
 * Tests the functionality of the GUI.
 */
public class GUITest {

  ImageProcessingGUI g;

  @Test
  public void test() {
    ImageProcessingGUIController c = new GUIControllerMock();
    c.initializeProgram();

  }
}
