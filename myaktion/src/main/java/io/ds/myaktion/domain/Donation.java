package io.ds.myaktion.domain;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Donation {

    @Id
    @GeneratedValue
    @JsonProperty(access=Access.READ_ONLY)
    private Long id;

    private double amount;
    private boolean receiptRequested;
    private String donorName;

    public enum Status {
        IN_PROCESS, TRANSFERRED
    }

    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_PROCESS;

    @Embedded
    private Account account;

    @ManyToOne
    @JsonIgnore
    private Campaign campaign;

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setReceiptRequested(boolean receiptRequested) {
        this.receiptRequested = receiptRequested;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isReceiptRequested() {
        return receiptRequested;
    }

    public String getDonorName() {
        return donorName;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "id: " + id + " amount: " + amount + " receipt requested: " + receiptRequested + " donor name: " + donorName + " status: " + status + " in campaign: " + Objects.toString(campaign, "null");
    }
}
