package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import util.ImageUtil;

/**
 * This class represents a GUI view of this image processing program.
 */
public class ImageProcessingGUIView extends JFrame implements ImageProcessingGUI  {
  private final JMenu fileMenu;
  private final JMenu imageTransforms;
  private final JMenu colorFilters;
  private final JMenu imageFilters;
  private final JPanel histogramPanel;
  private Histogram histogram;
  private final JLabel picture;
  private List<JMenuItem> fileItems;
  private List<JMenuItem> transformItems;
  private List<JMenuItem> colorFilterItems;
  private List<JMenuItem> imageFilterItems;
  private List<JMenuItem> allMenuItems;

  /**
   * Constructs a new GUI View.
   */
  public ImageProcessingGUIView() {
    super("Image Processing");

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    fileItems = new ArrayList<>();
    transformItems = new ArrayList<>();
    colorFilterItems = new ArrayList<>();
    imageFilterItems = new ArrayList<>();
    allMenuItems = new ArrayList<>();

    fileItems.add(new JMenuItem("Open"));
    fileItems.add(new JMenuItem("Save"));
    fileItems.add(new JMenuItem("Save As"));
    transformItems.add(new JMenuItem("Flip (Horizontal)"));
    transformItems.add(new JMenuItem("Flip (Vertical)"));
    transformItems.add(new JMenuItem("Downscale"));
    colorFilterItems.add(new JMenuItem("Adjust Red"));
    colorFilterItems.add(new JMenuItem("Adjust Green"));
    colorFilterItems.add(new JMenuItem("Adjust Blue"));
    colorFilterItems.add(new JMenuItem("Adjust Brightness"));
    colorFilterItems.add(new JMenuItem("Invert Colors"));
    colorFilterItems.add(new JMenuItem("Sepia Tone"));
    colorFilterItems.add(new JMenuItem("Greyscale (Red)"));
    colorFilterItems.add(new JMenuItem("Greyscale (Green)"));
    colorFilterItems.add(new JMenuItem("Greyscale (Blue)"));
    colorFilterItems.add(new JMenuItem("Greyscale (Value)"));
    colorFilterItems.add(new JMenuItem("Greyscale (Intensity)"));
    colorFilterItems.add(new JMenuItem("Greyscale (Luma)"));
    imageFilterItems.add(new JMenuItem("Box Blur"));
    imageFilterItems.add(new JMenuItem("Gaussian Blur"));
    imageFilterItems.add(new JMenuItem("Emboss"));
    imageFilterItems.add(new JMenuItem("Sharpen"));
    imageFilterItems.add(new JMenuItem("Ridge Detection"));


    setBackground(new Color(51,51,51));
    setPreferredSize(new Dimension(1280, 720));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);

    //Creating the MenuBar and adding components
    JMenuBar menu = new JMenuBar();
    menu.setBackground(new Color(132, 132, 132));
    fileMenu = new JMenu("File");
    JMenu processMenu = new JMenu("Processes");
    imageTransforms = new JMenu("Image Transforms");
    colorFilters = new JMenu("Color Filters");
    imageFilters = new JMenu("Image Filters");

    processMenu.add(imageTransforms);
    processMenu.add(colorFilters);
    processMenu.add(imageFilters);
    menu.add(fileMenu);
    menu.add(processMenu);


    allMenuItems.addAll(fileItems);
    allMenuItems.addAll(transformItems);
    allMenuItems.addAll(colorFilterItems);
    allMenuItems.addAll(imageFilterItems);
    for (JMenuItem i : allMenuItems) {
      i.setActionCommand(i.getName());
    }

    addItemsToBar();

    //Creating the panel at bottom and adding components
    histogramPanel = new JPanel(); // the panel is not visible in output
    histogramPanel.setBackground(new Color(51,51,51));
    histogramPanel.setPreferredSize(new Dimension(500, 500));
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histograms"));
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.PAGE_AXIS));
    histogram = new Histogram();
    histogramPanel.add(histogram);
    pack();

    // Add Workspace Section
    picture = new JLabel();
    picture.setOpaque(true);
    picture.setBackground(new Color(51,51,51));
    picture.setAlignmentX(Component.CENTER_ALIGNMENT);
    picture.setAlignmentY(Component.CENTER_ALIGNMENT);
    JScrollPane workspace = new JScrollPane(picture);
    workspace.setBackground(new Color(51,51,51));
    workspace.setPreferredSize(new Dimension(1024, 576));

    //Combine all sections into the frame.
    add(BorderLayout.EAST, histogramPanel);
    add(BorderLayout.NORTH, menu);
    add(BorderLayout.CENTER, workspace);
    setVisible(true);
  }

  @Override
  public void setImage(String filename) {
    Image img;
    try {
      histogram.setImg(ImageUtil.convertPNGJPEG(filename));

      img = ImageIO.read(new File(filename));

      picture.setIcon(new ImageIcon(img));
      histogramPanel.add(histogram);


    } catch (IOException ex) {
      throw new IllegalArgumentException("Image not found");
    }
  }

  /**
   * Adds all items in the current lists to the menu bars.
   */
  private void addItemsToBar() {
    for (JMenuItem i : this.imageFilterItems) {
      imageFilters.add(i);
    }

    for (JMenuItem i : this.transformItems) {
      imageTransforms.add(i);
    }

    for (JMenuItem i : this.fileItems) {
      fileMenu.add(i);
    }

    for (JMenuItem i : this.colorFilterItems) {
      colorFilters.add(i);
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void setListener(ActionListener listener) {
    for (JMenuItem i : this.allMenuItems) {
      i.addActionListener(listener);
    }
  }
}
