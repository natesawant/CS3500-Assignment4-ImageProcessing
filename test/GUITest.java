import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.Random;

import controller.ImageProcessingGUIController;
import view.ImageProcessingGUI;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the GUI.
 */
public class GUITest {

  @Test
  public void test() {

    String[] actions = new String[]{
            "Save",
            "Save As",
            "Open",
            "Flip (Horizontal)",
            "Flip (Vertical)",
            "Downscale",
            "Adjust Red",
            "Adjust Green",
            "Adjust Blue",
            "Adjust Brightness",
            "Invert Colors",
            "Sepia Tone",
            "Greyscale (Red)",
            "Greyscale (Green)",
            "Greyscale (Blue)",
            "Greyscale (Value)",
            "Greyscale (Intensity)",
            "Greyscale (Luma)",
            "Box Blur",
            "Gaussian Blur",
            "Emboss",
            "Sharpen",
            "Ridge Detection"};

    Appendable log = new StringBuilder();
    Random r = new Random();
    String action = actions[r.nextInt(actions.length)];
    ImageProcessingGUIController c = new GUIControllerMock(log);
    c.initializeProgram();
    c.actionPerformed(new ActionEvent(this, 0, action));

    assertEquals("Action performed: " + action, log.toString() );
  }
}
