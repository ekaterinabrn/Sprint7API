package Praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Praktikum.Constant.EndpointConstant.LOGINCOURIER;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginStep {
    @Step("Create courier login")
    public static Response courierLogin(CourierLogin courierLogin) {
        return given().header("Content-type", "application/json")
                .body(courierLogin)
                .when()
                .post(LOGINCOURIER);
    }

    @Step("Success request return status code 200 and  id")
    public void getIDCourier(Response response) {
        response.then().statusCode(200).and().assertThat().body("id", notNullValue());
    }

    @Step("Error status code 400 when request has no login or password")
    public void checkAnswerWithoutLoginOrPassword(Response response) {
        response.then().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Error status code 400 if the username or password is incorrect")
    public void checkAnswerWithoutData(Response response) {
        response.then()
                .statusCode(400).assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
