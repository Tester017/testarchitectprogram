package com.dz.prism.module.AddTask;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.dz.prism.main.AutomationDriver;
import com.dz.prism.utils.SeleniumUtils;
public class AddTaskMain {
	public static Properties Add_Task_PROP = null;
	public static final String moduleName = "AddTask";
	static{
		try {
			Add_Task_PROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\addtask.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public AddTaskMain(){
		if(Add_Task_PROP != null)
		{
			//MyListModule
			SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("DashBoard"), "xpath");
			WebElement Dash = SeleniumUtils.webDriver.findElement(By.xpath(AddTaskMain.Add_Task_PROP.getProperty("DashBoard")));
			Actions s= new Actions(SeleniumUtils.webDriver);
			s.moveToElement(Dash).build().perform();
			s.click(Dash).build().perform();
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			callTestCases();
			
		} 
		else {
			System.out.println("Add_Task properties file not loaded please verify");
		}
	}
	private void callTestCases(){
		try {

			if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
			Addtask.DashBoardpage();
				
				System.out.println("Executed Added_Task verification");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}







	

