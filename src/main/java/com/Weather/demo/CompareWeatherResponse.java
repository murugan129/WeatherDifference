package com.Weather.demo;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

public class CompareWeatherResponse 
{
    public static void main( String[] args )
    {
    
    	String[] arrState = new String[5];
    	
    	arrState[0] = "FL";
    	arrState[1] = "CO";
    	arrState[2] = "MA";
    	arrState[3] = "NJ";
    	arrState[4] = "TX";
    	
    	String[] arrCity = new String[5];
    	
    	arrCity[0] = "Miami";
    	arrCity[1] = "Denver";
    	arrCity[2] = "Boston";
    	arrCity[3] = "Edison";
    	arrCity[4] = "Dallas";
    	
    	String[] arrTemp = new String[5];
    	
    	arrTemp[0] = weather_Temperature("FL","Miami");
    	arrTemp[1] = weather_Temperature("CO","Denver");
    	arrTemp[2] = weather_Temperature("MA","Boston");
    	arrTemp[3] = weather_Temperature("NJ","Edison");
    	arrTemp[4] = weather_Temperature("TX","Dallas");
    	
        String[] arrFeelsLike = new String[5];
    	
        arrFeelsLike[0] = weather_FeelsLike("FL","Miami");
        arrFeelsLike[1] = weather_FeelsLike("CO","Denver");
        arrFeelsLike[2] = weather_FeelsLike("MA","Boston");
        arrFeelsLike[3] = weather_FeelsLike("NJ","Edison");
        arrFeelsLike[4] = weather_FeelsLike("TX","Dallas");  	    	
    	
    	String[] arrWindChill = new String[5];
    	
    	arrWindChill[0] = weather_WindChill("FL","Miami");
    	arrWindChill[1] = weather_WindChill("CO","Denver");
    	arrWindChill[2] = weather_WindChill("MA","Boston");
    	arrWindChill[3] = weather_WindChill("NJ","Edison");
    	arrWindChill[4] = weather_WindChill("TX","Dallas");    	
    	
    	String[] arrWind = new String[5];
    	
    	arrWind[0] = weather_Wind("FL","Miami");
    	arrWind[1] = weather_Wind("CO","Denver");
    	arrWind[2] = weather_Wind("MA","Boston");
    	arrWind[3] = weather_Wind("NJ","Edison");
    	arrWind[4] = weather_Wind("TX","Dallas");
    	
    	String[] arrHumidity = new String[5];
    	
    	arrHumidity[0] = weather_Humidity("FL","Miami");
    	arrHumidity[1] = weather_Humidity("CO","Denver");
    	arrHumidity[2] = weather_Humidity("MA","Boston");
    	arrHumidity[3] = weather_Humidity("NJ","Edison");
    	arrHumidity[4] = weather_Humidity("TX","Dallas");
    	
    	String[] arrPreceip = new String[5];
    	
    	arrPreceip[0] = weather_Preciptation("FL","Miami");
    	arrPreceip[1] = weather_Preciptation("CO","Denver");
    	arrPreceip[2] = weather_Preciptation("MA","Boston");
    	arrPreceip[3] = weather_Preciptation("NJ","Edison");
    	arrPreceip[4] = weather_Preciptation("TX","Dallas");
    	
    	System.out.println("State|City|  Temperature|Feels Lik|Wind Chill |Wind|Humidity|Preciptation");
	 	
    	for(int i = 0; i< 5; i++)
    	{
    		System.out.println(arrState[i]+ " |" + arrCity[i] + "    |" + arrTemp[i] + "        |" + arrFeelsLike[i] + "       |" + arrWindChill[i] + "       |"+ arrWind[i] + "      |" +  arrHumidity[i] + "       |" + arrPreceip[i]);
    	}    	
    }
    
    public static String weather_Temperature(String strState, String strCity)
    {	
    	String strTemperature = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strTemperature = JsonPath.read(body, "$.current_observation.temp_f").toString();
        
		return strTemperature;         
    }
    
    public static String weather_FeelsLike(String strState, String strCity)
    {	
    	String strFeelsLike = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strFeelsLike = JsonPath.read(body, "$.current_observation.feelslike_f").toString();
        
		return strFeelsLike;         
    }
    
    public static String weather_WindChill(String strState, String strCity)
    {	
    	String strWindChill = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strWindChill = JsonPath.read(body, "$.current_observation.windchill_f").toString();
        
		return strWindChill;         
    }
    
    public static String weather_Wind(String strState, String strCity)
    {	
    	String strWind = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strWind = JsonPath.read(body, "$.current_observation.wind_mph").toString();
        
		return strWind;         
    }
    
    public static String weather_Humidity(String strState, String strCity)
    {	
    	String strHumidity = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strHumidity = JsonPath.read(body, "$.current_observation.relative_humidity").toString();
        
		return strHumidity;         
    }
    
    public static String weather_Preciptation(String strState, String strCity)
    {	
    	String strPreciptation = "";
    	String body = RestAssured.given().get("http://api.wunderground.com/api/Key/conditions/q/"+strState+"/"+strCity+".json").asString();
    	strPreciptation = JsonPath.read(body, "$.current_observation.precip_today_in").toString();
        
		return strPreciptation;         
    }
    
   }

