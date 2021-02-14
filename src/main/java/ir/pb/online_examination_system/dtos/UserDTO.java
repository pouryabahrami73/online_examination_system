package ir.pb.online_examination_system.dtos;

import javax.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank(message = "نام کاربری را وارد کنید!")
    private String userName;
    @NotBlank(message = "عنوان را وارد کنید!")
    private String roles;
    @NotBlank(message = "نام را وارد کنید!")
    private String firstName;
    @NotBlank(message = "نام خانوادگی را وارد کنید!")
    private String lastName;
    @NotBlank(message = "کد ملی را وارد کنید!")
    private String nationalCode;

    public UserDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
