import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import util.ImageUtil;
import org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmallScaleOperationTest {

  Image img;
  OperationsModel m;
  Image actual, expected;

  @Before
  public void setup() {
    m = new OperationsModelManager();
    m.load("images/TestImageOriginalExpected.ppm", "img");
  }

  @Test
  public void testSave() {
    m.save("images/TestImageOriginalActual.ppm","img");

    actual = ImageUtil.convertPPM("images/TestImageOriginalActual.ppm");
    expected = ImageUtil.convertPPM("images/TestImageOriginalExpected.ppm");

    assertEquals(expected,actual);
  }

  @Test
  public void testVerticalFlip() {
    m.verticalFlip("img","img");
    m.save("images/TestImageVerticalActual.ppm","img");

    actual = ImageUtil.convertPPM("images/TestImageVerticalActual.ppm");
    expected = ImageUtil.convertPPM("images/TestImageVerticalExpected.ppm");

    assertEquals(expected,actual);
  }

  @Test
  public void testConstructor() {
    m = new OperationsModelManager();
  }

  /*
  Tests to do:

  Exceptions:
  load throws when the filepath is not valid

  save throws when the provided image has not yet been loaded
  save throws when the filepath is not valid
  save throws when unable to append to Writer -- make corrupt

  valueComponent throws when image has not yet been loaded
  valueComponent throws when provided value is not supported

  horizontalFlip throws when provided image has not yet been loaded

  verticalFlip throws when provided image has not yet been loaded

  brighten throws when provided image has not yet been loaded

  applyKernel throws when provided image has not yet been loaded
  applyKernel throws when provided dimensions are not odd

  Tests:
  load ->
    provided image is present in loaded after call
  save ->
    must input writer , ensure it contains the first correct lines
    spot check pixels for correctness
    try with:
      size 0 image
      square image
      rectangular image
  valueComponent ->
    will need to first load image, edit it, then save for each iteration
      use for loop to go through each variation
      spot check one to two pixels per variation
  horizontalFlip ->
    will need to first load image, edit, then save and check with output
      might be easier to try with a 1 x 2 picture , ensure they swap places
  verticalFlip ->
    same as above, but try with a 2 x 1 picture , ensure the two pixels swap
  brighten ->
    can try with a one pixel image | should also try with a larger
    attempt with both positive and negative values and check delta of each color
  rest : Nate


   */

}
