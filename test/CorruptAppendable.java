import java.io.IOException;

/**
 * This class represents a corrupt appendable object that throws an exception when
 * something is appended to it.
 */
public class CorruptAppendable implements Appendable {


  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    return null;
  }

  @Override
  public Appendable append(char c) throws IOException {
    return null;
  }
}
