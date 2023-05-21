import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CoinPanel extends BasePanel{
    ForeignExchange foreignExchange;
    private JLabel conversion;
    private JTextField textField ;
    private JLabel result ;
    private boolean stop ;

    public CoinPanel (ForeignExchange foreignExchange) {
        super(0, 0, Constants.WINDOW_WIDTH , Constants.WINDOW_HEIGHT, null ,Constants.FILE_NAME);
        this.foreignExchange = foreignExchange;
        this.stop = false ;
        initPanel();
    }

    public void runConversion(String rent) {
        this.conversion.setText("The price is ->" + rent +
                "[from " + foreignExchange.COMPARISON + "to " + this.foreignExchange.getChangeTo() + "]");
    }

    public void run () {
        new Thread(() -> {
            while (!stop){
                try {
                    runConversion(String.valueOf(foreignExchange.rateConversion()));
                    Thread.sleep(Constants.THREAD_SLEEP_TIME);
                }catch (InterruptedException ex ){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void stopPanel () {
        stop = true;
    }

    public JLabel initLabel (String text , int x , int y , int width , int height , int size) {
        JLabel label = new JLabel(text , SwingConstants.CENTER);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial",Font.BOLD , 15));
        label.setForeground(Color.orange.darker());
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }

    public void initPanel () {
        this.conversion = initLabel("", 0 , 0 , Constants.WINDOW_WIDTH , Constants.TITLE_H_POS , Constants.TITLE_H_POS/3 );
        add(conversion);
        run();
        this.textField = new JTextField();
        textField.setBounds(SwingConstants.CENTER, Constants.WINDOW_HEIGHT / 4, Constants.WINDOW_WIDTH , Constants.BUTTON_DISTANCE);
        add(textField);
        this.result = initLabel("Haven't a request yet" , SwingConstants.CENTER , textField.getY() + textField.getHeight() , Constants.WINDOW_WIDTH, Constants.TITLE_H_POS, Constants.TITLE_H_POS/4);
        add(result);
        JLabel message = initLabel("Enter the sum you want to convert. Numbers Only" , textField.getX() , textField.getY() - Constants.BUTTON_DISTANCE , Constants.WINDOW_WIDTH , Constants.BUTTON_DISTANCE , Constants.TITLE_H_POS / 4);
        add(message);
        backBottom();
        getTextFromUser();
    }

    public void getTextFromUser () {
        textField.addActionListener(e -> {
            String textFromUser = textField.getText();
            try {
                float sum = Float.parseFloat(textFromUser);
                this.result.setText(textFromUser + " USD- " + " converted to- " + foreignExchange.getChangeTo() + " is: " + foreignExchange.rateConversion() * sum);
            } catch (NumberFormatException ex) {
                result.setText("Error!Type only numbers!");
            }
        });

        textField.setText(null);
    }

    public void backBottom () {
        Button button = new Button("Back to main menu");
        button.setFont(Constants.FONT);
        button.setBounds(0, Constants.WINDOW_HEIGHT-(Constants.BUTTON_DISTANCE*2), Constants.BUTTON_DISTANCE*2, Constants.BUTTON_DISTANCE*2);
        button.setForeground(Color.black.darker());
        button.setBackground(Color.orange.darker());
        button.addActionListener(e -> {
            try {
                Main main = new Main();
                (SwingUtilities.getAncestorOfClass(JFrame.class, this)).setVisible(false);
                stopPanel();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        add(button);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }
}
