package org.zezutom.arquillian.webapp;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;

import com.google.common.io.Files;

@RunWith(Arquillian.class)
public class LoginAndroidTestCase {	

	public static final String WEBAPP_SRC = "src/main/webapp";
	
	@Drone
	private AndroidDriver driver;
			
	@Test
	public void testGoogle() throws WebDriverException, IOException {
		
		// Visit google
		driver.get("http://www.google.com");
		
		// Find the text input element by its name
		WebElement el = driver.findElement(By.name("q"));
		Files.copy(driver.getScreenshotAs(OutputType.FILE), new File("target/screen.png"));
		
		// Enter something to search for
		el.sendKeys("Cheese!");
		
		// Submit the search
		el.submit();
		
		Files.copy(driver.getScreenshotAs(OutputType.FILE), new File("target/screen2.png"));
		
	}
	
}
