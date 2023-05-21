import javax.swing.*;
import java.awt.*;

public class MainScreenPanel extends BasePanel{

    public MainScreenPanel () {
        super(0,0,Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null,Constants.FILE_NAME);
        myTitle();
    }

    public void myTitle () {
        JLabel title = new JLabel("Exchange option :" , SwingConstants.CENTER);
        title.setFont(new Font("arial" , Font.BOLD , 35));
        title.setBackground(Color.BLACK.darker());
        title.setOpaque(true);
        title.setForeground(Color.orange);
        title.setBounds(0 , Constants.TITLE_Y_POS , Constants.WINDOW_WIDTH , Constants.TITLE_H_POS);
        add(title);
    }
}
