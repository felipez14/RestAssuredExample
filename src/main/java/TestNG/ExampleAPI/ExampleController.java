package TestNG.ExampleAPI;

import Services.ExampleAPI.ExampleService;
import files.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;

public class ExampleController {

    private static String serviceVar;

    @BeforeTest
    public void getData() throws IOException {

        RestAssured.baseURI= UserData.ambiente();
        RestAssured.config = RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }

    @Test
    public void create() throws IOException {

        serviceVar = ExampleService.example().get("id");

    }

    @Test(dependsOnMethods = "create")
    public void findAll() throws IOException {

        given().
                basePath(BasePath.getApiPath()).
                header(Constantes.AUTHORIZATION, UserData.accessToken()).
                when().
                get(Resource.getUrlExample()).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "findAll")
    public void findById() throws IOException {

        given().
                basePath(BasePath.getApiPath()).
                header(Constantes.AUTHORIZATION, UserData.accessToken()).
                pathParam("id", serviceVar).
                when().
                get(Resource.getUrlExample()).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "findById")
    public void update() throws IOException {

        given().
                basePath(BasePath.getApiPath()).
                header(Constantes.CONTENT_TYPE, Constantes.APPJSON).
                header(Constantes.AUTHORIZATION, UserData.accessToken()).
                pathParam("id", serviceVar).
                body(Payload.payloadExample()).
                when().
                put(Resource.getUrlExample()).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON);
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws IOException {

        given().
                basePath(BasePath.getApiPath()).
                header(Constantes.AUTHORIZATION, UserData.accessToken()).
                pathParam("id", serviceVar).
                when().
                delete(Resource.getUrlExample()).
                then().assertThat().statusCode(204);
    }
}
