package services.exampleAPI;

import files.BasePath;
import files.Constantes;
import files.Resource;
import files.UserData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jsons.PayloadExample;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ExampleService {

    //Esta classe usa o padrão singleton

    private static JsonPath var;

    //Construtor
    public ExampleService(){}

    public static JsonPath example() throws IOException {

        if(var == null) {

            Response res = given().
                    basePath(BasePath.getApiPath()).
                    header(Constantes.CONTENT_TYPE, Constantes.APPJSON).
                    header(Constantes.AUTHORIZATION, UserData.accessToken()).
                    body(PayloadExample.payloadExample()).
                    when().
                    post(Resource.getUrlExample()).
                    then().assertThat().statusCode(201).and().
                    contentType(ContentType.JSON).and().
                    extract().response();

            var = new JsonPath(res.asString());
        }
        return var;
    }
}