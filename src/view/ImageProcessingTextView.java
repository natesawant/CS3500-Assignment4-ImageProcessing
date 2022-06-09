package view;

import java.io.IOException;

import model.OperationsModel;

/**
 * The text view allows for messages to be rendered to an appendable object.
 */
public class ImageProcessingTextView implements ImageProcessingView {
  protected final OperationsModel model;
  protected final Appendable appendable;

  /**
   * Constructs a text view based off of a provided model.
   * @param model the given model to connect with the view.
   * @throws IllegalArgumentException if the model is null.
   */
  public ImageProcessingTextView(OperationsModel model) throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }

    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Alternative constructor with option for different appendable object.
   *
   * @param app   A non-null Appendable object.
   */
  public ImageProcessingTextView(OperationsModel model, Appendable app)
          throws IllegalArgumentException {
    if (app == null || model == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.appendable = app;
  }

  /**
   * Makes sure that the arguments are not null.
   * @param app should be a non-null Appendable object.
   */
  protected void verifyStateAppend(Appendable app) throws IllegalArgumentException {
    if (app == null) {
      throw new IllegalArgumentException("Appendable is null");
    }
  }

  /**
   * Appends the given message to a certain appendable object.
   * @param message the message to be transmitted.
   * @throws IOException if unable to append the message or message is null.
   */
  public void renderMessage(String message) throws IllegalArgumentException {
    if (message == null) {
      throw new IllegalArgumentException("Message cannot be null.");
    } else {
      try {
        appendable.append(message);
      } catch (IOException ex) {
        throw new IllegalArgumentException("Cannot append message.");
      }
    }
  }
}
