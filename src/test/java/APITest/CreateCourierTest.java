package APITest;

import Praktikum.Courier;
import Praktikum.CourierStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static Praktikum.Constant.EndpointConstant.URL;
import static Praktikum.Constant.RandomDataCourier.*;

public class CreateCourierTest {
    CourierStep courierStep;
    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        courierStep =new CourierStep();
    }
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    @Test
    @DisplayName("Creating new courier")
    @Description("Creating new courier with correct data and checkins status code")
    public void creatingCourierPositive(){
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourier= courierStep.createCourier(courier);
       courierStep.courierAfterCreationSuccess(createCourier);

    }
    @Test
    @DisplayName("creating a courier if the login is already in use")
    @Description("Creating courier with  existing login checking the response")
    public void creatinCourierWhenLoginAlreadyUsed(){
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        CourierStep.createCourier(courier);
        Response createCourierTwi= courierStep.createCourier(courier);
       courierStep.courierCreationLoginAlreadyUsed(createCourierTwi);
    }
    @Test
    @DisplayName("Creating  courier without login")
    @Description("Creating  courier without login and checking the response")
    public void creatingCourierWithoutLoginBadRequest() {
        Courier courier = new Courier("", RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourierWithoutLogin= courierStep.createCourier(courier);
        courierStep.courierAfterCreationErr(createCourierWithoutLogin);
    }
    @Test
    @DisplayName("Creating  courier without password")
    @Description("Creating  courier without password and checking the response")
    public void creatingCourierWithoutPasswordBadRequest() {
        Courier courier = new Courier(RANDOM_LOGIN,"", RANDOM_FIRSTNAME);
        Response createCourierWithoutPassword= courierStep.createCourier(courier);
        courierStep.courierAfterCreationErr(createCourierWithoutPassword);
    }





}
