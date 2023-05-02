package io.ds.myaktion.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Account {
    private String iban;
    private String name;
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
}
