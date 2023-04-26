
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation,
                                        String phone, int rentTime, String deliveryDate, String comment,
                                        List<String> color) {
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

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Наруто", "Узумаки", "Конохагакурэ 1", "Станция 1", "+79123456789", 1, "01-02-2023",
                        "comment", List.of("Black")},
                {"Sasuke", "Uchiha", "Konahagure 2", "Station 2", "8 912 345 67 89", 2, "01-03-2023",
                        "", List.of("Gray")},
                {"Сакура_123", "Харуно", "Конохагакурэ но Сато", "@#$%^", "89123456789", 3, "01-04-2023",
                        "комментарий", List.of("Black", "Gray")},
                {"!@#$%^&*()", "!@#$%^&*()", "Кадзэ но Куни", "", "+7(123)456789", 4, "01-05-2023",
                        ";%:??", null},
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
