package APITest;

import Praktikum.OrderStep;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static Praktikum.Constant.EndpointConstant.URL;

public class GetOrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = URL;

    }

    @Test
    @DisplayName("Get order list response have s list orders in body")
    public void getOrderListNot() {
        //проверено в постмане очень долго получает от сервера список заказов
        OrderStep orderStep = new OrderStep();
        Response responseOrder = orderStep.getOrderList();
        orderStep.orderListNotNull(responseOrder);
        orderStep.orderListIsList(responseOrder);
    }


}
