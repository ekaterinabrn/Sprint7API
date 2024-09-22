package Praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Praktikum.Constant.EndpointConstant.CREATEORDER;
import static Praktikum.Constant.EndpointConstant.ORDERSLIST;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderStep {
    @Step("Create new order")
    public Response createOrder(CreateOrder order) {
        return given().header("Content-type", "application/json")
                .body(order)
                .when()
                .post(CREATEORDER);

    }

    @Step("After create order body have status code 201 and track ")
    public void getOrderTrack(Response response) {
        response.then().statusCode(201).and().assertThat().body("track", notNullValue());

    }

    @Step("Get order list")
    public Response getOrderList() {
        return given().get(ORDERSLIST);
    }

    @Step("List of orders is not empty and status code 200")
    public void orderListNotNull(Response response) {
        response.then()
                .statusCode(200).and().assertThat().body("orders", notNullValue());
    }
}
