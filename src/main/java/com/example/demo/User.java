package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String nameOnCard;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private long zip;
    private long creditCardNo;
    private String cvv;

    @Column (name = "username")
    @Size(min= 1)
    private String username;

    @Column(name = "email")
    @NotEmpty
    @NotNull
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "first_name")
    @NotEmpty
    @NotNull
    private String firstName;

    @Column (name = "last_name")
    @NotEmpty
    @NotNull
    private String lastName;

    @Column (name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user",
    cascade = CascadeType.REMOVE,
    fetch = FetchType.EAGER)
    private Set<Sale> sales;


    public User() {
    }
    public User(@Size(min=1) String username,
                @NotEmpty @NotNull String email,
                @NotEmpty @NotNull String password,
                @NotEmpty @NotNull String firstName,
                @NotEmpty @NotNull String lastName, boolean enabled,long creditCardNo) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.creditCardNo=creditCardNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
//        this.password = password;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void clearPassword() {
        this.password = "";
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public long getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(long creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
