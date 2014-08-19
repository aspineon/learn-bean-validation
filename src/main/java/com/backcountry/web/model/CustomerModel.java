package com.backcountry.web.model;

import com.backcountry.web.constraints.UniqueEmail;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author Edwin Dalorzo.
 */
public class CustomerModel {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @UniqueEmail
    private String email;

    @Past
    private Date dateOfBirth;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
