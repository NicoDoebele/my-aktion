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
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;

@Entity
public class Campaign {

    @Id
    @GeneratedValue
    @JsonProperty(access=Access.READ_ONLY)
    private Long id;

    @Size(min = 4, max = 30, message = "Length of campaign name must be at least 4 and at most 30.")
    private String name;

    @DecimalMin(value = "1.00", message = "The amount of the donation must be at least 1.")
    private double donationMinimum;

    @DecimalMin(value = "10.00", message = "The target amount of the campaign must be at least 10 Euro.")
    private double targetAmount;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "accountName"))
    })
    private Account account;

    @Transient
    private double amountDonatedSoFar;

    @OneToMany(mappedBy = "campaign", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
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

        boolean firstIteration = true;

        for (Donation donation : donations) {
            if (!firstIteration) donationsString += ",";
            else firstIteration = false;
            donationsString += donation.getId().toString();
        }

        donationsString += "]";


        return "Campaign [id=" + id + ", name=" + name + ", donationMinimum=" + donationMinimum + ", targetAmount="
                + targetAmount + ", account=" + Objects.toString(account, "null") + ", donationIds=" + donationsString + "]";
    }
}
