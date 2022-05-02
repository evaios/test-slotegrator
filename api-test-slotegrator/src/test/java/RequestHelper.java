import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;


class RequestHelper {

    private static RequestSpecBuilder requestSpecificationBuilder;

    static RequestSpecBuilder getRequestSpecificationBuilder() {
        createRequestSpecification();
        return requestSpecificationBuilder;
    }

    private static void createRequestSpecification() {
        requestSpecificationBuilder = new RequestSpecBuilder()
                .setBaseUri(ConstantsVariables.BASE_URI)
                .setBasePath(ConstantsVariables.BASE_PATH)
                .addHeader("Accept", ContentType.JSON.getAcceptHeader());
    }

    static Response auth(String grantTypeValue, Map<String, ?> authParams) {
        createRequestSpecification();

        RequestSpecification authParamsSpecification = requestSpecificationBuilder
                .addParams(authParams)
                .build();
        return given().urlEncodingEnabled(true)
                .filter(new AllureRestAssured())
                .auth()
                .preemptive()
                .basic(ConstantsVariables.BASIC_AUTH, "")
                .param("grant_type", grantTypeValue)
                .spec(authParamsSpecification)
                .log()
                .all()
                .post(ConstantsVariables.OAUTH2);
    }
}