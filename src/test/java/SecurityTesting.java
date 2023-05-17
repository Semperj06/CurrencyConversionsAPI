import Constant.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.core.CombinableMatcher.either;
import static org.hamcrest.core.IsEqual.equalTo;

public class SecurityTesting extends BasicResponse {


    @Test
    public void VerifySecurityTesting() {
        verifysecurity(Constants.TIME_ENDPOINT,Constants.TOKEN);
        response.then().statusCode(200);
        performance();
    }
    @ParameterizedTest
    @ValueSource(strings = {"success", "terms", "privacy", "timestamp", "source", "quotes"})
    public void EndpointsTesting(String endpoint) {
        verifysecurity(Constants.TIME_ENDPOINT,Constants.TOKEN);
        endpointNotNUL(endpoint);
        performance();
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "apikey=", "apikey=few324rfd2", "apikey=452354332"})
    public void VerifySecurityNegativeTesting(String wrong_token) {
        verifysecurity(wrong_token, Constants.TOKEN);
        response.then().statusCode(either(equalTo(101)).or(equalTo(401)));
        performance();
    }
    @ParameterizedTest
    @ValueSource(strings = {"USDCAD", "USDEUR", "USDNIS", "USDRUB"})
    public void MainCurrenciesTesting(String currency) {
        verifysecurity(Constants.TIME_ENDPOINT,Constants.TOKEN);
        currenciesNotNUL("quotes", currency);
        performance();
    }
    @ParameterizedTest
    @ValueSource(strings = {"CAD", "EUR", "NIS", "RUB"})
    public void OptionalParameterCurrenciesTesting(String currency) {
        String source = "USD";
        verifysecurity(Constants.TIME_ENDPOINT, Constants.TOKEN,Constants.SPLIT+Constants.BASIC_CURRENCY+source+ Constants.SPLIT+ Constants.EXCHANGE_RATES+currency);
        endpointNotNUL("success");
        getBoolean("success");
        endpointNotNUL("timestamp");
        endpointNotNUL("source");
        compareValue("source", source);
        currenciesNotNUL("quotes", source+currency);
        performance();
    }
    @ParameterizedTest
    @ValueSource(strings = {"2018-01-01", "2010-01-01", "2005-04-01", "2001-10-01"})
    public void OptionalParameterDate(String date) {
        String time_endpoint = Constants.HISTORICAL_ENDPOINT + date + Constants.SPLIT;
        verifysecurity(time_endpoint, Constants.TOKEN);
        endpointNotNUL("success");
        getBoolean("success");
        endpointNotNUL("historical");
        getBoolean("historical");
        compareValue("date", date);
        endpointNotNUL("timestamp");
        compareValue("source", "USD");
        currenciesNotNUL("quotes", "USDCAD");
        currenciesNotNUL("quotes",  "USDEUR");
        currenciesNotNUL("quotes", "USDNIS");
        currenciesNotNUL("quotes",  "USDRUB");
        performance();
    }






}
