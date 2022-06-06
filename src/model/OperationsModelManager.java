package model;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ImageUtil;

/**
 * Implementation of the image processing operations.
 */
public final class OperationsModelManager implements OperationsModel {
    Map<String, Image> loaded;

    public OperationsModelManager() {
        loaded = new HashMap<String, Image>();
    }

    @Override
    public void load(String path, String name) throws IllegalArgumentException {
        try {
            Image img = ImageUtil.convertPPM(path);
            loaded.put(name, img);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Filepath not valid.");
        }
    }

    @Override
    public void save(String path, String name) throws IllegalArgumentException {
        Writer output;
        Image img;
        int width, height,r,g,b,max;
        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }
        img = loaded.get(name);
        width = img.getWidth();
        height = img.getHeight();
        max = img.getMaxValue();
        try {
            output = new FileWriter(path);
            output.append("P3 " + System.lineSeparator());
            output.append("# Created by an image processing program by Nathaniel Sawant and Aiden" +
                    " Cahill for CS3500 at Northeastern University." + System.lineSeparator());
            output.append(width + " " +height + " " + System.lineSeparator());
            output.append(max + " " + System.lineSeparator());

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    r = img.getPixel(x,y).getRed();
                    g = img.getPixel(x,y).getGreen();
                    b = img.getPixel(x,y).getBlue();

                    output.append(r + " " + System.lineSeparator());
                    output.append(g + " " + System.lineSeparator());
                    output.append(b + " " + System.lineSeparator());
                }
            }
            output.close();
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Filepath not valid.");
        } catch(IOException ex) {
            throw new IllegalArgumentException("Unable to write.");
        }
    }

    @Override
    public void valueComponent(String component, String name, String destName) throws IllegalArgumentException {
        Image img;
        Color[][] pixels;
        int width;
        int height;
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
                Color newColor;
                int r,g,b,max;
                newColor = img.getPixel(x,y);
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
                        val = (newColor.getBlue() + newColor.getRed() + newColor.getGreen()) / 3;
                        break;
                    case "intensity":
                        val = (int)
                                (0.2126 * newColor.getBlue() + 0.7152 * newColor.getRed() + 0.0722 * newColor.getGreen());
                        break;
                    default:
                        throw new IllegalArgumentException("Not supported component.");
                }
                max = img.getMaxValue();

                r = val;
                g = val;
                b = val;

                pixels[x][y] = new Color(r,g,b);
            }
        }


        int max = img.getMaxValue();
        loaded.put(destName, new RGBImage(pixels, max));
    }

    @Override
    public void horizontalFlip(String name, String destName) throws IllegalArgumentException {
        Image img;
        Color[][] pixels;
        int width;
        int height;
        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }

        img = loaded.get(name);
        width = img.getWidth();
        height = img.getHeight();
        pixels = new Color[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][height - y - 1] = img.getPixel(x,y);
            }
        }

        int max = img.getMaxValue();
        loaded.put(destName, new RGBImage(pixels, max));
    }

    @Override
    public void verticalFlip(String name, String destName) throws IllegalArgumentException {
        Image img;
        Color[][] pixels;
        int width;
        int height;
        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }

        img = loaded.get(name);
        width = img.getWidth();
        height = img.getHeight();
        pixels = new Color[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[width - x - 1][y] = img.getPixel(x,y);
            }
        }

        int max = img.getMaxValue();
        loaded.put(destName, new RGBImage(pixels, max));
    }

    @Override
    public void brighten(int increment, String name, String destName) throws IllegalArgumentException {
        Image img;
        Color[][] pixels;
        int width;
        int height;
        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }

        img = loaded.get(name);
        width = img.getWidth();
        height = img.getHeight();
        pixels = new Color[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color newColor;
                int r,g,b,max;
                newColor = img.getPixel(x,y);
                max = img.getMaxValue();

                r = Math.max(Math.min(newColor.getRed() + increment, max), 0);
                g = Math.max(Math.min(newColor.getGreen() + increment, max), 0);
                b = Math.max(Math.min(newColor.getBlue() + increment, max), 0);

                pixels[x][y] = new Color(r,g,b);
            }
        }

        int max = img.getMaxValue();
        loaded.put(destName, new RGBImage(pixels, max));
    }

    @Override
    public void blur(int radius, String name, String destName) throws IllegalArgumentException {
        Image img;
        Color[][] pixels;
        int width;
        int height;
        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }

        img = loaded.get(name);
        width = img.getWidth();
        height = img.getHeight();
        pixels = new Color[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                List<Color> sample;
                sample = new ArrayList<Color>();

                for (int i = -radius; i <= radius; i++) {
                    for (int j = -radius; j <= radius; j++) {
                        if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height) {
                            sample.add(img.getPixel(x + i, y + j));
                        }
                    }
                }

                int r = 0,g = 0,b = 0;

                for (Color s : sample) {
                    r += s.getRed();
                    g += s.getGreen();
                    b += s.getBlue();
                }
                r /= sample.size();
                g /= sample.size();
                b /= sample.size();

                pixels[x][y] = new Color(r,g,b);
            }
        }

        int max = img.getMaxValue();
        loaded.put(destName, new RGBImage(pixels, max));
    }
}
