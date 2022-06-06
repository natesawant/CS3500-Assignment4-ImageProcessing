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

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof Image) {
      Image img = (Image) other;
      int width = img.getWidth();
      int height = img.getHeight();

      if (this.getWidth() != width || this.getHeight() != height) {
        return false;
      }
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          if (!this.getPixel(x,y).equals(img.getPixel(x,y))) {
            return false;
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }
}
