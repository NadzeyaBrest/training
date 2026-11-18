package api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookData {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private OffsetDateTime publish_date;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;
}
