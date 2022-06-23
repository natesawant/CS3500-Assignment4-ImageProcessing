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
  public void testDownSameWidthHeight() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(2, 2, "test", "testDown");
    m.save("images/testDown.ppm", "testDown");

    actual = ImageUtil.convertPPM("images/testDown.ppm");
    assertEquals(2, actual.getHeight());
    assertEquals(2, actual.getWidth());
    expected = new RGBImage(new Color[][]{
            {new Color(117, 109, 101), new Color(77, 53, 29)},
            {new Color(173, 79, 72), new Color(130, 81, 79)}}, 255);

    assertEquals(expected, actual);
  }

  @Test
  public void testDiffWidthHeight() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(3, 2, "test", "testDown");
    m.save("images/testDown32.ppm", "testDown");

    actual = ImageUtil.convertPPM("images/testDown32.ppm");
    assertEquals(2, actual.getHeight());
    assertEquals(3, actual.getWidth());
    expected = new RGBImage(new Color[][]{
            {new Color(102, 91, 80), new Color(52, 47, 34)},
            {new Color(207, 126, 122), new Color(111, 120, 116)},
            {new Color(136, 46, 37), new Color(126, 49, 47)}}, 255);

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

  @Test (expected = IllegalArgumentException.class)
  public void zeroWidthThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(0, 2, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void zeroHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(2, 0, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void zeroWidthHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(0, 0, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void negWidthThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(-2, 2, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void negHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(3, -2, "test", "testDown");
  }

  @Test (expected = IllegalArgumentException.class)
  public void negWidthHeightThrows() {
    m.load("images/test4x4.ppm", "test");
    m.applyDownscaling(-2, -2, "test", "testDown");
  }
}
