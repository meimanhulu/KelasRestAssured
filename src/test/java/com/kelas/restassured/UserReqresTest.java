package com.kelas.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserReqresTest {
    private RequestSpecification request;
    private Response response;

     @BeforeClass
     public void setup() {
         RestAssured.baseURI = "https://reqres.in";
         request = RestAssured.given();
     }

     @Test(priority = 1)
     public void readTest() {
         response = request.get("/api/users");
         JsonPath jsonPathEvaluator = response.jsonPath();
         int total = jsonPathEvaluator.getInt("total");
         Assert.assertTrue(total >= 1);
     }

    @Test(priority = 2)
    public void getTest() {
        response = request.get("/api/users/2");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String expect = "janet.weaver@reqres.in";
        String actual = jsonPathEvaluator.getString("data.email");
        Assert.assertEquals(actual, expect);
        int[] a = {1, 2};
        int[] b = {2, 1};
        Assert.assertEquals(a, b);
        Assert.assertEquals(a, b);
    }

    @Test(priority = 3)
    public void createTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");
        jsonObject.put("password", "pistol");
        request.body(jsonObject.toJSONString());
        request.header("Content-Type", "application/json");
        response = request.post("/api/register");
        JsonPath jsonPathEvaluator = response.jsonPath();

        String expect = "QpwL5tke4Pnpja7X4";
        String actual = jsonPathEvaluator.getString("token");
        System.out.println(jsonPathEvaluator.prettyPrint());
        Assert.assertEquals(actual, expect);
    }

    @Test(priority = 4)
    public void updateTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "andika");
        jsonObject.put("job", "QA Engineer");
        request.body(jsonObject.toJSONString());
        request.header("Content-Type", "application/json");
        response = request.put("/api/users/2");
        JsonPath jsonPathEvaluator = response.jsonPath();

        String expect = "QA Engineer";
        String actual = jsonPathEvaluator.getString("job");
        Assert.assertEquals(actual, expect);
    }

    @Test(priority = 5)
    public void deleteTest() {

    }
}
