package api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private String userID;
    private String username;
    private List<BookData> books=new ArrayList<BookData>();
}
