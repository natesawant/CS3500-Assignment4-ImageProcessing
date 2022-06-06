import controller.ImageProcessing;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImplementation;

import org.junit.Before;
import org.junit.Test;

import model.OperationsModel;
import model.OperationsModelManager;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import java.io.StringReader;

public class OperationTests {
  OperationsModel manager;
  @Before
  public void setup() {
    manager = new OperationsModelManager();
    manager.load("images/koala.ppm", "koala");
  }

  @Test
  public void testHFlip() {
    manager.horizontalFlip("koala","koala");
    manager.save("images/koala-horizontal.ppm", "koala");
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
    manager.blur(3,"koala","koala");
    manager.save("images/koala-smallblur.ppm", "koala");
  }

  @Test
  public void testBigBlur() {
    manager.blur(10,"koala","koala");
    manager.save("images/koala-bigblur.ppm", "koala");
  }

  @Test
  public void testCopy() {
    manager.save("images/koala-copy.ppm", "koala");
  }
}
