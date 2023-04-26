package ru.gloomyana.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;
import static ru.gloomyana.helpers.CustomAllureListener.withCustomTemplates;

public class ReqresInSpec {
    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .baseUri("https://reqres.in");

    public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("name", notNullValue())
            .expectBody("job", notNullValue())
            .build();

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .expectBody("error", notNullValue())
            .build();
}
