


import Constant.Constants;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import io.restassured.response.Response;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class BasicResponse {
    public static Response response;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BasicResponse.class);

    public void verifysecurity(String time ,String token, String... point) {
        String url;
        if ((point.length == 0)){
             url = Constants.URL + time + token;
        }
        else {
            String arrayString = Arrays.toString(point);
            String result = arrayString.replaceAll("[\\[\\]]", "");
             url = Constants.URL + time + token + result;
        }
        response = given().get(url);
        Logger.info("Status code: " + response.getStatusCode());
    }
    public void performance() {
        response.then().time(lessThan(8000l));
        Logger.info("We get response less then 8 sec");
    }
    public void endpointNotNUL(String endpoint) {
        response.then().body(endpoint, notNullValue());
        Logger.info("Endpoint " + endpoint + " exist and value not null");
    }
    public void currenciesNotNUL(String endpoint, String currency) {
        response.then().body(endpoint+"."+currency, notNullValue());
        Logger.info("Currency " + currency + " exist and value not null");
    }
    public void getBoolean(String endpoint) {
        assertThat(response.jsonPath().getBoolean(endpoint), equalTo(true));
        Logger.info("Endpoint " + endpoint + " value equalTo 'TRUE'");
    }
    public void compareValue(String endpoint, String value){
        String valueWithoutQuotes = value.replace("\"", "");
        response.then().body(endpoint, equalTo(valueWithoutQuotes));
        Logger.info("Endpoint " + endpoint + " contain  value " + value);

    }


}
