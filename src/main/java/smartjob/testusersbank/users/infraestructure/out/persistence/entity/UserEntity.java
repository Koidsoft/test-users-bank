package smartjob.testusersbank.users.infraestructure.out.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean enable;
    @Column
    private String token;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "created_at")
    private Date created;
    @Column(name = "update_at")
    private Date updated;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "idUser")
    private List<PhoneEntity> phones;

    @PrePersist
    protected void onCreate() {
        enable = true;
        if (lastLogin == null) {
            lastLogin = new Date();
        }
        created = new Date();
        updated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.name = builder.name;
        this.password = builder.password;
        this.enable = builder.enable;
        this.token = builder.token;
        this.lastLogin = builder.lastLogin;
        this.created = builder.created;
        this.updated = builder.updated;
        this.phones = builder.phones;
    }

    public UserEntity() {

    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getToken() {
        return token;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public static class Builder {
        private UUID id;
        private final String email;
        private final String name;
        private String password;
        private boolean enable;
        private String token;
        private Date lastLogin;
        private Date created;
        private Date updated;
        private List<PhoneEntity> phones;

        public Builder(String email, String name) {
            this.email = email;
            this.name = name;

        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder enable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder lastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder created(Date created) {
            this.created = created;
            return this;
        }

        public Builder updated(Date updated) {
            this.updated = updated;
            return this;
        }

        public Builder phones(List<PhoneEntity> phones) {
            this.phones = phones;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity(this);
            if (phones != null) {
                phones.forEach(phones -> phones.setIdUser(userEntity));
            }
            return userEntity;
        }
    }
}
