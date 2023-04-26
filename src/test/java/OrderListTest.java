import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
    @Test
    @DisplayName("Получение списка всех заказов")
    public void getAllOrdersTest(){
        ValidatableResponse response = OrderStep.getOrders();
        response.statusCode(200).assertThat().body("orders", notNullValue());
    }
}
