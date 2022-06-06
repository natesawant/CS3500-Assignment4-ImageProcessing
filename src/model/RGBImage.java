package model;

import java.awt.*;

public class RGBImage extends AbstractImage {
  public RGBImage(Color[][] pixels, int maxValue) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels array cannot be null.");
    } else {
      this.pixels = pixels;
      this.width = pixels.length;
      this.height = pixels[0].length;
      this.maxValue = maxValue;
    }
  }
}
