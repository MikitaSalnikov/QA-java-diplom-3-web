package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class CustomerClient {
    public Response createCustomer(String email, String pass) {
        given()
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                        "  \"name\": \"Squancheee\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + pass + "\"\n" +
                        "}")
                .when()
                .post("/api/auth/register");
        return post();
    }

    public String loginCustomer(String email, String pass) {
        Response post =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body("{\n" +
                                "  \"email\": \"" + email + "\",\n" +
                                "  \"password\": \"" + pass + "\"\n" +
                                "}")
                        .when()
                        .post("/api/auth/login");
        return post.then().extract().path("accessToken").toString();
    }

    public void deleteCustomer(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}