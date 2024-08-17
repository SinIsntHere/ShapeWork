import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Model class
class Model {
    private static Model instance;

    private String iconType = "Rectangle";
    private Color iconColor = Color.RED;
    private int iconSize = 50;

    // New fields for ellipse and triangle
    private int ellipseWidth = 50;
    private int ellipseHeight = 50;
    private int triangleBase = 50;
    private int triangleHeight = 50;

    private Model() {}

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public Color getIconColor() {
        return iconColor;
    }

    public void setIconColor(Color iconColor) {
        this.iconColor = iconColor;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    // Getters and setters for ellipse width and height
    public int getEllipseWidth() {
        return ellipseWidth;
    }

    public void setEllipseWidth(int ellipseWidth) {
        this.ellipseWidth = ellipseWidth;
    }

    public int getEllipseHeight() {
        return ellipseHeight;
    }

    public void setEllipseHeight(int ellipseHeight) {
        this.ellipseHeight = ellipseHeight;
    }

    // Getters and setters for triangle base and height
    public int getTriangleBase() {
        return triangleBase;
    }

    public void setTriangleBase(int triangleBase) {
        this.triangleBase = triangleBase;
    }

    public int getTriangleHeight() {
        return triangleHeight;
    }

    public void setTriangleHeight(int triangleHeight) {
        this.triangleHeight = triangleHeight;
    }
}

// View class
class View {
    private JFrame frame;
    private JPanel iconPanel;
    private JButton rectangleButton, ellipseButton, triangleButton;
    private JButton redButton, blueButton, yellowButton;
    private JSlider sizeSlider;
    private JSlider ellipseWidthSlider, ellipseHeightSlider;
    private JSlider triangleBaseSlider, triangleHeightSlider;

    public View() {
        frame = new JFrame("Icon GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw icon based on model
                if (Model.getInstance().getIconType().equals("Rectangle")) {
                    g.setColor(Model.getInstance().getIconColor());
                    int size = Model.getInstance().getIconSize();
                    g.fillRect((getWidth() - size) / 2, (getHeight() - size) / 2, size, size);
                } else if (Model.getInstance().getIconType().equals("Ellipse")) {
                    g.setColor(Model.getInstance().getIconColor());
                    int width = Model.getInstance().getEllipseWidth();
                    int height = Model.getInstance().getEllipseHeight();
                    g.fillOval((getWidth() - width) / 2, (getHeight() - height) / 2, width, height);
                } else if (Model.getInstance().getIconType().equals("Triangle")) {
                    g.setColor(Model.getInstance().getIconColor());
                    int[] xPoints = {(getWidth() - Model.getInstance().getTriangleBase()) / 2,
                            (getWidth() + Model.getInstance().getTriangleBase()) / 2,
                            getWidth() / 2};
                    int[] yPoints = {(getHeight() + Model.getInstance().getTriangleHeight()) / 2,
                            (getHeight() + Model.getInstance().getTriangleHeight()) / 2,
                            (getHeight() - Model.getInstance().getTriangleHeight()) / 2};
                    g.fillPolygon(xPoints, yPoints, 3);
                }
            }
        };
        frame.add(iconPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(3, 1));

        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconType("Rectangle");
                iconPanel.repaint();
            }
        });

        ellipseButton = new JButton("Ellipse");
        ellipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconType("Ellipse");
                iconPanel.repaint();
            }
        });

        triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconType("Triangle");
                iconPanel.repaint();
            }
        });

        redButton = new JButton("Red");
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconColor(Color.RED);
                iconPanel.repaint();
            }
        });

        blueButton = new JButton("Blue");
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconColor(Color.BLUE);
                iconPanel.repaint();
            }
        });

        yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.getInstance().setIconColor(Color.YELLOW);
                iconPanel.repaint();
            }
        });

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getInstance().setIconSize(sizeSlider.getValue());
                iconPanel.repaint();
            }
        });

        ellipseWidthSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, 50);
        ellipseWidthSlider.setMajorTickSpacing(20);
        ellipseWidthSlider.setPaintTicks(true);
        ellipseWidthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getInstance().setEllipseWidth(ellipseWidthSlider.getValue());
                iconPanel.repaint();
            }
        });

        ellipseHeightSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, 50);
        ellipseHeightSlider.setMajorTickSpacing(20);
        ellipseHeightSlider.setPaintTicks(true);
        ellipseHeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getInstance().setEllipseHeight(ellipseHeightSlider.getValue());
                iconPanel.repaint();
            }
        });

        triangleBaseSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, 50);
        triangleBaseSlider.setMajorTickSpacing(20);
        triangleBaseSlider.setPaintTicks(true);
        triangleBaseSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getInstance().setTriangleBase(triangleBaseSlider.getValue());
                iconPanel.repaint();
            }
        });

        triangleHeightSlider = new JSlider(JSlider.HORIZONTAL, 10, 200, 50);
        triangleHeightSlider.setMajorTickSpacing(20);
        triangleHeightSlider.setPaintTicks(true);
        triangleHeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Model.getInstance().setTriangleHeight(triangleHeightSlider.getValue());
                iconPanel.repaint();
            }
        });

        controlPanel.add(rectangleButton);
        controlPanel.add(ellipseButton);
        controlPanel.add(triangleButton);
        controlPanel.add(redButton);
        controlPanel.add(blueButton);
        controlPanel.add(yellowButton);
        controlPanel.add(new JLabel("Icon Size:"));
        controlPanel.add(sizeSlider);
        controlPanel.add(new JLabel("Ellipse Width:"));
        controlPanel.add(ellipseWidthSlider);
        controlPanel.add(new JLabel("Ellipse Height:"));
        controlPanel.add(ellipseHeightSlider);
        controlPanel.add(new JLabel("Triangle Base:"));
        controlPanel.add(triangleBaseSlider);
        controlPanel.add(new JLabel("Triangle Height:"));
        controlPanel.add(triangleHeightSlider);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// Controller class
class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }
}

// Main class
public class IconApp {
    public static void main(String[] args) {
        Model model = Model.getInstance();
        View view = new View();
        Controller controller = new Controller(view, model);
    }
}
