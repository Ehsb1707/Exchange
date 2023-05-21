import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {
    private ImageIcon image ;

    // Constructor
    public BasePanel (int x , int y , int w , int h , Color color , String fileName) {
        setBounds(x, y, w, h);
        setBackground(color);
        image = new ImageIcon(fileName);
        init();
    }

    protected void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);
        image.paintIcon(this,graphics,0,0);
    }

    public void init() {
        setLayout(null);
        setDoubleBuffered(true);
    }
}
