package utility;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class PasswordEncoder implements PasswordEncoderI {

    @Override
    public byte[] computeHash(String password) {
        byte[] hashBytes = BCrypt.with(new SecureRandom()).hash(10,
                        password.getBytes(StandardCharsets.UTF_8));

        return hashBytes;
    }

    @Override
    public boolean compare(String password, byte[] hashString) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.getBytes(StandardCharsets.UTF_8), hashString);
        return result.verified;
    }
}
