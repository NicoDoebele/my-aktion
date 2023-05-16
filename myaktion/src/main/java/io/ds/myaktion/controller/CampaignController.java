package io.ds.myaktion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.service.CampaignService;

@RestController
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/campaigns")
    List<Campaign> getCampaigns() {
        List<Campaign> campaigns = campaignService.getCampaigns();

        for (Campaign campaign : campaigns) {
            Optional<Double> donationAmount = campaignService.getAmountDonatedSoFar(campaign.getId());
            if (donationAmount.isPresent()) campaign.setAmountDonatedSoFar(donationAmount.get());
            else campaign.setAmountDonatedSoFar(0);
        }

        return campaigns;
    }

    @PostMapping("/campaigns")
    Campaign addCampaign(@RequestBody Campaign newCampaign) {
        return campaignService.addCampaign(newCampaign);
    }

    @GetMapping("/campaigns/{id}")
    Campaign getCampaignById(@PathVariable String id) {
        Campaign campaign = campaignService.getCampaignById(Long.parseLong(id));
        Optional<Double> donationAmount = campaignService.getAmountDonatedSoFar(campaign.getId());
        if (donationAmount.isPresent()) campaign.setAmountDonatedSoFar(donationAmount.get());
        else campaign.setAmountDonatedSoFar(0);
        return campaign;
    }

    @PutMapping("/campaigns/{id}")
    Campaign updateCampaign(@PathVariable String id, @RequestBody Campaign campaign) {
        return campaignService.updateCampaign(Long.parseLong(id), campaign);
    }

    @DeleteMapping("/campaigns/{id}")
    void deleteCampaign(@PathVariable String id) {
        campaignService.deleteCampaign(Long.parseLong(id));
    }
}
