package BusAlarmScreen;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.text.*;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.awt.List;
import java.io.*;

public class BusAPI {

	ArrayList<Integer> BusPassengerRide_List=new ArrayList<Integer>();
	ArrayList<Integer> BusPassengerAlight_List=new ArrayList<Integer>();
	
	public BusAPI() {
			
	}

	public NodeList BusInfo() {
		NodeList nList = null;
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.seoul.go.kr:8088/7849444d62796c3435377954726871/xml/CardBusTimeNew/1/129/201708/152/");

		InputStream tmpIS = null;
		BufferedReader rd = null;

		try {
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				tmpIS = conn.getInputStream();
			} else {
				tmpIS = conn.getErrorStream();
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(tmpIS);
			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("row");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return nList;
	}

	public String GetBusStopInfo(int i) {
		String s = null;

		NodeList nList = BusInfo();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (temp == i) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					s ="<html>"+ eElement.getElementsByTagName("BSST_ARS_NO").item(0).getTextContent()+"<br>"+eElement.getElementsByTagName("BUS_STA_NM").item(0).getTextContent()+"</html>";
					break;
				}
			}
		}

		return s;

	}
	
	public String GetBusInfo()
	{
		String s;
		NodeList nList = BusInfo();
		 Node nBasic=nList.item(0);
		 Element eBasic=(Element)nBasic;
		 s= "<html>"+"³¯Â¥ : "+eBasic.getElementsByTagName("USE_MON").item(0).getTextContent()+"<br>"
		 +"¹ö½º³ë¼± : "+eBasic.getElementsByTagName("BUS_ROUTE_NO").item(0).getTextContent()+"<br>"
		 +"³ë¼±¸í : "+eBasic.getElementsByTagName("BUS_ROUTE_NM").item(0).getTextContent();
		 return s;
	}
	
	public void GetBusPassengerInfo(int i)
	{

		NodeList nList = BusInfo();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					System.out.println("¿ª¸í :" + eElement.getElementsByTagName("BUS_STA_NM").item(0).getTextContent());
					System.out.println(temp);
					 String[] array = new String[]{"MIDNIGHT","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN",
							 "ELEVEN","TWELVE","THIRTEEN","FOURTEEN","FIFTEEN","SIXTEEN","SEVENTEEN","EIGHTEEN","NINETEEN","TWENTY",
							 "TWENTY_ONE","TWENTY_TWO","TWENTY_THREE"};
					 
					 int daily_ride_passengers=Integer.parseInt(eElement.getElementsByTagName(array[i] + "_RIDE_NUM").item(0).getTextContent())/ 30 ;
					 int daily_alight_passengers=Integer.parseInt(eElement.getElementsByTagName(array[i] + "_ALIGHT_NUM").item(0).getTextContent())/ 30 ;

			
					daily_ride_passengers=Integer.parseInt(eElement.getElementsByTagName(array[i] + "_RIDE_NUM").item(0).getTextContent())/calculate_passengers(daily_ride_passengers);
					daily_alight_passengers=Integer.parseInt(eElement.getElementsByTagName(array[i] + "_ALIGHT_NUM").item(0).getTextContent())/calculate_passengers(daily_alight_passengers);

					 System.out.println(i+"½Ã ½ÂÂ÷ ÃÑ°´¼ö : "+daily_ride_passengers);
					 //+ Integer.parseInt(eElement.getElementsByTagName(array[i]+"_RIDE_NUM").item(0).getTextContent())/30);
					 System.out.println(i+"½Ã ÇÏÂ÷ ÃÑ°´¼ö : "+daily_alight_passengers);
//					 System.out.println(i+"½Ã ÇÏÂ÷ ÃÑ°´¼ö : "+eElement.getElementsByTagName(array[i]+"_ALIGHT_NUM").item(0).getTextContent()+" ");
					 
					 BusPassengerRide_List.add(daily_ride_passengers);
					 BusPassengerAlight_List.add(daily_alight_passengers);
					 System.out.println();
				}
			
		}
		
	}
	public int calculate_passengers(int daily_passengers)
	{
		 int gap = 30;

		if (daily_passengers > 80) {
			gap = (int)(Math.random()*20)+60;
		} else if (daily_passengers > 60) {
			gap = (int)(Math.random()*20)+50;
		} else if (daily_passengers > 40) {
			gap = (int)(Math.random()*20)+40;
		} else if (daily_passengers > 20) {
			gap = (int)(Math.random()*20)+30;
		}
		System.out.println(gap);
		return gap;

	}

}
