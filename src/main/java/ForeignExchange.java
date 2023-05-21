import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.print.Doc;
import java.io.IOException;
import java.security.interfaces.ECKey;
import java.util.*;

public class ForeignExchange {
    public static final String COMPARISON = "USD";
    private final String changeTo;
    private boolean reverse;
    private float value;
    public static final Set<ForeignExchange> ALL_EXCHANGES = new HashSet<>();

    public ForeignExchange (Element element) {
        this.value = Float.parseFloat(Objects.requireNonNull(element.getElementById("p")).text());
        String total = element.getElementsByTag("a").get(0).text();
        int index = total.indexOf(COMPARISON);
        this.changeTo = total.replace(COMPARISON ,"");
        this.reverse = (index != 0);
    }

    public float rateConversion() {
        refresh();
        float value = this.value;
        if (this.reverse) {
            value = 1 / value;
        }
        return value;
    }

    private static ForeignExchange getByCurrency (String Currency) {
        for (ForeignExchange foreignExchange : ALL_EXCHANGES)
            if (foreignExchange.changeTo.equals(Currency))
                return foreignExchange;
        return null;
    }

    public String getChangeTo() {
        return changeTo;
    }

    public static float exchange(String cForm , String cto , float amount ) {
        ForeignExchange form = getByCurrency(cForm);
        ForeignExchange to = getByCurrency(cto);
        if (form == null)
            throw new IllegalArgumentException("currency" + cForm + "doesn't exist");
        if (to == null)
            throw new IllegalArgumentException("Currency" + to + "doesn't exist");

        float asUSD = form.changeFromUSD(amount);
        return to.changeFromUSD(asUSD);
    }

    public static void load () throws IOException {
        Document web = getDocument();
        ArrayList<Element> elements = web.getElementsByClass("datatable-row");
        for (Element element : elements) {
            ForeignExchange temp = new ForeignExchange(element);
            ALL_EXCHANGES.add(temp);
        }
    }

    public void refresh () {
        try {
            Document web = getDocument();
            String symbol ;
            if (reverse) {
                symbol = changeTo + COMPARISON;
            } else {
                symbol = COMPARISON + changeTo;
            }

            symbol += "CUR";

            ArrayList<Element> elements = web.getElementsByAttributeValue("data-symbol", symbol);
            ForeignExchange foreignExchange = new ForeignExchange(elements.get(0));

            this.value = foreignExchange.value;
            this.reverse = foreignExchange.reverse;

        } catch (IOException ignore){

        }
    }

    private static Document getDocument () throws IOException {
        return Jsoup.connect("https://tradingeconomics.com/currencies").get();
    }

    public float changeFromUSD (float source) {
        return source * rateConversion();
    }

    public float changeToUSD (float source_amount) {
        return source_amount / rateConversion();
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) return  true;
       if (!(o instanceof ForeignExchange that)) return false;
       return reverse == that.reverse && changeTo.equals(that.changeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(changeTo , reverse);
    }

    @Override
    public String toString() {
        return "changeTo='" + changeTo + '\'' + ", reverse=" + reverse + ", value=" + value ;
    }
}
