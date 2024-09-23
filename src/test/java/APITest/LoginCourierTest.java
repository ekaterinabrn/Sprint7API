package APITest;

import Praktikum.Courier;
import Praktikum.CourierLogin;
import Praktikum.CourierLoginStep;
import Praktikum.CourierStep;
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
    CourierStep courierStep;
    private String login;
    private String password;
    private String firstName;
    @Before
    public void setUp() {
        RestAssured.baseURI = URL;
courierLoginStep= new CourierLoginStep();
        Courier courier = new Courier(RANDOM_LOGIN, RANDOM_PASS, RANDOM_FIRSTNAME);
        Response createCourier= courierStep.createCourier(courier);
        // получаем созданные логин и пароль
        login = courier.getLogin();
        password = courier.getPassword();
        firstName = courier.getFirstName();
    }
    @DisplayName("Courier login is success")
    @Description("Success request return status code 200 and  id")
    @Test
    public void courierLoginSuccess(){
        CourierLogin courierLogin = new CourierLogin(login, password);

Response loginResponse = courierLoginStep.courierLogin(courierLogin);
       courierLoginStep.getIDCourier(loginResponse);
    }
    @Test
    public void courierLoginWrong(){

        CourierLogin courierLogin = new CourierLogin("wrong", password);
        Response wrongLogin =courierLoginStep.courierLogin(courierLogin);
        courierLoginStep.checkAnswerWithoutLoginOrPassword(wrongLogin);
    }
}
