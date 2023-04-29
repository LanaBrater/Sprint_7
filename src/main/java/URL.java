import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class URL {
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String COURIER_URL = "/api/v1/courier/";
    public static final String ORDERS_URL = "api/v1/orders";
    public static final String COURIER_LOGIN = COURIER_URL + "login";
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(MAIN_PAGE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
