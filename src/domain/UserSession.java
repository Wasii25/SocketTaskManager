package domain;

public class UserSession {
    private  String username;

    public boolean isLoggedIn() {
        return username!= null;
    }

    public void login(String username) {
        this.username = username;
    }

    public void logout() {
        this.username = null;
    }

    public  String getUsername() {
        return  username;
    }
}
