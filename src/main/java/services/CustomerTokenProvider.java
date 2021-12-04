//package services;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import model.Customer;
//
//import java.security.SecureRandom;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//
//public class CustomerTokenProvider extends JwtTokenProvider implements CustomerTokenProviderI{
//
//    @Override
//    public HashMap<String,Object> issueToken(Customer customer) {
//
//        byte[] arr = new byte[50];
//        new SecureRandom().nextBytes(arr);
//        String randomChars = new String(Base64.getEncoder().encode(arr));
//
//        Instant now = Instant.now();
//        byte[] secret = Base64.getDecoder().decode(randomChars);
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        HashMap<String, Object> claims = new HashMap<String, Object>();
//        claims.put("id", customer.getCustomerId());
//        claims.put("iss", "Ecommerce site");
//        claims.put("iat", Date.from(now));
//        claims.put("exp", Date.from(now.plus(1, ChronoUnit.HOURS)));
//
//        String jwtToken = Jwts.builder().setClaims(claims).compact();
//
//        map.put("token", jwtToken);
//        map.put("secret", secret);
//        return map;
//    }
//
//}
