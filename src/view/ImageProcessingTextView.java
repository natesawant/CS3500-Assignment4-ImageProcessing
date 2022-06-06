package view;

import java.io.IOException;

/**
 * The text view allows for messages to be rendered to an appendable object.
 */
public class ImageProcessingTextView implements ImageProcessingView {
  Appendable appendable;

  /**
   * Alternative constructor with option for different appendable object.
   *
   * @param app   A non-null Appendable object.
   */
  public ImageProcessingTextView(Appendable app) {
    verifyStateAppend(app);
  }

  /**
   * Makes sure that the arguments are not null.
   * @param app should be a non-null Appendable object.
   */
  protected void verifyStateAppend(Appendable app) throws IllegalArgumentException {
    if (app == null) {
      throw new IllegalArgumentException("Appendable is null");
    } else {
      this.appendable = app;
    }
  }

  /**
   * Appends the given message to a certain appendable object.
   * @param message the message to be transmitted.
   * @throws IOException if unable to append the message or message is null.
   */
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null.");
    } else {
      try {
        appendable.append(message);
      } catch (Exception ex) {
        throw new IOException("Cannot append message.");
      }
    }
  }
}
