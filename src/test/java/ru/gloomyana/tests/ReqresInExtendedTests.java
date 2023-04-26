package ru.gloomyana.tests;

import org.junit.jupiter.api.Test;
import ru.gloomyana.models.ReqresInBodyModel;
import ru.gloomyana.models.ReqresInResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ReqresInExtendedTests {
    @Test
    void successfulCreateUserTest() {

        ReqresInBodyModel body = new ReqresInBodyModel();
        body.setName("morpheus");
        body.setJob("leader");

        ReqresInResponseModel response =  given()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(ReqresInResponseModel.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }


}
