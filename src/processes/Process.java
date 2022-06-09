package processes;

import model.OperationsModel;

/**
 * This interface represents an operation that can be applied to an image.
 */
public interface Process {

  /**
   * Applies the given OperationModel to this image.
   * @param m The operation to be applied.
   */
  void go(OperationsModel m);
}
