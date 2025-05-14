package com.petStore.userApis;

import com.petStore.pojoBodies.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UsersRequests {

    String baseUrl;
    String userEndpoint = "/api/v3/user/";
    public Dotenv dotenv = null;

    public UsersRequests() {
        dotenv = Dotenv.load();
        baseUrl = dotenv.get("BASE_URI");
    }

    @Step("create new user")
    public Response createNewUser(User user) {
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("authorization")))
                .body(user)
                .when()
                .post(userEndpoint);
        return response;
    }

    @Step("get user by username")
    public Response getUserByUsername(String username) {
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("authorization")))
                .when()
                .get(userEndpoint + username);
        return response;
    }

    @Step("update user")
    public Response updateUser(String oldUsername,User user) {
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(user)
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("authorization")))
                .when()
                .put(userEndpoint + oldUsername);
        return response;
    }


    @Step("delete user")
    public Response deleteUser(String username) {
        Response response = RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("authorization")))
                .when()
                .delete(userEndpoint + username);
        return response;
    }
}
