package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpecification (String Uri){
        return new RequestSpecBuilder()
                .setBaseUri(Uri)
                .setContentType(ContentType.JSON)
                .build();
    };
public static ResponseSpecification responseSpecificationOk (){
    return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
}
