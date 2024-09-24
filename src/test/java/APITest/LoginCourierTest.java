package APITest;

import Praktikum.Client.CourierClient;
import Praktikum.Client.LogINClient;
import Praktikum.Courier;
import Praktikum.CourierLogin;
import Praktikum.CourierLoginStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static Praktikum.Constant.EndpointConstant.URL;
import static Praktikum.Constant.RandomDataCourier.*;


public class LoginCourierTest {
    CourierLoginStep courierLoginStep;
    LogINClient logINClient;
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
        courierLoginStep = new CourierLoginStep();
        CourierClient courierClient = new CourierClient();
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourier = courierClient.createCourier(courier);
        // получаем созданные логин и пароль
        login = courier.getLogin();
        password = courier.getPassword();
        firstName = courier.getFirstName();
    }


    @DisplayName("Courier login is success")
    @Description("Success request return status code 200 and  id")
    @Test
    public void courierLoginSuccess() {
        CourierLogin courierLogin = new CourierLogin(login, password);

        Response loginResponse = logINClient.courierLogin(courierLogin);
        courierLoginStep.getIDCourier(loginResponse);
    }

    @DisplayName("Courier login is wrong")
    @Description("request return status code 404 and   message")
    @Test
    public void courierLoginWrong() {

        CourierLogin courierLogin = new CourierLogin("wrong", password);
        Response wrongLogin = logINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutLoginOrPassword(wrongLogin);
    }

    @DisplayName("Courier password is wrong")
    @Description("request return status code 404 and   message")
    @Test
    public void courierPasswordWrong() {

        CourierLogin courierLogin = new CourierLogin(login, "wrong");
        Response wrongPassword = logINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutLoginOrPassword(wrongPassword);
    }

    @Test
    @DisplayName("login courier  without courierLogin")
    @Description("login without courierLogin and request return status code 400")
    public void courierLoginWithoutLogin() {
        CourierLogin courierLogin = new CourierLogin("", password);
        Response courierWithoutLoginAuthorization = logINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutLoginAuthorization);

    }

    @Test
    @DisplayName("login courier  without password")
    @Description("login without courier password and request return status code 400")
    public void courierLoginWithoutPassword() {
        CourierLogin courierLogin = new CourierLogin(login, "");
        Response courierWithoutPasswordAuthorization = logINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutPasswordAuthorization);

    }
    @Test
    @DisplayName("login courier  without data")
    @Description("login without data and request return status code 400")
    public void courierLoginWithoutData() {
        CourierLogin courierLogin = new CourierLogin("", "");
        Response courierWithoutPasswordAuthorization = logINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutPasswordAuthorization);

    }

}

