import java.awt.*;

public class Constants {
    public static int WINDOW_WIDTH = 1000 ;
    public static int WINDOW_HEIGHT = 650 ;
    public static final int ALL_EXCHANGE_IN_ROW = 5 ;
    public static final int BUTTON_DISTANCE = 100 ;
    public static final int BUTTON_WIDTH = (WINDOW_WIDTH-(BUTTON_DISTANCE*2))/ALL_EXCHANGE_IN_ROW;
    public static final int BUTTON_HEIGHT = (WINDOW_HEIGHT-(BUTTON_DISTANCE+BUTTON_DISTANCE/2))/16;
    public static final int TITLE_Y_POS = 0 ;
    public static final int TITLE_H_POS = 100 ;
    public static final Font FONT = new Font("Arial", Font.BOLD , 15) ;
    public static final int THREAD_SLEEP_TIME = 1000 ;
    public static final String FILE_NAME = "Exchange/src/main/resources/pic.jpeg";
}
