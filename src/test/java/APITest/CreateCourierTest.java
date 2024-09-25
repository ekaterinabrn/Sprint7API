package APITest;

import Praktikum.Client.CourierClient;
import Praktikum.Client.LogINClient;
import Praktikum.Courier;
import Praktikum.CourierLoginStep;
import Praktikum.CourierStep;
import Praktikum.Credentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static Praktikum.Constant.EndpointConstant.URL;
import static Praktikum.Constant.RandomDataCourier.*;

public class CreateCourierTest {
    CourierStep courierStep= new CourierStep();
    CourierClient courierClient =new CourierClient();
    private int courierID;
    CourierLoginStep courierLoginStep=new CourierLoginStep();

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;

    }

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    @DisplayName("Creating new courier")
    @Description("Creating new courier with correct data and checkins status code")
    public void creatingCourierPositive() {
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourier = courierClient.createCourier(courier);
        courierStep.courierAfterCreationSuccess(createCourier);
        Credentials creds= Credentials.fromCourier(courier);
        Response loge = LogINClient.courierLoginCredit(creds);
        courierLoginStep.getIDCourier(loge);
        this.courierID = courierLoginStep.getIDFOrDeleting(loge);


    }

   @Test
    @DisplayName("creating a courier if the login is already in use")
    @Description("Creating courier with  existing login checking the response")
    public void creatingCourierWhenLoginAlreadyUsed() {
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        CourierClient.createCourier(courier);
        Response createCourierTwi = courierClient.createCourier(courier);
        courierStep.courierCreationLoginAlreadyUsed(createCourierTwi);
    }

    @Test
    @DisplayName("Creating  courier without login")
    @Description("Creating  courier without login and checking the response")
    public void creatingCourierWithoutLoginBadRequest() {
        Courier courier = new Courier("", RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourierWithoutLogin = courierClient.createCourier(courier);
        courierStep.courierAfterCreationErr(createCourierWithoutLogin);
    }

    @Test
    @DisplayName("Creating  courier without password")
    @Description("Creating  courier without password and checking the response")
    public void creatingCourierWithoutPasswordBadRequest() {
        Courier courier = new Courier(RANDOM_LOGIN, "", RANDOM_FIRSTNAME);
        Response createCourierWithoutPassword = courierClient.createCourier(courier);
        courierStep.courierAfterCreationErr(createCourierWithoutPassword);
    }
    @After
    public void deleteCourier(){

        if (courierID != 0)  {
            courierClient.deleteCourier(courierID);

        }

    }


}
