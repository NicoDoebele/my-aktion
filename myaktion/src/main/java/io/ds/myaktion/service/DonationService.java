package io.ds.myaktion.service;

import java.util.Optional;

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

    public Donation addDonation(Donation donation, Long campaignId) throws CampaignNotFoundException {
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);
        if (!campaign.isPresent()) throw new CampaignNotFoundException(null);
        donationRepository.save(donation);
        campaign.get().addDonation(donation);
        return donation;
    }
}
