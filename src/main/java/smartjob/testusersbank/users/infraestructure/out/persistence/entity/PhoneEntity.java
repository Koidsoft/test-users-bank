package smartjob.testusersbank.users.infraestructure.out.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_phones")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String phone;
    @Column
    private String cityCode;
    @Column
    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity idUser;


    public PhoneEntity(String phone, String cityCode, String countryCode) {
        this.phone = phone;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public PhoneEntity() {

    }

    public UUID getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }
}
