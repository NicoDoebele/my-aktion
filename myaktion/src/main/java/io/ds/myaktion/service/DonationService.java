package io.ds.myaktion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.domain.DonationRepository;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class DonationService {
    
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    public Donation addDonation(Donation donation, Long campaignId) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException(null));
        donation.setCampaign(campaign);
        Donation addeDonation = donationRepository.save(donation);
        log.trace("Added donation " + addeDonation.toString() + " to campaign with id " + campaignId);
        return addeDonation;
    }
}
