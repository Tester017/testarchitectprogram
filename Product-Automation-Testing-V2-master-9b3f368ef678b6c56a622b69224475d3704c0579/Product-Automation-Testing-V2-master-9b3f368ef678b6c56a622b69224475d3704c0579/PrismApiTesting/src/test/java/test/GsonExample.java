package test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import constant.ProductConstant;

public class GsonExample {
	
	
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser parser = new JsonParser();
   	    JsonArray periodFilterArray = (JsonArray) parser.parse(new FileReader("./src/test/resources/GsonData.json"));
   	    
   	    for(int i=0;i<periodFilterArray.size();i++) {
   	    	JsonObject obj = periodFilterArray.get(i).getAsJsonObject();
   	    	if(i==0) {
   	    		JsonArray list = obj.get("checked").getAsJsonArray();
   	    		System.out.println(list.toString());
   	    	}
   	    	
   	    	 
   	    	
   	    	
   	    }
   	    
	}

}
