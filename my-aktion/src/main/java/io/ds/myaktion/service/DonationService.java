package io.ds.myaktion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.CampaignRepository;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.domain.DonationRepository;
import io.ds.myaktion.dto.ReducedDonation;
import io.ds.myaktion.exceptions.CampaignNotFoundException;

@Service
public class DonationService {

    private static String URL_MYAKTION_MONITOR = "http://localhost:8081/donations";
    
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
        log.info("Sending donation to myaktion-monitor");
        sendReducedDonation(donation);
        return addeDonation;
    }

    public List<Donation> getDonationsFromCampaign(Long campaignId) {
        return donationRepository.getDonationsFromCampaign(campaignId);
    }

    private void sendReducedDonation(Donation donation) {
        ReducedDonation reducedDonation = new ReducedDonation(donation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReducedDonation> entity = new HttpEntity<>(reducedDonation, headers);
        log.info("Send Message object: " + reducedDonation);
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(URL_MYAKTION_MONITOR, entity, String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            log.debug("Sent donation to myaktion-monitor successfully");
        } else {
            log.debug("Failed to send donation to myaktion-monitor. Http Status=" + response.getStatusCode());
        }
        } catch (RestClientException e) {
            // TODO Implement retry mechanism
            log.info("Failed to send donation to myaktion-monitor");
            log.error("Exception received trying to send donation:", e);
        }
    }
}
