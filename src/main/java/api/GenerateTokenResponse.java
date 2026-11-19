package api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerateTokenResponse {
    private String token;
    private String expires;
    private String status;
    private String result;
}
