package io.ds.myaktion.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Column;

@Entity
public class Campaign {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double donationMinimum;
    private double targetAmount;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "accountName"))
    })
    private Account account;

    @OneToMany(mappedBy = "campaign", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations = new LinkedList<Donation>();

    public void setId(Long id) {
        this.id = id;
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
}
