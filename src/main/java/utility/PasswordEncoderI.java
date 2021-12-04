package utility;

public interface PasswordEncoderI {

    byte[] computeHash(String password);

    boolean compare(String password, byte[] hashString);

}
