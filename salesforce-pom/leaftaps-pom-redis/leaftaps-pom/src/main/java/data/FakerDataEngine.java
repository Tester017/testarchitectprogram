package data;

import com.github.javafaker.Faker;

public class FakerDataEngine {
	
	private static final Faker faker = new Faker();
	
	public static LeadInfo getLeadInfo() {
		LeadInfo lead = new LeadInfo();
		
		lead.setCompanyName(faker.company().name());
		lead.setFirstName(faker.name().firstName());
		lead.setLastName(faker.name().lastName());
		lead.setPhoneNumber(faker.phoneNumber().cellPhone());
		
		return lead;
		
	}
	
	
}
