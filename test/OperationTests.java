import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of different operations that can be applied to images.
 */
public class OperationTests {

  OperationsModel manager;
  Image expected, actual;

  @Before
  public void setup() {
    manager = new OperationsModelManager();
    manager.load("images/koala.ppm", "koala");
  }

  @Test
  public void testHFlip() {
    manager.horizontalFlip("koala","koala");
    manager.save("images/koala-horizontal.ppm", "koala");

    actual = ImageUtil.convertPPM("images/koala-horizontal.ppm");
    expected = ImageUtil.convertPPM("images/koala-horizontal-expected.ppm");

    assertEquals(expected,actual);
  }

  @Test
  public void testVFlip() {
    manager.verticalFlip("koala","koala");
    manager.save("images/koala-vertical.ppm", "koala");
  }

  @Test
  public void testVHFlip() {
    manager.verticalFlip("koala","koala");
    manager.horizontalFlip("koala","koala");
    manager.save("images/koala-vertical-horizontal.ppm", "koala");
  }

  @Test
  public void testBrighten() {
    manager.brighten(100,"koala","koala");
    manager.save("images/koala-bright.ppm", "koala");
  }

  @Test
  public void testDarken() {
    manager.brighten(-100,"koala","koala");
    manager.save("images/koala-dark.ppm", "koala");
  }

  @Test
  public void testRed() {
    manager.valueComponent("red","koala","koala");
    manager.save("images/koala-red.ppm", "koala");
  }

  @Test
  public void testGreen() {
    manager.valueComponent("green","koala","koala");
    manager.save("images/koala-green.ppm", "koala");
  }

  @Test
  public void testBlue() {
    manager.valueComponent("blue","koala","koala");
    manager.save("images/koala-blue.ppm", "koala");
  }

  @Test
  public void testValue() {
    manager.valueComponent("value","koala","koala");
    manager.save("images/koala-value.ppm", "koala");
  }

  @Test
  public void testIntensity() {
    manager.valueComponent("intensity","koala","koala");
    manager.save("images/koala-intensity.ppm", "koala");
  }

  @Test
  public void testLuma() {
    manager.valueComponent("luma","koala","koala");
    manager.save("images/koala-luma.ppm", "koala");
  }

  @Test
  public void testSmallBlur() {
    manager.boxBlur(1,"koala","koala");
    manager.save("images/koala-smallblur.ppm", "koala");
  }

  @Test
  public void testBigBlur() {
    manager.boxBlur(3,"koala","koala");
    manager.save("images/koala-bigblur.ppm", "koala");
  }

  @Test
  public void testSharpen() {
    manager.sharpen("koala","koala");
    manager.save("images/koala-sharpen.ppm", "koala");
  }

  @Test
  public void testRidgeDetection() {
    manager.ridgeDetection("koala","koala");
    manager.save("images/koala-ridge.ppm", "koala");
  }

  @Test
  public void testCopy() {
    manager.save("images/koala-copy.ppm", "koala");
  }

  @Test
  public void testEmboss() {
    double[][] kernel =
            {{-2,-1, 0},
                    {-1, 1, 1},
                    { 0, 1, 2}};
    manager.applyKernel(kernel,"koala","koala");
    manager.save("images/koala-emboss.ppm","koala");
  }

  @Test
  public void testGaussianBlue3x3() {
    double[][] kernel =
            {{1.0/16.0, 2.0/16.0, 1.0/16.0},
                    {2.0/16.0, 4.0/16.0, 2.0/16.0},
                    { 1.0/16.0, 2.0/16.0, 1.0/16.0}};
    manager.applyKernel(kernel,"koala","koala");
    manager.save("images/koala-gaussianblur3x3.ppm","koala");
  }

/*  @Test
  public void testCat() {
    manager.load("images/catp3.ppm", "cat");
    manager.horizontalFlip("cat", "cat");
    manager.save("images/cat-flipped.ppm", "cat");
  }*/
}
