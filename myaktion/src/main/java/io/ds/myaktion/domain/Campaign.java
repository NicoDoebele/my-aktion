package io.ds.myaktion.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;

@Entity
public class Campaign {

    @Id
    @GeneratedValue
    @JsonProperty(access=Access.READ_ONLY)
    private Long id;

    private String name;
    private double donationMinimum;
    private double targetAmount;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "accountName"))
    })
    private Account account;

    @JsonProperty(access=Access.READ_ONLY)
    @OneToMany(mappedBy = "campaign", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations = new LinkedList<Donation>();

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDonationMinimum(double donationMinimum) {
        this.donationMinimum = donationMinimum;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDonationMinimum() {
        return donationMinimum;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public List<Donation> getDonations() {
        return donations;
    }

    @Override
    public String toString() {

        String donationsString = "[";

        for (Donation donation : donations) {
            donationsString += donation.getId().toString();
        }

        donationsString += "]";


        return "Campaign [id=" + id + ", name=" + name + ", donationMinimum=" + donationMinimum + ", targetAmount="
                + targetAmount + ", account=" + Objects.toString(account, "null") + ", donationIds=" + donationsString + "]";
    }
}
