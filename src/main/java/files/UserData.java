package files;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserData {

    private static String accessToken;
    private static String refreshToken;

    //Construtor
    private UserData() {}
    //Construtor

    public static String ambiente() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/env.properties");
        prop.load(fis);

        return prop.getProperty("baseUrl");
    }

    public static String login() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/env.properties");
        prop.load(fis);

        return prop.getProperty("LOGIN");
    }

    public static String senha() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/env.properties");
        prop.load(fis);

        return prop.getProperty("SENHA");
    }

    public static String accessToken() throws IOException {

        if (accessToken == null) {
            accessToken = getHeader("access_token");
        }
        return accessToken;
    }

    public static String refreshToken() throws IOException {

        if (refreshToken == null) {
            refreshToken = getHeader("refresh_token");
        }
        return refreshToken;
    }

    private static String getHeader(String Token) throws IOException {

            RestAssured.baseURI = UserData.ambiente();

            Response res = given().
                    basePath().
                    header(Constantes.CONTENT_TYPE, Constantes.APPJSON).
                    body(()).
                    when().
                    post().
                    then().assertThat().statusCode(200).and().
                    contentType(ContentType.JSON).extract().response();

            String responseString = res.asString();
            JsonPath js = new JsonPath(responseString);
            return js.get(Token);
    }
}
