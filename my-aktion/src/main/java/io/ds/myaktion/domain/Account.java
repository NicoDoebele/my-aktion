package io.ds.myaktion.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Account {

    @Pattern(regexp = "[A-Z]{2}[0-9]{2}[a-zA-Z0-9]{12,30}", message = "An IBAN starts with two big letters followed by two digits and 12 to 30 alphanumeric characters.")
    private String iban;

    @Size(min = 5, max = 40, message = "Length of donor name must be at least 5 and at most 40.")
    private String name;

    @Size(min = 5, max = 60, message = "Length of Name of account owner must be at least 5 and at most 60.")
    private String nameOfBank;

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    public String getIban() {
        return iban;
    }

    public String getName() {
        return name;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    @Override
    public String toString() {
        return "Account [iban=" + iban + ", name=" + name + ", nameOfBank=" + nameOfBank + "]";
    }
}
