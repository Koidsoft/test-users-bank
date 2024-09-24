package smartjob.testusersbank.users.infraestructure.in.data;

public class UserResponseDTO {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private boolean isActive;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String id, String created, String modified, String lastLogin, String token, boolean isActive) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return isActive;
    }
}
