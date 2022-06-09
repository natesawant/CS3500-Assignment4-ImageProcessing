import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImplementation;
import model.OperationsModel;
import model.OperationsModelManager;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the functionality of the controller of this program.
 */
public class PPMControllerTests {

  OperationsModel model;
  OperationsModel mockModel;
  ImageProcessingView mockView;
  ImageProcessingView view;

  StringBuilder mockLog;
  StringBuilder log;

  @Before
  public void init() {

    log = new StringBuilder();
    model = new OperationsModelManager();
    view = new ImageProcessingTextView(model, log);
    mockLog = new StringBuilder();
    mockModel = new ModelMock(mockLog);
    mockView = new ImageProcessingTextView(mockModel);

  }


  @Test (expected = IllegalArgumentException.class)
  public void nullReadable() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", mockView, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullView() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", null, new StringReader(""));
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullViewAndReadable() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("", null, null);
  }

  @Test (expected = IllegalStateException.class)
  public void incompleteInputThrows() {
    ImageProcessingController c = new ImageProcessingControllerImplementation("",
            mockView, mockModel, new StringReader("load images/test.ppm"));
    c.initializeProgram();
  }

  @Test
  public void quitImmediately() {
    ImageProcessingController c = new ImageProcessingControllerImplementation("images",
            view, model, new StringReader("q"));
    c.initializeProgram();
    String[] output = log.toString().split(System.lineSeparator());
    assertEquals("Quitting program.", output[1]);
  }

  @Test (expected = IllegalStateException.class)
  public void quitDuringCommandThrows() {
    ImageProcessingController c = new ImageProcessingControllerImplementation("images",
            view, model, new StringReader("load q"));
    c.initializeProgram();
  }

  @Test
  public void loadSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images/",
            mockView, mockModel, new StringReader("load testPicture test\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to load:", output[0]);
    assertEquals("Path: images/testPicture", output[1]);
    assertEquals("Desired name: test", output[2]);
  }

  @Test
  public void saveSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images/",
            mockView, mockModel, new StringReader("save testPicture test\nq"));
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
            mockView,
            mockModel,
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
            mockView, mockModel, new StringReader("horizontal-flip images/testPicture " +
            "testFlip\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt horizontal flip of image: images/testPicture", output[0]);
    assertEquals("To output: testFlip", output[1]);
  }

  @Test
  public void vFlipSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("images",
            mockView, mockModel, new StringReader("vertical-flip images/testPicture testFlip\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt vertical flip of image: images/testPicture", output[0]);
    assertEquals("To output: testFlip", output[1]);
  }

  @Test
  public void testBrightenSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
            mockView, mockModel, new StringReader("brighten 1000 images/testPicture outPic\nq"));
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
            mockView, mockModel, new StringReader("box-blur images/testPicture blurredPic\nq"));
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
            = new ImageProcessingControllerImplementation("image",
            mockView, mockModel, new StringReader("sharpen images/testPicture sharpPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: sharpPic", output[2]);
  }

  @Test
  public void ridgeDetectionSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
            mockView, mockModel, new StringReader("ridge-detection images/testPicture " +
            "ridgePic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: ridgePic", output[2]);
  }

  @Test
  public void applyKernelSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
            mockView, mockModel, new StringReader("emboss images/testPicture embossedPic\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: embossedPic", output[2]);
  }

  @Test
  public void gaussianSendsCorrectArguments() {
    ImageProcessingController c
            = new ImageProcessingControllerImplementation("image",
            mockView, mockModel, new StringReader("gaussian-blur images/testPicture gaussian\nq"));
    c.initializeProgram();

    String[] output = mockLog.toString().split("\n");
    assertEquals("Attempt to apply kernel of size 3 by 3", output[0]);
    assertEquals("To image: images/testPicture", output[1]);
    assertEquals("To output: gaussian", output[2]);
  }

}
