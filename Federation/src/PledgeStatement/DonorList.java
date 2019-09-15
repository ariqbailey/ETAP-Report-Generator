package PledgeStatement;
import java.util.ArrayList;

public class DonorList {
	
	ArrayList<Donor> donorList = new ArrayList<Donor>();
	
	public DonorList() {
	}
	
	public boolean contains(String name) { 
		for(Donor d : donorList) {
			if(d.getName().getFullName().equals(name)) return true;
		}
		return false;
	}
	
	public ArrayList<Donor> getList() {
		return donorList;
	}
	
	public int getIndex(String name) {
		int i = 0;
		for(Donor d : donorList) {
			if(d.getName().getFullName().equals(name)) return i;
			i++;
		}
		try {
			throw new Exception("Element Does Not Exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void add(Name name, Address address, String phoneVoice, String voiceMobile, String email) {
		if(!contains(name.getFullName())) donorList.add(new Donor(name, address, phoneVoice, voiceMobile, email, new ArrayList<Campaign>()));
	}
	
	public void updateCamapign(String name, Campaign campaign) {
		Donor temp = donorList.get(getIndex(name));
		temp.updateCampaign(campaign);
		donorList.set(getIndex(name), temp);
	}
}
