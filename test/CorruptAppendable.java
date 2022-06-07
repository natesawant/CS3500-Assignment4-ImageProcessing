import java.io.IOException;

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
