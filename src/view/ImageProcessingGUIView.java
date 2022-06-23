package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import util.ImageUtil;

public class ImageProcessingGUIView extends JFrame implements ImageProcessingGUI {
  private final JMenuBar menu;
  private final JMenu fileMenu;
  private final JMenu processMenu;
  private final JMenu imageTransforms;
  private final JMenu colorFilters;
  private final JMenu imageFilters;
  private final JPanel histogramPanel;
  private Histogram histogram;
  private final JScrollPane workspace;
  private final JLabel picture;

  public ImageProcessingGUIView() {
    super("Image Processing");

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    setBackground(new Color(51,51,51));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);

    //Creating the MenuBar and adding components
    menu = new JMenuBar();
    menu.setBackground(new Color(132, 132, 132));
    fileMenu = new JMenu("File");
    processMenu = new JMenu("Processes");
    imageTransforms = new JMenu("Image Transforms");
    colorFilters = new JMenu("Color Filters");
    imageFilters = new JMenu("Image Filters");

    processMenu.add(imageTransforms);
    processMenu.add(colorFilters);
    processMenu.add(imageFilters);
    menu.add(fileMenu);
    menu.add(processMenu);


    //Creating the panel at bottom and adding components
    histogramPanel = new JPanel(); // the panel is not visible in output
    histogramPanel.setBackground(new Color(51,51,51));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histograms"));
    JLabel rgbHistogram = new JLabel(new ImageIcon("images/blackhistogramPanel.png", "RGB Histogram"));
    JLabel redHistogram = new JLabel(new ImageIcon("images/redhistogramPanel.png", "Red Histogram"));
    JLabel greenHistogram = new JLabel(new ImageIcon("images/greenhistogramPanel.png", "Green " +
            "Histogram"));
    JLabel blueHistogram = new JLabel(new ImageIcon("images/bluehistogramPanel.png", "Blue Histogram"));
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.PAGE_AXIS));
    histogramPanel.add(rgbHistogram);
    histogramPanel.add(redHistogram);
    histogramPanel.add(greenHistogram);
    histogramPanel.add(blueHistogram);

    histogram = new Histogram();
    histogramPanel.add(histogram);

    // Add Workspace Section
    picture = new JLabel();
    picture.setOpaque(true);
    picture.setBackground(new Color(51,51,51));
    picture.setAlignmentX(Component.CENTER_ALIGNMENT);
    picture.setAlignmentY(Component.CENTER_ALIGNMENT);
    workspace = new JScrollPane(picture);
    workspace.setBackground(new Color(51,51,51));
    workspace.setPreferredSize(new Dimension(1024, 576));
    //workspace = new JScrollPane(new JLabel(new ImageIcon("images/ship-copy.jpg")));

    //Combine all sections into the frame.
    add(BorderLayout.EAST, histogramPanel);
    add(BorderLayout.NORTH, menu);
    add(BorderLayout.CENTER, workspace);
    setVisible(true);
  }

  public void setImage(String filename) {
    Image img;
    try {
      histogram = new Histogram(ImageUtil.convertPNGJPEG(filename));

      img = ImageIO.read(new File(filename));
      img = img.getScaledInstance((int) (picture.getWidth()), (int) (picture.getHeight()),
              Image.SCALE_SMOOTH);
      double wScale = img.getWidth(this) / (double)workspace.getWidth();
      double hScale = img.getHeight(this) / (double)workspace.getHeight();

//      if (wScale >= hScale) {
//        img = img.getScaledInstance(workspace.getWidth(), img.getHeight(this) * (workspace.getHeight() / img.getHeight(this)), Image.SCALE_SMOOTH);
//      } else {
//        img = img.getScaledInstance(img.getWidth(this) * (workspace.getWidth() / img.getWidth(this)), workspace.getHeight(), Image.SCALE_SMOOTH);
//      }

      picture.setIcon(new ImageIcon(img));


    } catch (IOException ex) {
      throw new IllegalArgumentException("Image not found");
    }
  }

  public void addToFileMenu(JMenuItem item) {
    fileMenu.add(item);
  }

  public void addToImageTransforms(JMenuItem item) {
    imageTransforms.add(item);
  }

  public void addToColorFilters(JMenuItem item) {
    colorFilters.add(item);
  }

  public void addToImageFilters(JMenuItem item) {
    imageFilters.add(item);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message);
  }
}
