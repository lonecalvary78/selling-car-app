package demo.app.car.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class SellerControllerTest {
   @Test
   @DisplayName("Find the Seller profile by unique ID")
   void findProfileById() {
     var sellerId = 1;
     given().when().get("/sellers/{sellerId}",sellerId).then().statusCode(400);
   }
}


