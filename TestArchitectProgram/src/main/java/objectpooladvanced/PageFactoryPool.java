package objectpooladvanced;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.WebDriver;

import pages.LoginPage;

public class PageFactoryPool {
	
	ConcurrentMap<String, BlockingQueue<Object>> pagePool;
	ConcurrentMap<WebDriver, String> objToPageKey;

	public PageFactoryPool() {
		pagePool = new ConcurrentHashMap<String, BlockingQueue<Object>>();
		objToPageKey = new ConcurrentHashMap<WebDriver, String>();
	}
	
	public Object getObject(String className) throws SecurityException, ClassNotFoundException {
		
		pagePool.putIfAbsent(className, new LinkedBlockingQueue<Object>());
		Object classObj = pagePool.get(className).poll();
		if (classObj == null) {
			
			classObj = Class.forName(className).getDeclaredConstructors();
		}
		return className;
		
	}

}
