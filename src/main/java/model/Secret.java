package model;

import javax.persistence.*;

@Entity
@Table(name = "secret_table")
public class Secret {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String secret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
