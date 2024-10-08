package APITest;

import Praktikum.Client.CourierClient;
import Praktikum.Client.LogINClient;
import Praktikum.Courier;
import Praktikum.CourierLogin;
import Praktikum.CourierLoginStep;
import Praktikum.Credentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Praktikum.Constant.EndpointConstant.URL;
import static Praktikum.Constant.RandomDataCourier.*;


public class LoginCourierTest {
   CourierLoginStep courierLoginStep = new CourierLoginStep();
   Courier courier ;



    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
      courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
     CourierClient.createCourier(courier);

    }


 @DisplayName("Courier login is success")
    @Description("Success request return status code 200 and  id")
    @Test
    public void courierLoginSuccess() {
         CourierLogin courierLogin = new CourierLogin(RANDOM_LOGIN, RANDOM_PASS);
         Response loginResponse = LogINClient.courierLogin(courierLogin);
courierLoginStep.responseHaveIDCourier(loginResponse);
    }

    @DisplayName("Courier login is wrong")
    @Description("request return status code 404 and   message")
    @Test
    public void courierLoginWrong() {

        CourierLogin courierLogin = new CourierLogin("wrong", RANDOM_PASS);
        Response wrongLogin = LogINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutLoginOrPassword(wrongLogin);
    }

    @DisplayName("Courier password is wrong")
    @Description("request return status code 404 and   message")
    @Test
    public void courierPasswordWrong() {

        CourierLogin courierLogin = new CourierLogin(RANDOM_LOGIN, "wrong");
        Response wrongPassword = LogINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutLoginOrPassword(wrongPassword);
    }

    @Test
    @DisplayName("login courier  without courierLogin")
    @Description("login without courierLogin and request return status code 400")
    public void courierLoginWithoutLogin() {
        CourierLogin courierLogin = new CourierLogin("", RANDOM_PASS);
        Response courierWithoutLoginAuthorization = LogINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutLoginAuthorization);

    }

    @Test
    @DisplayName("login courier  without password")
    @Description("login without courier password and request return status code 400")
    public void courierLoginWithoutPassword() {
        CourierLogin courierLogin = new CourierLogin(RANDOM_LOGIN, "");
        Response courierWithoutPasswordAuthorization = LogINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutPasswordAuthorization);

    }
    @Test
    @DisplayName("login courier  without data")
    @Description("login without data and request return status code 400")
    public void courierLoginWithoutData() {
        CourierLogin courierLogin = new CourierLogin("", "");
        Response courierWithoutPasswordAuthorization = LogINClient.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutData(courierWithoutPasswordAuthorization);

    }


@After
public void deleteCourier(){
  //курьер создан до теста с нормальным логином в тесте  намеренно путали данные-вызываем валидный логин и удаляем
    Credentials creds= Credentials.fromCourier(courier);
    Response loge = LogINClient.courierLoginCredit(creds);
    courierLoginStep.responseHaveIDCourier(loge);
    int courierID = courierLoginStep.getIDFOrDeleting(loge);
    if (courierID != 0)  {
        CourierClient.deleteCourier(courierID);

    }

}

}

