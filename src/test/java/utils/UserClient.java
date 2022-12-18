package utils;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;
import models.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    private final String USER_REGISTER_PATH = "/api/auth/register";

    private final String USER_LOGIN_PATH = "/api/auth/login";

    private final String USER_PATH = "/api/auth/user";

    @Step("Create new user {user}")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .when()
                .body(user)
                .post(USER_REGISTER_PATH)
                .then();
    }

    @Step("Login user with credentials {credentials}")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .when()
                .body(credentials)
                .post(USER_LOGIN_PATH)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse delete(String token) {

        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .when()
                .delete(USER_PATH)
                .then();
    }
}