package com.petStore.tests.apiE2eTests.user;

import com.petStore.pojoBodies.user.User;
import com.petStore.userApis.UsersRequests;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import utils.DataGeneration;


@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUsersEndToEnd {

    public Dotenv dotenv = null;
    User user = null;
    Integer id;
    String username;
    String email;
    String firstName;
    String lastName;
    String password;
    String phone;
    Integer userStatus;
    String updatedUsername;
    @BeforeAll
    @Description("create new user")
    @DisplayName("create new user")
    public void preconditions_createUser() {
        dotenv = Dotenv.load();
        user = new User();
        id = 1;
        username = new DataGeneration().generateRandomString(5);
        email = username + "@email.com";
        firstName = new DataGeneration().generateRandomString(5);
        lastName = new DataGeneration().generateRandomString(5);
        password = new DataGeneration().generateRandomString(5);
        phone = new DataGeneration().generateRandomTimeStamp();
        userStatus = 1;
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(1);
    }

    @Test
    @Description("1-create new user")
    @DisplayName("1-create new user")
    public void a_testAddingNewUser () {
        Response response = new UsersRequests()
                .createNewUser(user);
        response.then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("username", Matchers.equalTo(username))
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("email", Matchers.equalTo(email))
                .body("password", Matchers.equalTo(password))
                .body("phone", Matchers.equalTo(phone))
                .body("userStatus", Matchers.equalTo(1))
                ;
    }

    @Test
    @Description("2-get user by username")
    @DisplayName("2-get user by username")
    public void b_testGettingUserByUsername () {
        Response response = new UsersRequests()
                .getUserByUsername(username);
        response.then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("username", Matchers.equalTo(username))
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("email", Matchers.equalTo(email))
                .body("password", Matchers.equalTo(password))
                .body("phone", Matchers.equalTo(phone))
                .body("userStatus", Matchers.equalTo(1));
    }

    @Test
    @Description("3-update user")
    @DisplayName("3-update user")
    public void c_testUpdatingUser () {
        updatedUsername = new DataGeneration().generateRandomString(5);
        user.setUsername(updatedUsername);
        Response response = new UsersRequests()
                .updateUser(username, user);
        response.then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("username", Matchers.equalTo(updatedUsername))
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("email", Matchers.equalTo(email))
                .body("password", Matchers.equalTo(password))
                .body("phone", Matchers.equalTo(phone))
                .body("userStatus", Matchers.equalTo(1));
    }


    @Test
    @Description("4-get user after updating username")
    @DisplayName("4-get user after updating username")
    public void d_testGettingUserAfterUpdatingUsername () {
        Response response = new UsersRequests()
                .getUserByUsername(updatedUsername);
        response.then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("username", Matchers.equalTo(updatedUsername))
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("email", Matchers.equalTo(email))
                .body("password", Matchers.equalTo(password))
                .body("phone", Matchers.equalTo(phone))
                .body("userStatus", Matchers.equalTo(1));
    }

    @Test
    @Description("5-delete user")
    @DisplayName("5-delete user")
    public void e_testDeletingUser () {
        Response response = new UsersRequests()
                .deleteUser(updatedUsername);
        response.then().statusCode(200)
                ;
    }

    @Test
    @Description("6-get user after deletion")
    @DisplayName("6-get user after deletion")
    public void f_testGettingUserAfterDeletion () {
        Response response = new UsersRequests()
                .getUserByUsername(updatedUsername);
        response.then().statusCode(404)
                .body(Matchers.containsStringIgnoringCase("User not found"));
    }

}
