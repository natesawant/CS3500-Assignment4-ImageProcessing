import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import model.RGBImage;
import util.ImageUtil;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class tests the functionality of the operations that can be used to alter images.
 */
public class SmallScaleOperationTest {

  Image img;
  OperationsModel m;
  Image actual;
  Image expected;

  @Before
  public void setup() {
    m = new OperationsModelManager();
    m.load("images/TestImageOriginalExpected.ppm", "img");
  }

  @Test
  public void testLoadPNGSavePPM() {
    m.load("images/TestImageOriginalExpected.png", "imgpng");
    m.save("images/TestImageOriginalPNGToPPM.ppm","imgpng");
  }

  @Test
  public void testLoadJPEGSavePPM() {
    m.load("images/TestImageOriginalExpected.jpg", "imgjpg");
    m.save("images/TestImageOriginalJPGToPPM.ppm","imgjpg");
  }

  @Test
  public void testLoadPPMSaveJPG() {
    m.save("images/TestImageOriginalPPMToJPG.jpg", "img");
  }

  @Test
  public void testVerticalFlip() {
    m.verticalFlip("img","img");
    m.save("images/TestImageVerticalActual.ppm","img");

    actual = ImageUtil.convertPPM("images/TestImageVerticalActual.ppm");
    expected = ImageUtil.convertPPM("images/TestImageVerticalExpected.ppm");

    assertEquals(expected,actual);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidFilepathThrows() {
    m.load("taco/invalid/picture", "doesnt-exist");
  }

  @Test (expected = IllegalArgumentException.class)
  public void savingUnloadedThrows() {
    m.save("images/test2x2.ppm", "cool-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void savingInvalidFilePathThrows() {
    m.save("taco/invalid/picture", "newPicture");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testLoadSizeZeroThrows() {
    m.load("images/test0x0.ppm", "sizeZero");
  }

  @Test (expected = IllegalArgumentException.class)
  public void componentOfUnloadedThrows() {
    m.valueComponent("red", "normal-koala", "red-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void componentNotRGBThrows() {
    m.valueComponent("black", "normal-koala", "black-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void hFlipUnloadedThrows() {
    m.horizontalFlip("normal-koala", "flipped-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void vFlipUnloadedThrows() {
    m.verticalFlip("normal-koala", "flipped-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void brightenUnloadedThrows() {
    m.brighten(39, "normal-koala", "bright-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void kernelUnloadedThrows() {
    m.applyKernel(new double[][]{
        new double[]{0, 0, 0},
        new double[]{1, 1, 1},
        new double[]{2, 2, 2}}, "normal-koala", "kernel-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void kernelEvenWidthOddHeightThrows() {
    m.applyKernel(new double[][]{
        new double[]{0, 0, 0},
        new double[]{1, 1, 1}}, "normal-koala", "kernel-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void kernelOddWidthEvenHeightThrows() {
    m.applyKernel(new double[][]{new double[]{0, 0}}, "normal-koala", "kernel-koala");
  }

  @Test (expected = IllegalArgumentException.class)
  public void kernelEvenWidthEvenHeightThrows() {
    m.applyKernel(new double[][]{
        new double[]{0, 0},
        new double[]{1, 1}}, "normal-koala", "kernel-koala");
  }

  /*
  Tests to do:

  Exceptions:
  load throws when the filepath is not valid <>

  save throws when the provided image has not yet been loaded <>
  save throws when the filepath is not valid <>
  save throws when unable to append to Writer -- make corrupt

  valueComponent throws when image has not yet been loaded <>
  valueComponent throws when provided value is not supported <>

  horizontalFlip throws when provided image has not yet been loaded <>

  verticalFlip throws when provided image has not yet been loaded <>

  brighten throws when provided image has not yet been loaded <>

  applyKernel throws when provided image has not yet been loaded <>
  applyKernel throws when provided dimensions are not odd <>

  Tests:
  load ->
    provided image is present in loaded after call (shouldn't throw when trying to save) <>
  save ->
    must input writer , ensure it contains the first correct lines
    spot check pixels for correctness
    try with:
      size 0 image should throw <>
      square image <>
      rectangular image <>
  valueComponent ->
    will need to first load image, edit it, then save for each iteration
      use for loop to go through each variation
      spot check one to two pixels per variation <>
  horizontalFlip ->
    will need to first load image, edit, then save and check with output
      might be easier to try with a 1 x 2 picture , ensure they swap places <>
  verticalFlip ->
    same as above, but try with a 2 x 1 picture , ensure the two pixels swap <>
  brighten ->
    can try with a one pixel image | should also try with a larger
    attempt with both positive and negative values and check delta of each color <>
  rest : Nate

   */

  @Test
  public void testLoad() {
    m.load("images/test2x2.ppm", "test");

    try {
      m.save("images/test2x2.ppm", "test");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testSaveRectangle() {
    m.save("images/TestImageOriginalActual.ppm","img");

    actual = ImageUtil.convertPPM("images/TestImageOriginalActual.ppm");
    expected = ImageUtil.convertPPM("images/TestImageOriginalExpected.ppm");

    assertEquals(expected,actual);
  }

  @Test
  public void testSaveSquare() {
    m.load("images/test2x2.ppm", "test");
    m.save("images/test2x2.ppm", "test");

    actual = ImageUtil.convertPPM("images/test2x2.ppm");
    expected = new RGBImage(new Color[][]{
        {Color.RED, Color.BLUE}, {Color.GREEN, Color.WHITE}}, 255);

    assertEquals(expected, actual);
  }

  @Test
  public void testValueComponent() {

    m.load("images/test2x2.ppm", "test");
    String[] types = new String[]{"red", "green", "blue", "value", "luma", "intensity"};
    // of test2x2.ppm , values output calculating given types value:

    Image red = new RGBImage(new Color[][]{
            {Color.WHITE, Color.BLACK},
            {Color.BLACK, Color.WHITE}}, 255);
    Image green = new RGBImage(new Color[][]{
            {Color.BLACK, Color.BLACK},
            {Color.WHITE, Color.WHITE}}, 255);
    Image blue = new RGBImage(new Color[][]{
            {Color.BLACK, Color.WHITE},
            {Color.BLACK, Color.WHITE}}, 255);
    Image value = new RGBImage(new Color[][]{
            {Color.WHITE, Color.WHITE},
            {Color.WHITE, Color.WHITE}}, 255);
    Image intensity = new RGBImage(new Color[][]{
            {new Color(85, 85, 85), new Color(85, 85, 85)},
            {new Color(85, 85, 85), new Color(255, 255,  255)}}, 255);
    Image luma = new RGBImage(new Color[][]{
            {new Color(54, 54, 54), new Color(18, 18, 18)},
            {new Color(182, 182, 182), new Color(254, 254, 254)}}, 255);

    Image[] expected = new Image[]{red, green, blue, value, luma, intensity};

    for (int i = 0; i < 6; i++) {
      String currentType = types[i];
      m.valueComponent(currentType, "test", types[i]);

      m.save(types[i] + ".ppm", types[i]);
      actual = ImageUtil.convertPPM(types[i] + ".ppm");
      for (int x = 0; x < actual.getWidth(); x++) {
        for (int y = 0; y < actual.getHeight(); y++) {
          assertEquals(expected[i].getPixel(x,y), actual.getPixel(x,y));
        }
      }
      assertEquals(expected[i], actual);
    }

  }

  @Test
  public void testHFlip() {

    m.load("images/test2x1.ppm", "test");

    m.horizontalFlip("test", "test");
    m.save("hflip.ppm", "test");

    actual = ImageUtil.convertPPM("hflip.ppm");
    expected = new RGBImage(new Color[][]{{Color.GREEN}, {Color.RED}}, 255);

    assertEquals(expected, actual);
  }

  @Test
  public void testVFlip() {

    m.load("images/test1x2.ppm", "test");

    m.verticalFlip("test", "test");
    m.save("vflip.ppm", "test");

    actual = ImageUtil.convertPPM("vflip.ppm");
    expected = new RGBImage(new Color[][]{{Color.GREEN, Color.RED}}, 255);

    assertEquals(expected, actual);

  }

  @Test
  public void testBrighten() {

    Random r = new Random();
    m.load("images/test1x1.ppm", "test");

    int delta = r.nextInt(1020) - 510;
    int expectedVal;

    if (delta < -100) {
      expectedVal = 0;
    } else if (delta > 155) {
      expectedVal = 255;
    } else {
      expectedVal = delta + 100;
    }

    expected = new RGBImage(new Color[][]{{new Color(expectedVal, expectedVal, expectedVal)}}, 255);

    m.brighten(delta, "test", "test");
    m.save("bright.ppm", "test");

    actual = ImageUtil.convertPPM("bright.ppm");

    assertEquals(expected, actual);
  }

  /*
  still needed:
  box blur
  emboss
  gaussian blur
  ridge detection
   */

  @Test
  public void testBoxBlur() {
    m.load("images/test2x2.ppm", "test");

    m.applyKernel(new double[][]
        {{1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0},
        {1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0},
        {1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0}}, "test", "blur-test");
    m.save("images/blur-test.ppm", "blur-test");

    actual = ImageUtil.convertPPM("images/blur-test.ppm");
    expected = new RGBImage(new Color[][]{
            {new Color(140, 84, 84), new Color(112, 84, 168)},
            {new Color(112, 168, 84), new Color(140, 168, 168)}}, 255);

    assertEquals(expected, actual);
  }

  @Test
  public void testEmboss() {
    m.load("images/test2x2.ppm", "test");

    m.applyKernel(new double[][]
        {{-2,-1, 0},
        {-1, 1, 1},
        {0, 1, 2}}, "test", "emboss-test");
    m.save("images/emboss-test.ppm", "emboss-test");

    actual = ImageUtil.convertPPM("images/emboss-test.ppm");
    expected = new RGBImage(new Color[][]{
            {new Color(0, 255, 255), new Color(0, 255, 255)},
            {new Color(0, 255, 255), new Color(255, 255, 255)}}, 255);

    assertEquals(expected, actual);

  }

  @Test
  public void testGaussian() {
    m.load("images/test2x2.ppm", "test");

    m.applyKernel(new double[][]
        {{1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0},
        {2.0 / 16.0, 4.0 / 16.0, 2.0 / 16.0},
        { 1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0}}, "test", "gauss-test");
    m.save("images/gauss-test.ppm", "gauss-test");

    actual = ImageUtil.convertPPM("images/gauss-test.ppm");
    expected = new RGBImage(new Color[][]{
            {new Color(155, 61, 61), new Color(92, 61, 186)},
            {new Color(92, 186, 61), new Color(155, 186, 186)}}, 255);

    assertEquals(expected, actual);
  }

  @Test
  public void testRidge() {

    m.load("images/test-flip.ppm", "test");

    m.applyKernel(new double[][]{{-1,-1,-1},
        {-1, 8,-1},
        {-1,-1,-1}}, "test", "ridge-test");
    m.save("images/ridge-test.ppm", "ridge-test");

    actual = ImageUtil.convertPPM("images/ridge-test.ppm");
    expected = new RGBImage(new Color[][]{
            {new Color(255, 0, 0), new Color(0, 16, 0), new Color(0, 0, 0)},
            {new Color(0, 9, 41), new Color(0, 0, 0), new Color(255, 0, 0)},
            {new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255)}}, 255);

    assertEquals(expected, actual);
  }

}
