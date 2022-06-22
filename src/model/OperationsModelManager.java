package model;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import processes.BlueGrayscale;
import processes.Brighten;
import processes.GreenGrayscale;
import processes.IntensityGrayscale;
import processes.LumaGrayscale;
import processes.Process;
import processes.RedGrayscale;
import processes.ValueGrayscale;
import util.ImageUtil;

/**
 * Implementation of the image processing operations.
 */
public final class OperationsModelManager implements OperationsModel {

  private Map<String, Image> loaded;
  private Image img;
  private int width;
  private int height;
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
    String[] seperatedPath = path.split("\\.");
    String ext = seperatedPath[seperatedPath.length - 1];

    Map<String, Function<String, Image>> conversionMethod = new HashMap<>();
    conversionMethod.put("ppm", p -> ImageUtil.convertPPM(p));
    conversionMethod.put("png", p -> ImageUtil.convertPNGJPEG(p));
    conversionMethod.put("jpeg", p -> ImageUtil.convertPNGJPEG(p));
    conversionMethod.put("jpg", p -> ImageUtil.convertPNGJPEG(p));

    try {
      Function<String, Image> convert = conversionMethod.getOrDefault(ext, null);
      if (convert != null) {
        img = convert.apply(path);
        loaded.put(name, img);
      } else {
        throw new IllegalArgumentException("Illegal file type.");
      }
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException(ex.getMessage());
    }
  }

  @Override
  public void save(String path, String name) throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }
    img = loaded.get(name);

    String[] seperatedPath = path.split("\\.");
    String ext = seperatedPath[seperatedPath.length - 1];

    try {
      switch (ext) {
        case "ppm":
          ImageUtil.exportPPM(img, path);
          break;
        case "jpeg":
          ImageUtil.exportJPEG(img,path);
          break;
        case "jpg":
          ImageUtil.exportImage(img, path);
          break;
        case "png":
          ImageUtil.exportPNG(img, path);
          break;
        case "bmp":
          ImageUtil.exportBmp(img, path);
          break;
        default:
          throw new IllegalArgumentException("File extension is not currently supported.");
      }

    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public void valueComponent(String component, String name, String destName)
          throws IllegalArgumentException {

    double[][] filter;
    Process process;
    switch (component.toLowerCase()) {
      case "red":
        process = new RedGrayscale(name, destName);
        process.start(this);

        break;
      case "green":
        process = new GreenGrayscale(name, destName);
        process.start(this);

        break;
      case "blue":
        process = new BlueGrayscale(name, destName);
        process.start(this);

        break;
      case "value":
        process = new ValueGrayscale(name, destName);
        process.start(this);

        break;
      case "luma":
        process = new LumaGrayscale(name, destName);
        process.start(this);

        break;
      case "intensity":
        process = new IntensityGrayscale(name, destName);
        process.start(this);

        break;
      default:
        throw new IllegalArgumentException("Not supported component.");
    }
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

    Process process = new Brighten(increment,name,destName);
    process.start(this);
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

        int r = 0;
        int g = 0;
        int b = 0;

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

  @Override
  public void applyMultiplyFilter(Function<Color,
          double[][]> filterFunc, String name, String destName) throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];
    int maxValue = img.getMaxValue();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newColor = img.getPixel(x, y);

        double[][] filter = filterFunc.apply(newColor);
        int[] rgb = {newColor.getRed(), newColor.getGreen(), newColor.getBlue()};
        int[] newRGB = new int[3];

        for (int c = 0; c < filter.length; c++) {

          for (int j = 0; j < filter[c].length; j++) {
            newRGB[c] += (rgb[j] * filter[c][j]);
          }
          newRGB[c] = Math.max(Math.min(newRGB[c], maxValue),0);
        }

        pixels[x][y] = new Color(newRGB[0], newRGB[1], newRGB[2]);
      }
    }


    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }

  @Override
  public void applyAdditionFilter(Function<Color, double[]> filterFunc, String name,
                                String destName)
          throws IllegalArgumentException {
    if (!loaded.containsKey(name)) {
      throw new IllegalArgumentException("Image not loaded.");
    }

    img = loaded.get(name);
    width = img.getWidth();
    height = img.getHeight();
    pixels = new Color[width][height];
    int maxValue = img.getMaxValue();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newColor = img.getPixel(x, y);

        double[] filter = filterFunc.apply(newColor);
        int[] rgb = {newColor.getRed(), newColor.getGreen(), newColor.getBlue()};
        int[] newRGB = new int[3];

        for (int c = 0; c < filter.length; c++) {
          newRGB[c] += (rgb[c] + filter[c]);
          newRGB[c] = Math.max(Math.min(newRGB[c], maxValue),0);
        }

        pixels[x][y] = new Color(newRGB[0], newRGB[1], newRGB[2]);
      }
    }


    max = img.getMaxValue();
    loaded.put(destName, new RGBImage(pixels, max));
  }
}
