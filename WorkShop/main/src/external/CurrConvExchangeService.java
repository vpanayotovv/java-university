package external;

import domain.entities.Money;
import domain.external.Exchange;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrConvExchangeService implements Exchange {
    private static final String API_KEY = System.getenv("API_KEY");

    @Override
    public Money convert(Money from, String toCurrency) {
        BigDecimal rate = fetchExchangeRateFor(from.getCurrency(), toCurrency);
        BigDecimal exchangedValue = from.getValue().multiply(rate);
        return new Money(exchangedValue, toCurrency);
    }

    private BigDecimal fetchExchangeRateFor(String currencyFrom, String currencyTo) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(buildRequestUrl(currencyFrom, currencyTo)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return extractExchangeRateFrom(response.body());
        } catch (Exception e) {
            throw new RuntimeException("something went wrong");
        }
    }

    private BigDecimal extractExchangeRateFrom(String response) {
        int beginIndex = response.lastIndexOf(":");
        int closeIndex = response.lastIndexOf("}");
        String exchangeRate = response.substring(beginIndex + 1, closeIndex);
        return new BigDecimal(exchangeRate);
    }

    private URI buildRequestUrl(String from, String to) {
        String url = String.format("https://free.currconv.com/api/v7/convert?apiKey=%s&q=%s_%s&compact=ultra", API_KEY, from, to);
        try {

            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("failed to build URI");
        }
    }
}
