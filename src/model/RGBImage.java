package model;

import java.awt.*;
import java.util.Collection;

public class RGBImage extends AbstractImage {
  public RGBImage(Color[][] pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels array cannot be null.");
    } else {
      this.pixels = pixels;
      this.width = pixels.length;
      this.height = pixels[0].length;
    }
  }
}
