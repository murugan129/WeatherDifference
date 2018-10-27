package com.Weather.demo;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

public class TenDayForeCast_Day_8
{
    public static void main( String[] args )
    {
    	weather_Temperature("MA","Boston");
    	    	
    }
    
    public static void weather_Temperature(String strState, String strCity)
    {	
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/forecast10day/q/"+strState+"/"+strCity+".json").asString();
    	
    	int intTempHigh;
    	int intTempLow;
    	String strDate;
    	
    	intTempHigh = Integer.parseInt(JsonPath.read(body, "$.forecast.simpleforecast.forecastday[7].high.fahrenheit").toString());
    	intTempLow = Integer.parseInt(JsonPath.read(body, "$.forecast.simpleforecast.forecastday[7].low.fahrenheit").toString());
    	    	
    	strDate = JsonPath.read(body, "$.forecast.simpleforecast.forecastday[7].date.pretty").toString();
    
    		//Get the difference
    		int intTempDifference = intTempHigh - intTempLow;
    		System.out.println("State : " + strState + "| City : " + strCity + " |High Temperature : "+ intTempHigh + " Low Temperature : " + intTempLow);  
    		
    		if(intTempDifference < 20)
    		{
    			System.out.println("PASS |  Date : " + strDate + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}else
    		{
    			System.out.println("FAIL | State : " + strState + "| City : " + strCity + " |  Day : " + 0 + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}   
    }   
}

