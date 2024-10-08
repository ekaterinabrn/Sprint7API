package Praktikum;

public class Credentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials fromCourier(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

}
