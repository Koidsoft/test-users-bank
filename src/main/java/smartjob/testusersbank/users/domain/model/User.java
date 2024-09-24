package smartjob.testusersbank.users.domain.model;

import java.util.List;

public class User {
    private String id;
    private final String email;
    private final String name;
    private boolean enable;
    private final List<Phone> phones;
    private UserMetadata metadata;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.name = builder.name;
        this.enable = builder.enable;
        this.phones = builder.phones;
        this.metadata = builder.metadata;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public UserMetadata getMetadata() {
        return metadata;
    }

    public static class Builder {
        private String id;
        private final String email;
        private final String name;
        private boolean enable = true;
        private final List<Phone> phones;
        private UserMetadata metadata;

        public Builder(String email, String name, List<Phone> phones) {
            this.email = email;
            this.name = name;
            this.phones = phones;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder enable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder metadata(UserMetadata metadata) {
            this.metadata = metadata;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

