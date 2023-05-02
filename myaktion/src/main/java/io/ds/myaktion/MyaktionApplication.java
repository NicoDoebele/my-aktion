package io.ds.myaktion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.ds.myaktion.domain.Campaign;
import io.ds.myaktion.domain.Donation;
import io.ds.myaktion.service.CampaignService;
import io.ds.myaktion.service.DonationService;

@SpringBootApplication
public class MyaktionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyaktionApplication.class, args);
	}

	@Autowired
	CampaignService campaignService;

	@Autowired
	DonationService donationService;
	
	@Bean
	CommandLineRunner runner() {
		return (args) -> {

			Campaign campaignOne = new Campaign();

			Campaign savedCampaignOne = campaignService.addCampaign(campaignOne);
			long campaignId = savedCampaignOne.getId();

			Donation donationOne = new Donation();

			donationService.addDonation(donationOne, campaignId);

			Donation donationTwo = new Donation();

			donationService.addDonation(donationTwo, campaignId);

			List<Campaign> campaigns = campaignService.getCampaigns();

			for (Campaign campaign : campaigns) {
				System.out.println("The campaign with the id " + campaign.getId() + " holds the following donation ids:");

				List<Donation> donations = campaign.getDonations();

				for (Donation donation : donations) {
					System.out.println(donation.getId());
				}
			}
		};
	}

}
