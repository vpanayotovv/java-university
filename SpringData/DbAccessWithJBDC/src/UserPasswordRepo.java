public class UserPasswordRepo {
    private String username;
    private String password;

    UserPasswordRepo(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    String getUsername() {
        return this.username;
    }

    void setUsername(String username) {
        if (username.trim().isEmpty()){
            throw new IllegalArgumentException("Try Again With Username!");
        }
        this.username = username;
    }

    String getPassword() {
        return this.password;
    }

    void setPassword(String password) {
        if (password.trim().isEmpty()){
            throw new IllegalArgumentException("Try Again With Password!");
        }
        this.password = password;
    }
}
