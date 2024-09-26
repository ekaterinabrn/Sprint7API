package Praktikum;

public class CourierLogin {
/*уважаемый ревью, я после удалю этот комент- я оставила логин курьера, хоть у меня и есть Credentials
    просто ,для меня так было более красиво, да пришлось два раза создать ручку ,мне так было удобнее  просто чтоб разделить для себя логирование для теста и для удаления
            я сделала так */
//коментарий удалю по завершении, чтоб не запутать никого
    private String login;
    private String password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierLogin() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
