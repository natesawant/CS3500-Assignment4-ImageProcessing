import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import model.RGBImage;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of our downscaling operations.
 */
public class ECTest {
  Image expected;
  Image actual;
  OperationsModel m;

  @Before
  public void init() {
    m = new OperationsModelManager();
  }

  @Test
  public void test() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(3, 2, "test", "testDown");
    m.save("testDown.ppm", "testDown");

    actual = ImageUtil.convertPPM("testDown.ppm");
    assertEquals(2, actual.getHeight());
    assertEquals(2, actual.getWidth());
    expected = new RGBImage(new Color[][]{
            {new Color(117, 109, 101), new Color(77, 53, 29)},
            {new Color(173, 79, 72), new Color(130, 81, 79)}}, 255);

    assertEquals(expected, actual);
  }

  @Test (expected = IllegalArgumentException.class)
  public void biggerWidthThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(5, 3, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void biggerHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(2, 7, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void biggerWidthAndHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(5, 8, "test", "testDown");
  }
}
