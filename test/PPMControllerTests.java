import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImplementation;
import model.OperationsModel;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of the controller of this program.
 */
public class PPMControllerTests {

  OperationsModel model;
  ImageProcessingView view;

  StringBuilder mockLog;

  @Before
  public void init() {

    mockLog = new StringBuilder();
    model = new ModelMock(mockLog);
    view = new ImageProcessingTextView(model);

  }

  @Test
  public void controllerSuccess() {
    // TODO
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullReadable() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", view, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullView() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", null, new StringReader(""));
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullViewAndReadable() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", null, null);
  }

  @Test
  public void loadSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
                view, model, new StringReader("load images/testPicture test\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to load:", output[0]);
    assertEquals("Path: images/testPicture", output[1]);
    assertEquals("Desired name: test", output[2]);
  }

  @Test
  public void saveSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
                view, model, new StringReader("save images/testPicture test\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to save:", output[0]);
    assertEquals("Path: images/testPicture", output[1]);
    assertEquals("Desired name: test", output[2]);
  }

  @Test
  public void valueSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
                view,
                model,
                new StringReader("value-component red images/testPicture redPicture\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to get component: red", output[0]);
    assertEquals("Image name: images/testPicture", output[1]);
    assertEquals("Output name: redPicture", output[2]);
  }

  @Test
  public void hFlipSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
                view, model, new StringReader("horizontal-flip images/testPicture testFlip\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt horizontal flip of image: images/testPicture", output[0]);
    assertEquals("To output: testFlip", output[1]);
  }

  @Test
  public void vFlipSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
                view, model, new StringReader("vertical-flip images/testPicture testFlip\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt vertical flip of image: images/testPicture", output[0]);
    assertEquals("To output: testFlip", output[1]);
  }

  @Test
  public void testBrightenSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
                view, model, new StringReader("brighten 1000 images/testPicture outPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to change brightness by: 1000", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: outPic", output[2]);
  }

  @Test
  public void testBlurSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
                view, model, new StringReader("box-blur images/testPicture blurredPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: blurredPic", output[2]);
  }

  // waiting on implementation for last few

  @Test
  public void testSharpenSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image", view, model, new StringReader("sharpen images/testPicture sharpPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: sharpPic", output[2]);
  }

  @Test
  public void ridgeDetectionSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image", view, model, new StringReader("ridge-detection images/testPicture ridgePic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: ridgePic", output[2]);
  }

  @Test
  public void applyKernelSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image", view, model, new StringReader("emboss images/testPicture embossedPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: embossedPic", output[2]);
  }

  @Test
  public void gaussianSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image", view, model, new StringReader("gaussian-blur images/testPicture gaussian\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: gaussian", output[2]);
  }

}
