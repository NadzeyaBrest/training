package apiTests;

import api.BookData;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BooksTest {
    private static final String URL = "https://demoqa.com";

    @Test
    public void getBooksTest() {
        List<BookData> books = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/BookStore/v1/Books")
                .then()

                .extract().body().jsonPath().getList("books", BookData.class);

        Map<String, List<String>> mapPublishers = books.stream()
                .collect(Collectors
                        .groupingBy(BookData::getPublisher, Collectors.mapping(BookData::getTitle, Collectors.toList())));
        System.out.println("mapPublishers" + mapPublishers);
        for(Map.Entry<String, List<String>> entry : mapPublishers.entrySet()) {
            String publisher = entry.getKey();
            List <String>  titles = entry.getValue();
            System.out.println("Издатель "+publisher + titles );
        }

    }

}
