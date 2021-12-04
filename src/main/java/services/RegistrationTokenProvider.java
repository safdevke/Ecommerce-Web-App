//package services;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.SecureRandom;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//
//public class RegistrationTokenProvider extends JwtTokenProvider implements RegistrationTokenProviderI{
//
//    public HashMap<String, Object> issueToken() {
//
//        byte[] arr = new byte[50];
//        new SecureRandom().nextBytes(arr);
//        String randomChars = new String(Base64.getEncoder().encode(arr));
//
//        Instant now = Instant.now();
//        byte[] secret = Base64.getDecoder().decode(randomChars);
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//
//        String jwtToken = Jwts.builder()
//                .setIssuer("Ecommerce site")
//                .setSubject("Registration Token")
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(2, ChronoUnit.MINUTES)))
//                .signWith(Keys.hmacShaKeyFor(secret))
//                .compact();
//
//        map.put("token", jwtToken);
//        map.put("secret", secret);
//        return map;
//    }
//}
