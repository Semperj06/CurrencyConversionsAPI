


import Constant.Constants;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class BasicRessponse {
    private static Response response;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BasicRessponse.class);

    @Test
    public static void verifysecurity(String token) {
        response = given().get(Constants.URL+Constants.TIME_ENDPOINT+token);
        response.then().statusCode(200);
        Logger.info(response);
    }

}
