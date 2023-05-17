import Constant.Constants;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EndpointsFunctionalityTesting extends BasicResponse {


    @Test
    public void VerifySecurityTesting() {
        verifysecurity(Constants.TOKEN);
        response.then().statusCode(200);
    }


}
