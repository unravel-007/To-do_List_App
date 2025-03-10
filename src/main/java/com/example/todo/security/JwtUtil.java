package com.example.todo.security;

import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "secret123";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        long exp = now + EXPIRATION_TIME;
        String payload = username + ":" + exp;

        String signature = hmacSha256(payload, SECRET_KEY);
        return Base64.getEncoder().encodeToString((payload + "." + signature).getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidToken(String token) {
        try {
            String decodedToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            String[] parts = decodedToken.split("\\.");
            if (parts.length != 2) return false;

            String payload = parts[0];
            String expectedSignature = hmacSha256(payload, SECRET_KEY);

            return expectedSignature.equals(parts[1]) && !isExpired(payload);
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        String decodedToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        return decodedToken.split("\\.")[0].split(":")[0];
    }

    private boolean isExpired(String payload) {
        long exp = Long.parseLong(payload.split(":")[1]);
        return exp < System.currentTimeMillis();
    }

    private String hmacSha256(String data, String key) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating HMAC SHA256 signature", e);
        }
    }
}
