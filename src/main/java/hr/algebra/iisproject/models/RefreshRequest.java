package hr.algebra.iisproject.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {
    private String username;
    private String password;
    private String refreshToken;
}
