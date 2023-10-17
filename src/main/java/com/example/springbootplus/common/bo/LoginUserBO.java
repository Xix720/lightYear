package com.example.springbootplus.common.bo;

import com.example.springbootplus.validator.CpachaValid;
import com.example.springbootplus.validator.PasswordValid;
import com.example.springbootplus.validator.UserNameValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Data
public class LoginUserBO {
    @NotBlank(groups = UserNameValid.class)
//    @NotBlank
    private String username;
    @NotBlank(groups = PasswordValid.class)
//    @NotBlank
    private String password;
    @NotBlank(groups = CpachaValid.class)
//    @NotBlank
    private String cpacha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpacha() {
        return cpacha;
    }

    public void setCpacha(String cpacha) {
        this.cpacha = cpacha;
    }
}
