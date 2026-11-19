package apiTests;

import api.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserAuthorizationTest {
    private static final String URL = "https://demoqa.com";

    @DataProvider(name = "createUserData")
    public Object[][] dataForCreating() {
        String unique = String.valueOf(System.currentTimeMillis()).substring(2);
        return new Object[][]{
                {"Bac22" + unique, "BacBac12!"}
        };
    }

    @Test(dataProvider = "createUserData")
    public void createUserTest(String userName, String password) {
        UserData user = new UserData(userName, password);
        CreateUserResponse createUserResponse = given()
                .spec(Specification.requestSpecification(URL))
                .body(user)
                .when()
                .post("/Account/v1/User")
                .then().log().all()
                .extract().as(CreateUserResponse.class);
        TestContext.token = createUserResponse.getUserID();
        GenerateTokenResponse generateTokenResponse = given()
                .spec(Specification.requestSpecification(URL))
                .body(user)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .extract().as(GenerateTokenResponse.class);
        TestContext.token = generateTokenResponse.getToken();
    }
}
