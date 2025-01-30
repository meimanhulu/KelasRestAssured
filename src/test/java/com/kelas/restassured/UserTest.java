package com.kelas.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    private RequestSpecification request;
    private Response response;

    // @BeforeClass
    // public void setup() {
    // RestAssured.baseURI = "https://demoqa.com";
    // request = RestAssured.given();
    // }

    @Test(priority = 2)
    public void getUserTest() {
        RestAssured.baseURI = "https://demoqa.com";
        request = RestAssured.given();
        response = request.get("/Account/v1/User/test");
        // System.out.println(response.getStatusLine());
        Assert.assertEquals(response.statusCode(), 401, "Correct status code returned");
    }

    @Test(priority = 1)
    public void getPageUserTest() {
        RestAssured.baseURI = "https://reqres.in";
        request = RestAssured.given();
        response = request.get("/api/users");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int total = jsonPathEvaluator.getInt("total");
        Assert.assertTrue(total >= 1);
    }

    @Test(priority = 3)
    public void getUserList() {
        RestAssured.baseURI = "https://reqres.in";
        request = RestAssured.given();
        response = request.queryParam("page", "2").get("/api/users");
        JsonPath jsonPathEvaluator = response.jsonPath();
        int page = jsonPathEvaluator.getInt("page");
        Assert.assertTrue(page == 2);
    }

    @Test(priority = 4)
    public void createUserTest() {

    }
}
