package com.testproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    // TEST GET
    @Test
    public void testGetPost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)                        
            .body("id", equalTo(1))                 
            .body("title", notNullValue())          
            .time(lessThan(5000L));                  
    }

    // TEST POST
    @Test
    public void testCreatePost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{"
            + "\"title\": \"Test Baslik\","
            + "\"body\": \"Test Icerik\","
            + "\"userId\": 1"
            + "}";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)                         
            .body("title", equalTo("Test Baslik"))   
            .time(lessThan(5000L));                  
    }
}
