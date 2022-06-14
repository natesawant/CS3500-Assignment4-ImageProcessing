import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * THis class tests the program's ability to convert to different filetypes.
 */
public class NewTypeConversionTests {

  /*
  To test:
  exportPNG
  exportJPEG
  convertPNGJPEG
   */

  OperationsModel m;
  Image actual;
  Image expected;

  @Before
  public void init() {
    m = new OperationsModelManager();
  }

  @Test
  public void testExportImagePNG() {
    m.load("images/test2x2.ppm", "test");
    expected = ImageUtil.convertPPM("images/test2x2.ppm");
    m.save("images/test2x2.png", "test");

    m.load("images/test2x2.png", "test");
    m.save("images/test2x2.ppm", "test");
    actual = ImageUtil.convertPPM("images/test2x2.ppm");

    assertEquals(expected, actual);
  }

  @Test
  public void testExportJPG() {
    m.load("images/test1x1BlackJPG.jpg", "test");
    m.save("images/test1x1BlackJPGCopy.jpg", "test");

    expected = ImageUtil.convertPNGJPEG("images/test1x1BlackJPG.jpg");
    actual = ImageUtil.convertPNGJPEG("images/test1x1BlackJPGCopy.jpg");

    assertEquals(expected, actual);

  }

  @Test
  public void testExportJPEG() {
    m.load("images/test1x1BlackJPG.jpg", "test");
    m.save("images/test1x1BlackJPEGCopy.jpeg", "test");

    expected = ImageUtil.convertPNGJPEG("images/test1x1BlackJPG.jpg");
    actual = ImageUtil.convertPNGJPEG("images/test1x1BlackJPEGCopy.jpeg");

    assertEquals(expected, actual);

  }

  @Test
  public void testConvertJPEGToImage() {
    // tests both JPEG and JPG since they are the same file type
    expected = ImageUtil.convertPNGJPEG("images/test1x1BlackJPG.jpg");
    assertEquals(new Color(0, 0, 0), expected.getPixel(0, 0));
    assertEquals(1, expected.getHeight());
    assertEquals(1, expected.getWidth());
  }

  @Test
  public void testConvertPNGToImage() {
    expected = ImageUtil.convertPNGJPEG("images/test2x2.png");
    assertEquals(new Color(36, 32, 33), expected.getPixel(0, 0));
    assertEquals(2, expected.getWidth());
    assertEquals(2, expected.getHeight());
  }
}
