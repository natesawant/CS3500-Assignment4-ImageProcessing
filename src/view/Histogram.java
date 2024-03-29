package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import model.Image;

/**
 * This class represents a histogram that can be used in a JFrame object.
 */
public class Histogram extends JComponent {

  private Graphics2D gr;
  private Map<Integer, Integer> redValueCount;
  private Map<Integer, Integer> greenValueCount;
  private Map<Integer, Integer> blueValueCount;
  private Map<Integer, Integer> intensityCount;
  private int min;
  private int max;
  private Image img;

  /**
   * Constructs a new, empty histogram.
   */
  public Histogram() {
    setOpaque(true);
    setBackground(Color.WHITE);
    redValueCount = new HashMap<>();
    greenValueCount = new HashMap<>();
    blueValueCount = new HashMap<>();
    intensityCount = new HashMap<>();
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
  }

  /**
   * Assigns the correct RGB values to this instance's hashmaps.
   */
  private void getValues() {
    redValueCount = new HashMap<>();
    greenValueCount = new HashMap<>();
    blueValueCount = new HashMap<>();
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;

    if (img != null) {
      int value;
      for (int x = 0; x < img.getWidth(); x++) {
        for (int y = 0; y < img.getHeight(); y++) {
          value = img.getPixel(x,y).getRed();
          redValueCount.put(value, 1 + redValueCount.getOrDefault(value, 0));
          value = img.getPixel(x,y).getGreen();
          greenValueCount.put(value, 1 + greenValueCount.getOrDefault(value, 0));
          value = img.getPixel(x,y).getBlue();
          blueValueCount.put(value, 1 + blueValueCount.getOrDefault(value, 0));
          int intensity = (img.getPixel(x, y).getRed()
                  + img.getPixel(x, y).getGreen()
                  + img.getPixel(x, y).getBlue()) / 3;
          intensityCount.put(intensity, 1 + intensityCount.getOrDefault(intensity, 0));

        }
      }
    }
  }

  /**
   * Sets the current image of this histogram.
   * @param img the desired image.
   */
  public void setImg(Image img) {
    this.img = img;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    gr = (Graphics2D) g;
    gr.scale(2, 2);
    getValues();

    for (Integer key : redValueCount.keySet()) {
      int val = redValueCount.get(key);
      max = Math.max(val, max);
      min = Math.min(val, min);
    }
    for (Integer key : greenValueCount.keySet()) {
      int val = greenValueCount.get(key);
      max = Math.max(val, max);
      min = Math.min(val, min);
    }
    for (Integer key : blueValueCount.keySet()) {
      int val = blueValueCount.get(key);
      max = Math.max(val, max);
      min = Math.min(val, min);
    }
    for (Integer key : intensityCount.keySet()) {
      int val = intensityCount.get(key);
      max = Math.max(val, max);
      min = Math.min(val, min);
    }


    drawList(redValueCount, Color.red);
    drawList(greenValueCount, Color.green);
    drawList(blueValueCount, Color.blue);
    drawList(intensityCount, Color.black);
  }

  /**
   * Draws a given list of values as a histogram.
   * @param list the list of values to draw.
   * @param color the color to draw.
   */
  private void drawList(Map<Integer, Integer> list, Color color) {
    int y1;
    int x1;
    int y2;
    int x2;

    gr.setPaint(Color.WHITE);
    gr.drawRect(0,0,256,100);

    gr.setPaint(color);
    gr.setStroke(new BasicStroke(1));
    for (int i = 0; i < 256 - 1; i++) {
      x1 = i;
      y1 = (list.getOrDefault(x1, 0));

      y1 = (int)((y1 - min) / (double)(max - min) * 100);

      x2 = i + 1;
      y2 = (list.getOrDefault(x2, 0));

      y2 = (int)((y2 - min) / (double)(max - min) * 100);

      gr.drawLine(x1, 100 - y1, x2, 100 - y2);
    }
  }
}