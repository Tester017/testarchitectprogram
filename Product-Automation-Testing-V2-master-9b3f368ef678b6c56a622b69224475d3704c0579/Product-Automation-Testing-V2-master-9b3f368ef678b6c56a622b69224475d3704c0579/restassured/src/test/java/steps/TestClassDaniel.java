package steps;

import auth.OAuthStrategy;

public class TestClassDaniel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OAuthStrategy oa = new OAuthStrategy();
		String token = oa.getToken();
		
		System.out.println(token);

	}

}
