package data;

import java.util.Set;
import java.util.logging.Logger;

import redis.clients.jedis.Jedis;

public class RedisDataEngine {
	
	// make a connection 
	static Jedis jedis = new Jedis("localhost",6379);
	private static final Logger logger = Logger.getLogger(RedisDataEngine.class.getName());
	
	/**
	 * Push the record into redis keys (table)
	 * {@link LeadInfo} - The class that has all data information about the Lead
	 * Key : LeadId is the unique parameter (Hset) >> O(1)
	 * 
	 */
	public static void saveLeadRedis(LeadInfo leadInfo) {
		
		// Get the lead id as key
		String leadId = "Lead: "+leadInfo.getLeadId();
		
		jedis.hset(leadId, "companyName", leadInfo.getCompanyName());
		jedis.hset(leadId, "firstName", leadInfo.getFirstName());
		jedis.hset(leadId, "lastName", leadInfo.getLastName());
		jedis.hset(leadId, "phoneNumber", leadInfo.getPhoneNumber());
		
		logger.info("The lead "+leadId+" is stored in the redis");
		
	}
	
	/**
	 * Fetch the lead records (key info) from Redis - only one !!
	 * Top 1 or Random one ?
	 */
	public static LeadInfo getRandomLeadRedis() {
		String leadId = null;
		
		// Get all the keys
		Set<String> leadKeys = jedis.keys("Lead:*");
		
		// Get first lead and get all lead info from redis
		if(!leadKeys.isEmpty()) {
			
			// Fetch the first one
			leadId = leadKeys.iterator().next();
			
		} else {
			throw new RuntimeException("There are no leads matching in the redis");
		}
		
		// LeadInfo
		LeadInfo leadInfo = new LeadInfo();
		leadInfo.setLeadId(leadId.replaceAll("Lead: ", ""));
		leadInfo.setCompanyName(jedis.hget(leadId, "companyName"));
		leadInfo.setFirstName(jedis.hget(leadId, "firstName"));
		leadInfo.setLastName(jedis.hget(leadId, "lastName"));
		leadInfo.setPhoneNumber(jedis.hget(leadId, "phoneNumber"));
		
		return leadInfo;
		
	}
	
	/**
	 * Delete an existing record from redis
	 */
	public static void deleteLeadRedis(String leadId) {
		String deleteLeadId = "Lead: "+leadId;
		if(jedis.exists(deleteLeadId)) {
			jedis.del(deleteLeadId);
			logger.info("The lead "+leadId+" is removed from redis");
		}
	}
	
	
	
	
	
	
}
