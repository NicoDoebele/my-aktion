package io.ds.myaktion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign addCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(long id) throws CampaignNotFoundException {
        return campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));
    }

    public void deleteCampaign(long id) {
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(long id, Campaign newCampaign) throws CampaignNotFoundException {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException(null));

        newCampaign.setId(campaign.getId());

        campaignRepository.deleteById(campaign.getId());

        return campaignRepository.save(newCampaign);
    }
}
