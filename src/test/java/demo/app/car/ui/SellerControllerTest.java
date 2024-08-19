package demo.app.car.ui;

import demo.app.car.model.SellerDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class SellerControllerTest {
   @Test
   @DisplayName("Find the Seller profile by unique ID")
   void findProfileById() {
     var sellerId = 1;
     given().when().get("/sellers/{sellerId}",sellerId).then().statusCode(200);
   }

   @Test
   @DisplayName("Create new Seller profile")
   void createNewSellerProfile() {
     given().when().contentType(ContentType.JSON).body(new SellerDTO(null,"John","Doe","john.doe@testmail.com")).post("/sellers").then().statusCode(200);
   }
}


