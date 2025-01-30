package com.kelas.restassured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookTest {
    private RequestSpecification request;
    private Response response;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://demoqa.com";
        request = RestAssured.given();
    }

    @Test(priority = 1)
    public void listBookTest() {
        response = request.get("/BookStore/v1/Books");
        Assert.assertEquals(response.statusCode(), 200, "Correct status code returned");
    }

    @Test(priority = 2)
    public void headerContentTypeTest() {
        String actual = response.header("Content-Type");
        String expected = "application/json; charset=utf-8";

        Assert.assertEquals(actual, expected, "Correct Content-Type returned");
    }

    @Test(priority = 3)
    public void headerServerTest() {
        String actual = response.header("Server");
        String expected = "nginx/1.17.10 (Ubuntu)";
        Assert.assertEquals(actual, expected, "Correct Server returned");
    }

    @Test(priority = 5)
    public void headerContentEncodingTest() {
        String actual = response.header("Content-Encoding");
        Assert.assertNotNull(actual, "Correct Content-Encoding returned");
    }

    @Test(priority = 4)
    public void bodyTest() {
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
        Assert.assertTrue(body.asString().toLowerCase().contains("Git Pocket Guide"));
    }
}