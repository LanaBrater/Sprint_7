import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierStep {
    @Step("Регистрация курьера")
    public static ValidatableResponse create(Courier courier) {
        return given()
                .spec(URL.getRequestSpec())
                .when()
                .body(courier).log().all()
                .post(URL.COURIER_URL).then().log().all();
    }

    @Step("Авторизация курьера")
    public static ValidatableResponse login(Courier courier) {
        return given()
                .spec(URL.getRequestSpec())
                .when()
                .body(courier).log().all()
                .post(URL.COURIER_LOGIN).then().log().all();
    }

    @Step("Удаление курьера")
    public static ValidatableResponse delete(String courierId) {
        return given()
                .spec(URL.getRequestSpec())
                .when().log().all()
                .delete(URL.COURIER_URL + courierId).then().log().all();
    }
}
