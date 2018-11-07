package com.Weather.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Compare10DayLowHighTemperatureDay3 {

	public static void main(String[] args) {
		try
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.weather.com");
			driver.manage().deleteAllCookies();
			WebDriverWait wait=new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='undefined']")));
			String state = "OH";
			String city = "Orrville";
			driver.findElement(By.xpath("//input[@value='undefined']")).click();
			driver.findElement(By.xpath("//input[@value='undefined']")).sendKeys(city+", "+ state);
			driver.findElement(By.xpath("//input[@value='undefined']")).sendKeys(Keys.RETURN);
			WebElement lnk10Day;
			lnk10Day= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = '10 Day']")));
			lnk10Day.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'10 Day Weather')]")));
			
			//Get Date
			String strDay = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[2]//span[@class='date-time']")).getText();
			String strDate = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[2]//span[starts-with(@class,'day-detail')]")).getText();
			System.out.println("Today's Day is : " + strDay);
			System.out.println("Today's Date is : " + strDate);
			
			//Get weather description
			String desc = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[3]")).getText();
			System.out.println("Today's weather is : " + desc);
			
			//Get Temperature
			String highLow = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[4]")).getText();
			String[] arrhighLow = highLow.split("°");
			System.out.println("Today's High Temperature is : " + arrhighLow[0]);
			System.out.println("Today's Low Temperature is : " + arrhighLow[1]);
			
			//Get Precipitation
			String preceip = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[5]")).getText();
			System.out.println("Today's Preceiptation is : " + preceip);
			
			//Get Wind
			String wind = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[6]")).getText();
			System.out.println("Today's Wind is : " + wind);
			
			//Get Humidity
			String humidity = driver.findElement(By.xpath("//table[@class='twc-table']//tr[3]/td[7]")).getText();
			System.out.println("Today's Humidity is : " + humidity);
			
			//Validate temperature difference
			//Get the difference
			int intHighTemp = Integer.parseInt(arrhighLow[0]); 
		    int intLowTemp = Integer.parseInt(arrhighLow[1]);
    		
			int intTempDifference = intHighTemp - intLowTemp;
    		
    		System.out.println("State : " + state + "| City : " + city + " |High Temperature : "+ intHighTemp + " Low Temperature : " + intLowTemp);  
    		
    		if(intTempDifference < 20)
    		{
    			System.out.println("PASS |  Day : " + strDay + " Date : " + strDate + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}else
    		{
    			System.out.println("FAIL |  Day : " + strDay + " Date : " + strDate + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}   
		
			driver.quit();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
