package auth;

public class AuthFactory {
    public static AuthStrategy getAuthStrategy(String authType) {
        if ("JWT".equalsIgnoreCase(authType)) {
            return new JWTStrategy();
        } else if ("OAUTH".equalsIgnoreCase(authType)) {
            return new OAuthStrategy();
        }
        throw new IllegalArgumentException("Unsupported authentication type: " + authType);
    }
}
