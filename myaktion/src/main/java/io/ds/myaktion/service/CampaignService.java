package io.ds.myaktion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign addCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
        return campaign;
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return campaigns;
    }
}
