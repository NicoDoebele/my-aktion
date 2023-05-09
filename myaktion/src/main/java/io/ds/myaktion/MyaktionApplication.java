package io.ds.myaktion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(MyaktionApplication.class);

	public static void main(String[] args) {
		log.info("Starting application");
		SpringApplication.run(MyaktionApplication.class, args);
	}

	@Autowired
	CampaignService campaignService;

	@Autowired
	DonationService donationService;
	
	@Bean
	CommandLineRunner runner() {
		return (args) -> {

			log.info("Adding Sample data to DB");

			Campaign campaignOne = new Campaign();

			Campaign savedCampaignOne = campaignService.addCampaign(campaignOne);
			long campaignId = savedCampaignOne.getId();

			Donation donationOne = new Donation();

			log.debug("Add campaign to DB");

			donationService.addDonation(donationOne, campaignId);

			Donation donationTwo = new Donation();

			log.debug("Add donation to campaign with id");

			donationService.addDonation(donationTwo, campaignId);

			List<Campaign> campaigns = campaignService.getCampaigns();

			log.debug("Read all campaigns");

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
