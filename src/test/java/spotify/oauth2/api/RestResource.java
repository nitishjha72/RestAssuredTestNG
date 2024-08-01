package spotify.oauth2.api;

import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.Route.*;
import static spotify.oauth2.api.SpecBuilder.*;

public class RestResource {

    public static Response post(String path, String token, Object reqPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(token).
//                header("Authorization", "Bearer "+token).
                body(reqPlaylist).
                when().post(path).
                then().spec(getResponseSpec())
                .extract().response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                auth().oauth2(token).
//                header("Authorization", "Bearer "+token).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response update(String path, String token, Object reqPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(token).
//                header("Authorization", "Bearer "+token).
                body(reqPlaylist).
                when().put(path).
                then().spec(getResponseSpec()).extract().response();
        //6IEVkE1bdRf6ToEvXKuZ22
    }

    public static Response postMethodForAccount(HashMap<String, String> formParams) {

        return given(getAccountRequestSpec())
                .formParams(formParams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec())
                .extract().response();
    }
}
