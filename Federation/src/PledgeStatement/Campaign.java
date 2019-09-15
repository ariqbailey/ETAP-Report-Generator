package PledgeStatement;

public class Campaign implements Comparable{
	
	private double pledge;
	private double open_var;
	private String campaignYear;
	
	public Campaign() {
		pledge = 0;
		open_var = 0;
		campaignYear = "";
	}
	
	public Campaign(double p, double o, String year) {
		pledge = p;
		open_var = o;
		campaignYear = year;
	}
	
	public String getCampaignYear() {
		return campaignYear;
	}

	public void setCampaignYear(String campaignYear) {
		this.campaignYear = campaignYear;
	}

	public double getPledge() {
		return pledge;
	}

	public void setPledge(double d) {
		this.pledge = d;
	}

	public double getOpen() {
		return open_var;
	}

	public void setOpen(double openAmmount) {
		this.open_var = openAmmount;
	}

	@Override
	public int compareTo(Object obj) {
		return this.getCampaignYear().compareTo(((Campaign) obj).getCampaignYear());
	}
	
	

}
