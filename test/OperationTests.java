import controller.ImageProcessing;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImplementation;
import org.junit.Test;
import view.ImageProcessingTextView;

import java.io.StringReader;

public class OperationTests {

  @Test
  public void testFlip() {
    ImageProcessingController c = new ImageProcessingControllerImplementation("", new ImageProcessingTextView(System.out), new StringReader("load images/Koala.ppm koala \nhorizontal-flip koala koala-horizontal \nsave images/koala-hs.ppm koala-horizontal \nq"));
    c.initializeProgram();


  }

}
