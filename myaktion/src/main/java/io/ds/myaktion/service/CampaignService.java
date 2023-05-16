package io.ds.myaktion.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    private static final Logger log = LoggerFactory.getLogger(CampaignService.class);

    public Optional<Double> getAmountDonatedSoFar(long campaignId) {
        return campaignRepository.getAmountDonatedSoFar(campaignId);
    }

    public Campaign addCampaign(Campaign campaign) {
        Campaign addedCampaign = campaignRepository.save(campaign);
        log.trace("Added campaign " + addedCampaign.toString());
        return addedCampaign;
    }

    public List<Campaign> getCampaigns() {
        log.trace("Getting all campaigns");
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(long id) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));
        log.trace("Got campaign " + campaign.toString());
        return campaign;
    }

    public void deleteCampaign(long id) {
        log.trace("Deleting campaign with id " + id);
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(long id, Campaign newCampaign) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));

        newCampaign.setId(campaign.getId());

        campaignRepository.deleteById(campaign.getId());

        Campaign addedCampaign = campaignRepository.save(newCampaign);

        log.trace("Replaced campaign with id " + id + " with campaign " + addedCampaign.toString());

        return addedCampaign;
    }
}
