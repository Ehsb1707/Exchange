import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Supplier;

public class Main extends BasicWindow {
    private MainScreenPanel mainScreen;

    public Main () throws IOException {
        super(Constants.WINDOW_WIDTH , Constants.WINDOW_HEIGHT);
        ForeignExchange.load();
        mainScreen = new MainScreenPanel();
        add(mainScreen);
        myBottom();
        setVisible(true);
    }

    public void myBottom () {
        int y = Constants.BUTTON_DISTANCE;
        int x = Constants.BUTTON_DISTANCE;
        int counter = 0 ;

        ArrayList<ForeignExchange> allExchanges = new ArrayList<>(ForeignExchange.ALL_EXCHANGES);
        allExchanges.sort(Comparator.comparing(ForeignExchange :: getChangeTo));

        for (ForeignExchange foreignExchange : allExchanges ) {
            mainBottoms(x ,y , Constants.BUTTON_WIDTH , Constants.BUTTON_HEIGHT , ForeignExchange.COMPARISON + "-" + foreignExchange.getChangeTo()
            , () -> new CoinPanel(foreignExchange));
            counter ++ ;
            x += Constants.BUTTON_WIDTH;
            if (counter == Constants.ALL_EXCHANGE_IN_ROW) {
                counter = 0 ;
                x = Constants.BUTTON_DISTANCE ;
                y += Constants.BUTTON_HEIGHT;
            }
        }
    }


    public void mainBottoms (int x , int y , int width , int height , String titleOn , Supplier<CoinPanel> supplier) {
        Button button = new Button(titleOn);
        button.setFont(Constants.FONT);
        button.setBounds(x , y , width , height );
        button.setForeground(Color.BLACK.darker());
        button.setBackground(Color.orange.darker());
        button.addActionListener(e -> {
            CoinPanel cp = supplier.get();
            add(cp);
            cp.setVisible(true);
            mainScreen.setVisible(false);
        });

        mainScreen.add(button);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
    }
}
