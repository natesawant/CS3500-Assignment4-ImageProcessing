package model;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import util.ImageUtil;

public final class OperationManager implements Operation {
    Map<String, Image> loaded;

    public OperationManager() {
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





        if (!loaded.containsKey(name)) {
            throw new IllegalArgumentException("Image not loaded.");
        }
        switch (component.toLowerCase()) {
            case "red":
                break;
            case "green":
                break;
            case "blue":
                break;
            case "value":
                break;
            case "luma":
                break;
            case "intensity":
                break;
            default:
                throw new IllegalArgumentException("Not supported component.");
        }

        throw new IllegalArgumentException("Still in progress.");
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
                pixels[x][y] = img.getPixel(width - x - 1,y);
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

        System.out.println("width: " + width + ", height: " + height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = img.getPixel(x,height - y - 1);
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
}
