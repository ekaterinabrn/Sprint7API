package Praktikum.Client;

import Praktikum.CreateOrder;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Praktikum.Constant.EndpointConstant.CREATE_ORDER;
import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Create new order")
    public Response createOrder(CreateOrder order) {
        return given().header("Content-type", "application/json")
                .body(order)
                .when()
                .post(CREATE_ORDER);

    }
}
