package io.ds.myaktion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.service.DonationService;

@RestController
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PostMapping("/campaigns/{id}/donations")
    Donation addDonation(@PathVariable String id, @RequestBody Donation donation) {
        return donationService.addDonation(donation, Long.parseLong(id));
    }

    @GetMapping("/campaigns/{id}/donations")
    List<Donation> getDonations(@PathVariable String id) {
        return donationService.getDonationsFromCampaign(Long.parseLong(id));
    }
}
