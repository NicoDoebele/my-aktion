package io.ds.myaktion.service;

import java.util.List;

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

    public Campaign addCampaign(Campaign campaign) {
        log.trace("Adding campaign " + campaign.toString());
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaigns() {
        log.trace("Getting all campaigns");
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(long id) throws CampaignNotFoundException {
        log.trace("Getting campaign with id " + id);
        return campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));
    }

    public void deleteCampaign(long id) {
        log.trace("Deleting campaign with id " + id);
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(long id, Campaign newCampaign) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));

        log.trace("Replacing campaign id " + id + " with campaign " + newCampaign.toString());

        newCampaign.setId(campaign.getId());

        campaignRepository.deleteById(campaign.getId());

        return campaignRepository.save(newCampaign);
    }
}
