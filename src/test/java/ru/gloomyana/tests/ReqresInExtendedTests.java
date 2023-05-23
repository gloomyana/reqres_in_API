package ru.gloomyana.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gloomyana.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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

        step("Verify unsuccessful login", () ->
                assertThat(response.getError()).isEqualTo("Missing password"));
    }

    @Tag("extended_test")
    @Test
    @DisplayName("Successful get resource list request")
    void checkListResourceSchemeTest() {
        ListDataResponseModel response = step("Make request to get resource list", () ->
                given(baseRequestSpec)
                        .when()
                        .get("/api/unknown")
                        .then()
                        .statusCode(200)
                        .spec(resourceDataResponseSpec)
                        .body(matchesJsonSchemaInClasspath("schemes/list-resource-scheme.json"))
                        .extract().as(ListDataResponseModel.class));

        step("Verify response data count", () ->
                assertThat(response.getData().size()).isEqualTo(6));
        step("Verify response number of total", () ->
                assertThat(response.getTotal()).isEqualTo(12));
    }

    @Tag("extended_test")
    @Test
    @DisplayName("Successful get resource data request")
    void checkSingleResourceNameTest() {
        ResourceDataResponseModel response = step("Make request to get resource data", () ->

                given(baseRequestSpec)
                        .when()
                        .get("/api/unknown/2")
                        .then()
                        .statusCode(200)
                        .spec(resourceDataResponseSpec)
                        .extract().as(ResourceDataResponseModel.class));

        step("Verify resource data", () ->
                assertThat(response.getData().getName()).isEqualTo("fuchsia rose"));
    }

    @Tag("extended_test")
    @Test
    @DisplayName("Successful resource not found request")
    void checkSingleResourceNotFoundTest() {
        ResourceDataResponseModel response = step("Make request to get resource data", () ->

                given(baseRequestSpec)
                        .when()
                        .get("/api/unknown/23")
                        .then()
                        .statusCode(404)
                        .spec(resourceDataResponseSpec)
                        .extract().as(ResourceDataResponseModel.class));

        step("Verify resource data is null", () ->
                assertThat(response.getData()).isNull());
    }
}
