package ru.gloomyana.tests;

import org.junit.jupiter.api.Test;
import ru.gloomyana.models.UserBodyModel;
import ru.gloomyana.models.UserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.gloomyana.specs.ReqresInSpec.*;


public class ReqresInExtendedTests {
    @Test
    void successfulCreateUserTest() {
        UserBodyModel body = new UserBodyModel();
        body.setName("morpheus");
        body.setJob("leader");

        UserResponseModel response = step("Make request for create user", () ->
                given(userRequestSpec)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .spec(userResponseSpec)
                        .extract().as(UserResponseModel.class));

        step("Verify user name", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        step("Verify user job", () ->
                assertThat(response.getJob()).isEqualTo("leader"));
    }


}
