package appium_demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WeatherTest {
	
	 public static void main(String[] args) throws InterruptedException {

	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro Fold API 35");
	        caps.setCapability("appPackage", "com.example.weatherapp");
	        caps.setCapability("appActivity", "com.example.weatherapp.ui.MainActivity");
	        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
	        
	        
	        try {
	            // Initialize the Appium driver
	        	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
	        	 Thread.sleep(2000);

	            // Perform addition operation
	        	 WebElement cityName = driver.findElement(By.id("com.example.weatherapp:id/city"));
	        	 System.out.println("City name : "+cityName);
	        	 cityName.sendKeys("solapur");
		        	Thread.sleep(2000);

	        	 
	        	  WebElement shoButton = driver.findElement(By.id("com.example.weatherapp:id/showButton"));
	              shoButton.click();
		        	Thread.sleep(2000);

	              
	              WebElement resultView = driver.findElement(By.xpath("//android.widget.EditText[@hint='City Name']"));
	              Thread.sleep(2000);
	              System.out.println(resultView);
		        	Thread.sleep(2000);

	                if(resultView.isDisplayed()) {
	                  System.out.println("Successfully navigated to SecondActivity!!!");
	              } else {
	                  System.out.println("Not able to navigate!!!.");
	              }


	              System.out.println("Navigation to the second screen was successful!");

	              driver.quit();
	        	 
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }

}
}
