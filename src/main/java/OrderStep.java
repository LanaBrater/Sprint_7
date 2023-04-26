import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderStep {
    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order order) {
        return given()
                .spec(URL.getRequestSpec())
                .body(order).log().all()
                .when()
                .post(URL.ORDERS_URL).then().log().all();
    }
    @Step("Получение списка всех заказов")
    public static ValidatableResponse getOrders() {
        return given()
                .spec(URL.getRequestSpec())
                .when()
                .get(URL.ORDERS_URL).then().log().all();
    }
}
