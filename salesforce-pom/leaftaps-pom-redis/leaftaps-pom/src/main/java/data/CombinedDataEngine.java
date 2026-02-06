package data;

public class CombinedDataEngine {
	
	public static LeadInfo fetchData() {
		
		LeadInfo fakerLeadInfo = FakerDataEngine.getLeadInfo();
//		LeadInfo redisLeadInfo = RedisDataEngine.getRandomLeadRedis();
//		redisLeadInfo.setCompanyName(fakerLeadInfo.getCompanyName());
		//System.out.println(leadInfo.getLeadId());
		//leadInfo.setLeadId(JdbcDataRecord.getRandomLeadId());
		return fakerLeadInfo;
		
	}

}
