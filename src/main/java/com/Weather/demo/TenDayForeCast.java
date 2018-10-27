package com.Weather.demo;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

public class TenDayForeCast 
{
    public static void main( String[] args )
    {
    	weather_Temperature("MA","Boston");
    	    	
    }
    
    public static void weather_Temperature(String strState, String strCity)
    {	
    	String body = RestAssured.given().get("http://api.wunderground.com/api/99a8db9a0f3c2e31/forecast10day/q/"+strState+"/"+strCity+".json").asString();
    	
    	int[] arrTempHigh = new int[10];
    	int[] arrTempLow = new int[10];
    	String[] arrDate = new String[10];
    	
    	for(int i=0; i < 10; i++)
    	{
    		arrTempHigh[i] = Integer.parseInt(JsonPath.read(body, "$.forecast.simpleforecast.forecastday["+i+"].high.fahrenheit").toString());
    	}
    	
    	for(int j=0; j < 10; j++)
    	{
    		arrTempLow[j] = Integer.parseInt(JsonPath.read(body, "$.forecast.simpleforecast.forecastday["+j+"].low.fahrenheit").toString());
    	}
    	
    	for(int k=0; k < 10; k++)
    	{
    		arrDate[k] = JsonPath.read(body, "$.forecast.simpleforecast.forecastday["+k+"].date.pretty").toString();
    	}
    	
    	for(int iTemp=0; iTemp < 10; iTemp++)
    	{
    		int intTempDifference = arrTempHigh[iTemp] - arrTempLow[iTemp];
    		System.out.println("State : " + strState + "| City : " + strCity + " |High Temperature : "+ arrTempHigh[iTemp] + " Low Temperature : " + arrTempLow[iTemp]);  
    		
    		if(intTempDifference < 20)
    		{
    			System.out.println("PASS |  Date : " + arrDate[iTemp] + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}else
    		{
    			System.out.println("FAIL | State : " + strState + "| City : " + strCity + " |  Day : " + iTemp + " Temperature difference is: " + intTempDifference);
    			System.out.println();
    		}
    	}	

    }   
}

