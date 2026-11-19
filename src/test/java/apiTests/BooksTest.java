package apiTests;

import api.BookData;
import api.Specification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BooksTest {
    private static final String URL = "https://demoqa.com";

    @Test
    public void getBooksTest() {
        RestAssured.requestSpecification = Specification.requestSpecification(URL);
        List<BookData> books = given()
                .when()
                .get("/BookStore/v1/Books")
                .then()
         //       .log().all()
                .extract().body().jsonPath().getList("books", BookData.class);

        Map<String, List<String>> mapPublishers = books.stream()
                .collect(Collectors.groupingBy(BookData::getPublisher, Collectors.mapping(BookData::getTitle, Collectors.toList())));
        //  System.out.println("mapPublishers" + mapPublishers);
        // проверить что опубликовано 6 книг издателя O'Reilly Media
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mapPublishers.containsKey("O'Reilly Media"));
        softAssert.assertEquals(mapPublishers.get("O'Reilly Media").size(), 6);
        softAssert.assertTrue(mapPublishers.get("O'Reilly Media").contains("Git Pocket Guide"));
        softAssert.assertAll();
    }

    @Test
    public void getBookTest() {
        Response response = given()
                .spec(Specification.requestSpecification(URL))
                .queryParam("ISBN", "9781449325862")
                .when()
                .get("/BookStore/v1/Book");
        BookData book = response.as(BookData.class);
        System.out.println("book   + 1" + book);
        boolean isValid = matchesJsonSchemaInClasspath("bookShema.json").matches(response.asString());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isValid, "Схема не соответствует");
        softAssert.assertEquals(book.getTitle(), "Git Pocket Guide", "Название не соответствует");
        softAssert.assertEquals(book.getAuthor(), "Richard E. Silverman", "Автор не соответстует");
        softAssert.assertAll();
    }

}
