package model;

import java.awt.*;

public abstract class AbstractImage implements Image {
  protected Color[][] pixels;
  protected int width;
  protected int height;

  @Override
  public Color getPixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= width) {
      throw new IllegalArgumentException("X must be [0, " + width + ").");
    } else if (y < 0 || y >= height) {
      throw new IllegalArgumentException("Y must be [0, " + height + ").");
    } else {
      Color ret = pixels[x][y];
      return ret;
    }
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
