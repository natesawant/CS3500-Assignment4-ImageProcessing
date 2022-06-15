import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.Image;
import model.OperationsModel;
import model.OperationsModelManager;
import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of two new methods that manipulate
 * kernels being applied to images.
 */
public class ManipKernelTests {

  // test addition using a process that extends the ApplyAdditionFilter process
  // in this case, AdjustRed

  OperationsModel m;
  Image actual;
  Image expected;

  @Before
  public void init() {
    m = new OperationsModelManager();
  }

  @Test
  public void testApplyAdditionFilterPPM() {
    m.load("images/test1x1Black.ppm", "test");
    m.applyAdditionFilter((c) -> (new double[]{255, 0, 0}), "test", "test-red");
    m.save("images/test-red.ppm", "test-red");

    expected = ImageUtil.convertPPM("images/test-red-expected.ppm");
    actual = ImageUtil.convertPPM("images/test-red.ppm");

    assertEquals(expected, actual);
  }

  @Test
  public void testApplyMultiplyFilterPPM() {
    m.load("images/test2x2.ppm", "test");
    m.applyMultiplyFilter((color -> (new double[][]{{1.0, 0.0, 0.0}, {0,0,0}, {0,0,0}})), "test", "test-mult");
    m.save("images/test-mult.ppm", "test-mult");

    // not really "actual" , just original to show how color values relate
    actual = ImageUtil.convertPPM("images/test2x2.ppm");
    expected = ImageUtil.convertPPM("images/test-mult.ppm");

    assertEquals(new Color(36, 32, 33), actual.getPixel(0, 0));
    assertEquals(new Color(36, 0, 0), expected.getPixel(0,0));

    assertEquals(new Color(244, 240, 241), actual.getPixel(1, 1));
    assertEquals(new Color(244, 0, 0), expected.getPixel(1, 1));

  }


}
