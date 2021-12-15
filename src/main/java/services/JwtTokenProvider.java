package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import model.Customer;
import org.checkerframework.checker.units.qual.C;
import utility.Consts;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class JwtTokenProvider implements JwtTokenProviderI {

    private ObjectMapper jsonMapper = new ObjectMapper();

    public HashMap<String,Object> issueToken(Customer customer, boolean newCustomer) {

        byte[] arr = new byte[50];
        new SecureRandom().nextBytes(arr);
        String randomChars = new String(Base64.getEncoder().encode(arr));

        Instant now = Instant.now();
        byte[] secret = Base64.getDecoder().decode(randomChars);

        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> claims = new HashMap<String, Object>();

        if (newCustomer) {
            claims.put("email", customer.getEmail());
            claims.put("iss", "Ecommerce site");
            claims.put("iat", Date.from(now));
            claims.put("exp", Date.from(now.plus(10, ChronoUnit.MINUTES)));
        } else {
            claims.put("id", customer.getCustomerId());
            claims.put("iss", "Ecommerce site");
            claims.put("iat", Date.from(now));
            claims.put("exp", Date.from(now.plus(1, ChronoUnit.HOURS)));
        }

        String jwtToken = Jwts.builder().
                setClaims(claims).
                signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        map.put("token", jwtToken);
        map.put("secret", secret);
        return map;
    }

    public boolean validateToken(Customer customer) {
        try {
            Jws<Claims> result = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(customer.getSecret()))
                    .build()
                    .parseClaimsJws(Consts.token);
        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            throw e;
        }
        return true;
    }

    public Customer decodeToken(String token) {
        String[] arr = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(arr[1]));
        Customer customer = new Customer();
        try {
            JsonNode node = jsonMapper.readTree(payload);
            int id = node.get("id").asInt();
            customer.setCustomerId(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
