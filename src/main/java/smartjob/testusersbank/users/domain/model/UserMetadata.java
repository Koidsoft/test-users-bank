package smartjob.testusersbank.users.domain.model;

public class UserMetadata {
    private final String token;
    private final String lastLogin;
    private final String created;
    private final String modified;

    public UserMetadata(String token, String lastLogin, String created, String modified) {
        this.token = token;
        this.lastLogin = lastLogin;
        this.created = created;
        this.modified = modified;
    }

    public String getToken() {
        return token;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
