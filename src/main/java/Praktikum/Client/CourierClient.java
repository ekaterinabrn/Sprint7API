package Praktikum.Client;

import Praktikum.Courier;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Praktikum.Constant.EndpointConstant.CREATE_COURIER;
import static io.restassured.RestAssured.given;

public class CourierClient {
    @Step("Create courier")
    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(CREATE_COURIER);
    }
}
