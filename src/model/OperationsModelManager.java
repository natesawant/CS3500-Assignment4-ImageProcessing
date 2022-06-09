package model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import util.ImageUtil;

/**
 * Implementation of the image processing operations.
 */
public final class OperationsModelManager implements OperationsModel {

  private Map<String, Image> loaded;
  private Image img;
  private int width;
  private int height;
  private int r;
  private int g;
  private int b;
  private int max;
  private Color[][] pixels;
  private Color newColor;
  private double[][] kernel;

  /**
   * Constructs a default model that allows different operations to be applied to images.
   */
  public OperationsModelManager() {
    loaded = new HashMap<String, Image>();
  }

  @Override
  public void load(String path, String name) throws IllegalArgumentException {
    try {
      img = ImageUtil.convertPPM(path);
      loaded.put(name, img);
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException(ex.getMessage());
    }
  }

  @Override
  public void save(String path, String name) throws IllegalArgumentException {
    Writer output;
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }
    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    max = img.getMaxValue();
    try {
      output = new FileWriter(path);
      output.append("P3 ").append(System.lineSeparator());
      output.append("# Created by an image processing program by Nathaniel Sawant "
              + "and Aiden Cahill for CS3500 at Northeastern University.\n");
      output.append(String.valueOf(width))
              .append(" ")
              .append(String.valueOf(height))
              .append(" \n");
      output.append(String.valueOf(max)).append(" \n");

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          r = img.getPixel(x, y).getRed();
          g = img.getPixel(x, y).getGreen();
          b = img.getPixel(x, y).getBlue();

          output.append(String.valueOf(r))
                  .append(" ").append(System.lineSeparator());
          output.append(String.valueOf(g))
                  .append(" ").append(System.lineSeparator());
          output.append(String.valueOf(b))
                  .append(" ").append(System.lineSeparator());
        }
      }
      output.close();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("Filepath not valid.");
    } catch (IOException ex) {
      throw new IllegalArgumentException("Unable to write.");
    }
  }

  @Override
  public void valueComponent(String component, String name, String destName)
          throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int val;
        newColor = img.getPixel(x, y);
        switch (component.toLowerCase()) {
          case "red":
            val = newColor.getRed();
            break;
          case "green":
            val = newColor.getGreen();
            break;
          case "blue":
            val = newColor.getBlue();
            break;
          case "value":
            val = Math.max(newColor.getBlue(), Math.max(newColor.getRed(),
                    newColor.getGreen()));
            break;
          case "luma":
            val = (newColor.getBlue()
                    + newColor.getRed()
                    + newColor.getGreen()) / 3;
            break;
          case "intensity":
            val = (int) (0.2126 * newColor.getBlue()
                    + 0.7152 * newColor.getRed()
                    + 0.0722 * newColor.getGreen());
            break;
          default:
            throw new IllegalArgumentException("Not supported component.");
        }

        pixels[x][y] = new Color(val, val, val);
      }
    }


    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }

  @Override
  public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        pixels[width - x - 1][y] = img.getPixel(x, y);
      }
    }

    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }

  @Override
  public void verticalFlip(String name, String destName) throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        pixels[x][height - y - 1] = img.getPixel(x, y);
      }
    }


    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }

  @Override
  public void brighten(int increment, String name, String destName)
          throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newColor = img.getPixel(x, y);
        max = img.getMaxValue();

        r = Math.max(Math.min(newColor.getRed() + increment, max), 0);
        g = Math.max(Math.min(newColor.getGreen() + increment, max), 0);
        b = Math.max(Math.min(newColor.getBlue() + increment, max), 0);

        pixels[x][y] = new Color(r, g, b);
      }
    }

    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }

  @Override
  public void applyKernel(double[][] kernel, String name, String destName)
          throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    } else if (kernel.length % 2 == 0 || kernel[0].length % 2 == 0) {
      throw new IllegalArgumentException("Kernel dimensions must be odd.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];
    max = img.getMaxValue();

    int halfWidth = (int) Math.floor(kernel.length / 2);
    int halfHeight = (int) Math.floor(kernel[0].length / 2);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color[][] sample = new Color[kernel.length][kernel[0].length];


        for (int i = -halfWidth; i <= halfWidth; i++) {
          for (int j = -halfHeight; j <= halfHeight; j++) {
            if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height) {
              sample[i + halfWidth][j + halfHeight] = img.getPixel(x + i, y + j);
            } else if (x + i >= 0 && x + i < width) {
              sample[i + halfWidth][j + halfHeight] = img.getPixel(x + i, y);
            } else if (y + j >= 0 && y + j < height) {
              sample[i + halfWidth][j + halfHeight] = img.getPixel(x, y + j);
            } else {
              sample[i + halfWidth][j + halfHeight] = img.getPixel(x, y);
            }
          }
        }

        r = 0;
        g = 0;
        b = 0;

        for (int i = 0; i < sample.length; i++) {
          for (int j = 0; j < sample[0].length; j++) {
            if (sample[i][j] != null) {
              r += kernel[i][j] * sample[i][j].getRed();
              g += kernel[i][j] * sample[i][j].getGreen();
              b += kernel[i][j] * sample[i][j].getBlue();
            }
          }
        }

        r = Math.max(Math.min(r, max), 0);
        g = Math.max(Math.min(g, max), 0);
        b = Math.max(Math.min(b, max), 0);

        pixels[x][y] = new Color(r, g, b);
      }
    }

    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }
}
