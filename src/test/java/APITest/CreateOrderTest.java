package APITest;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static Praktikum.Constant.EndpointConstant.URL;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{{"Имя Один", "Фамилия", "Адрес 1", "Красные ворота", "79991111111", 1, "2024-09-25", "black", List.of("BLACK")},
                {"Имя Два", "Фамилия", "Адрес 2", "Чистые пруды", "79992222222", 2, "2024-09-25", "grey", List.of("GREY")},
                {"Имя Три", "Фамилия", "Адрес 3", "Охотный ряд", "79993333333", 3, "2024-09-25", "black and grey", List.of("BLACK", "GREY")},
                {"Имя Четыре", "Фамилия", "Адрес 4", "Соколиная гора", "79994444444", 4, "2024-09-25", "не указан", List.of("")},
                {"Имя Пять", "Фамилия", "Адрес 5", "Красные ворота", "79995555555", 5, "2023-09-25", "null", null}};

    }


@Before
    public void setUp() {
        RestAssured.baseURI = URL;
    }

}
