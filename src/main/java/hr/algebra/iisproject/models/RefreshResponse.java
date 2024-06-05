package hr.algebra.iisproject.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshResponse {
    private String accessToken;
    private String refreshToken;

    public RefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
