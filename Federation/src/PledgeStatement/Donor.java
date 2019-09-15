package PledgeStatement;

import java.util.ArrayList;
import java.util.Collections;

public class Donor {

	private Name name;
	private Address address;
	private String phoneVoice;
	private String phoneMobile;
	private String email;
	private ArrayList<Campaign> campaigns;
	
	private double openTotals;

	public Donor(Name name, Address address, String phoneVoice, String phoneMobile, String email, ArrayList<Campaign> campaigns) {
		this.name = name;
		this.address = address;
		this.phoneVoice = phoneVoice;
		this.phoneMobile = phoneMobile;
		this.email = email;
		this.campaigns = campaigns;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneVoice() {
		return phoneVoice;
	}

	public void setPhoneVoice(String phoneVoice) {
		this.phoneVoice = phoneVoice;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public Campaign getCampaign(int year) {
		for(int i = 0; i < campaigns.size(); i++)
			if (Integer.parseInt(campaigns.get(i).getCampaignYear()) == year) return campaigns.get(i);
		return new Campaign(0, 0, year + "");
	}

	public void setCampaigns(ArrayList<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public double getOpenTotals() {
		return openTotals;
	}

	public void setOpenTotals(double openTotals) {
		this.openTotals = openTotals;
	}

	public void recalculateOpenTotals() {
		for(Campaign c : campaigns) {
			openTotals += c.getOpen();
		}
	}

	public void updateCampaign(Campaign campaign) {
		boolean flag = false;
		for(Campaign c : campaigns) {
			if(c.getCampaignYear().equals(campaign.getCampaignYear())) {
				flag = true;
				Campaign temp = c;
				campaigns.remove(c);
				temp.setOpen(temp.getOpen() + campaign.getOpen());
				temp.setPledge(temp.getPledge() + campaign.getPledge());
				campaigns.add(c);
			}
		}
		if(!flag) campaigns.add(campaign);
		Collections.sort(campaigns);
		calculateOpenTotals();
	}
	
	public void calculateOpenTotals() {
		double total = 0;
		for(Campaign c : campaigns) {
			total += c.getOpen();
		}
		openTotals = total;
	}

}
