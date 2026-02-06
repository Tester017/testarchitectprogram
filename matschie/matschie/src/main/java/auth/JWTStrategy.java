package auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTStrategy implements AuthStrategy {

    private final String secretKey = "SecretKey";

    @Override
    public String getToken() {
        return Jwts.builder()
                .setSubject("sampleUser")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
