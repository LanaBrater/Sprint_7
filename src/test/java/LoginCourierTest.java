import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTest {

    Courier courier;
    String id;

    @Before
    public void setUp() {
        courier = Courier.generateCourierRandom();
        CourierStep.create(courier);
        id = given()
                .spec(URL.getRequestSpec())
                .when()
                .body(courier)
                .post(URL.COURIER_URL + "login")
                .then().extract().path("id").toString();
    }

    @After
    public void cleanUp() {
        if (id != null) {
            CourierStep.delete(id);
        }
    }

    @Test
    @DisplayName("Успешный логин в системе")
    public void successfullyLoginTest() {
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(200)
                .assertThat()
                .body("id", is(notNullValue()));
    }

    @Test
    @DisplayName("Авторизация незарегистрированного курьера")
    public void loginWithNonExistentCourierTest() {
        courier = Courier.generateCourierRandom();
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(404)
                .assertThat()
                .body("code", equalTo(404))
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация c неверным логином")
    public void loginWithInvalidLoginTest() {
        courier.setLogin("unknow");
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(404)
                .assertThat()
                .body("code", equalTo(404))
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация c неверным паролем")
    public void loginWithInvalidPasswordTest() {
        courier.setPassword("unknown");
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(404)
                .assertThat()
                .body("code", equalTo(404))
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void loginWithoutPasswordTest() {
        courier.setPassword("");
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(400)
                .assertThat()
                .body("code", equalTo(400))
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация без логина")
    public void loginWithoutLoginTest() {
        courier.setLogin("");
        ValidatableResponse response = CourierStep.login(courier);
        response
                .statusCode(400)
                .assertThat()
                .body("code", equalTo(400))
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
