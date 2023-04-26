package ru.gloomyana.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.LoginBodyModel;
import ru.gloomyana.models.LoginResponseModel;
import ru.gloomyana.models.UserBodyModel;
import ru.gloomyana.models.UserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.specs.ReqresInSpec.*;


public class ReqresInExtendedTests {
    @Tag("extended_test")
    @Test
    @DisplayName("Successful user creation request")
    void successfulCreateUserTest() {
        UserBodyModel body = new UserBodyModel();
        body.setName("morpheus");
        body.setJob("leader");

        UserResponseModel response = step("Make request for create user", () ->
                given(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("api/users")
                        .then()
                        .statusCode(201)
                        .spec(userResponseSpec)
                        .extract().as(UserResponseModel.class));

        step("Verify user name", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        step("Verify user job", () ->
                assertThat(response.getJob()).isEqualTo("leader"));
    }

    @Tag("extended_test")
    @Test
    @DisplayName("Successful user data update request")
    void successfulUpdateUserTest() {
        UserBodyModel body = new UserBodyModel();
        body.setName("morpheus");
        body.setJob("zion resident");

        UserResponseModel response = step("Make request for update user data", () ->
                given(baseRequestSpec)
                        .body(body)
                        .when()
                        .patch("/api/users/2")
                        .then()
                        .statusCode(200)
                        .spec(userResponseSpec)
                        .extract().as(UserResponseModel.class));

        step("Verify user data update", () ->
                assertThat(response.getJob()).isEqualTo("zion resident"));
    }

    @Tags({
            @Tag("extended_test"),
            @Tag("unsuccessful")
    })
    @Test
    @DisplayName("Unsuccessful login request")
    void unSuccessfulLoginTest() {
        LoginBodyModel body = new LoginBodyModel();
        body.setEmail("peter@klaven");

        LoginResponseModel response = step("Make unsuccessful login request without password", () ->
                given(baseRequestSpec)
                        .body(body)
                        .when()
                        .post("/api/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Verify unsuccessful login request", () ->
                assertThat(response.getError()).isEqualTo("Missing password"));
    }
}

