package dat.backend.model.entities;

import java.util.Objects;

public class User
{
    private String username;
    private String password;
    private float balance;
    private int admin;

    public User(String username, String password, float balance, int admin)
    {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.admin = admin;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) &&
                getAdmin() == user.getAdmin();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUsername(), getPassword(), getAdmin());
    }

    @Override
    public String toString()
    {
        return "User{" +
                "brugerNavn='" + username + '\'' +
                ", balance='" + balance + '\'' +
                ", rolle='" + admin + '\'' +
                '}';
    }
}
