
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String firstName = "Наруто";
    private String lastName = "Узумаки";
    private String address = "Москва";
    private String metroStation = "Лубянка";
    private String phone = "89991234567";
    private int rentTime = 2;
    private String deliveryDate = "01-05-2023";
    private String comment = "ааа";
    private List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {List.of("Black")},
                { List.of("Gray")},
                {List.of("Black", "Gray")},
                {null},
        };
    }

    @Test
    @DisplayName("Успешное создание заказа с валидными данными")
    public void successfullyCreateOrderWitchValidDataTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = OrderStep.createOrder(order);
        response.statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}
