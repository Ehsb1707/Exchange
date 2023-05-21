import javax.swing.*;

public class BasicWindow extends JFrame {
    public BasicWindow (int w , int h) {
        setSize(w , h);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
