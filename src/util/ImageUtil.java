package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.RGBImage;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Exports an Image object to a .ppm file format.
   * @param img the desired image to convert
   * @param path the desired path of the new image relative to this program's directory
   */
  public static void exportPPM(Image img, String path) {
    Writer output;
    int width = img.getWidth();
    int height = img.getHeight();
    int max = img.getMaxValue();
    int r;
    int g;
    int b;

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

  public static void exportPNG(Image img, String path) throws IOException {
    exportImage(img,path);
  }

  public static void exportJPEG(Image img, String path) throws IOException {
    exportImage(img,path);
  }

  /**
   * Exports an image object to a given filetype.
   * @param img the desired image to export.
   * @param path the desired path of the image relative to this program's directory.
   * @throws IOException if the file is unable to be written to.
   */
  public static void exportImage(Image img, String path) throws IOException {
    String[] seperatedPath = path.split("\\.");
    String ext = seperatedPath[seperatedPath.length - 1];

    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    WritableRaster raster = bufferedImage.getRaster();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color color = img.getPixel(x, y);
        int[] rgb = {color.getRed(), color.getGreen(), color.getBlue()};
        raster.setPixel(x, y, rgb);
      }
    }

    ImageIO.write(bufferedImage, ext, new File(path));
  }

  /**
   * Converts a PNG or JPEG (JPG) image file to an Image object.
   * @param filename the name of the desired file.
   * @return The Image object equivalent of the provided file.
   * @throws IllegalArgumentException if there is an error reading the image file.
   */
  public static Image convertPNGJPEG(String filename) throws IllegalArgumentException {
    Image img;
    Color[][] pixels;
    BufferedImage bufferedImg;

    try {
      bufferedImg = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    int width = bufferedImg.getWidth();
    int height = bufferedImg.getHeight();
    int maxValue = 255;

    pixels = new Color[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = bufferedImg.getRGB(x, y);
        pixels[x][y] = new Color(rgb);
      }
    }

    img = new RGBImage(pixels, maxValue);

    return img;
  }


  /**
   * Converts a .ppm file to an Image object.
   *
   * @param filename the filepath of the .ppm file.
   * @return an Image object.
   * @throws IllegalArgumentException if the filepath is invalid.
   */
  public static Image convertPPM(String filename) throws IllegalArgumentException {
    Image img;
    Color[][] pixels;

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    if (width == 0 || height == 0) {
      throw new IllegalArgumentException("Image must be at least 1 pixel by 1 pixel!");
    }

    pixels = new Color[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Color color = new Color(r, g, b);
        pixels[x][y] = color;
      }
    }

    img = new RGBImage(pixels, maxValue);

    return img;
  }


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Demo main.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

