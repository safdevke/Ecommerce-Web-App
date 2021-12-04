package model;

import javax.persistence.*;

@Entity
@Table(name = "email_table")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String mail;

    String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String from) {
        this.mail = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
