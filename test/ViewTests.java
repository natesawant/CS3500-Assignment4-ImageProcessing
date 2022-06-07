import org.junit.Test;

import java.io.IOException;

import model.OperationsModelManager;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionality of the text view to interface with this image-editing program.
 */
public class ViewTests {

  /*
  Tests needed:
  One param throws when model null
  two param throws for all combos of null
  render throws will null message
  render throws with bad appendable

  appendable has content send through method
   */

  @Test (expected = IllegalArgumentException.class)
  public void oneParamNullModelThrows() {
    ImageProcessingView v = new ImageProcessingTextView(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void twoParamNullModelValidAppendThrows() {
    ImageProcessingView v = new ImageProcessingTextView(null, new StringBuilder());
  }

  @Test (expected = IllegalArgumentException.class)
  public void twoParamValidModelNullAppendThrows() {
    ImageProcessingView v = new ImageProcessingTextView(new OperationsModelManager(), null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void twoParamNullBothThrows() {
    ImageProcessingView v = new ImageProcessingTextView(null, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullMessageThrows() {
    ImageProcessingView v
            = new ImageProcessingTextView(new OperationsModelManager(), new StringBuilder());
    try {
      v.renderMessage(null);
    } catch (IOException e) {
      fail();
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void badAppendableThrows() {
    ImageProcessingView v
            = new ImageProcessingTextView(new OperationsModelManager(), new CorruptAppendable());
    try {
      v.renderMessage("This will fail.");
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  public void renderMessageCorrect() {
    Appendable a = new StringBuilder();
    ImageProcessingView v = new ImageProcessingTextView(new OperationsModelManager(), a);
    try {
      v.renderMessage("Hello world");
    } catch (IOException e) {
      fail();
    }
    assertEquals("Hello world", a.toString());
  }

}
