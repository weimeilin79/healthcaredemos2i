package com.mycompany.camel.spring;

import java.util.Calendar;
import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ClinicRegistryService implements Processor {
	
	HashMap<String, String> allRegisteredInfo = new HashMap<String, String>();
	
	
	public String orderTest(String hsId,String dept ,String testDetail){
		String hl7content = ""; 
		if(allRegisteredInfo.get(hsId)==null){
		
			StringBuffer defaultHL7SB = new StringBuffer();
			defaultHL7SB.append("MSH|^~\\&|||||20160314112147.232+0800||ADT^A01^ADT_A01|801|T|2.6"+"\r");
			defaultHL7SB.append("EVN||384928479324837||||394802394832049\r");
			defaultHL7SB.append("PID|||56782445^^^UAReg^PI||DemoFamilyName^DemoName^Q^JR||19890406|M||2028-9^^HL70005^"
			+hsId+"^^XYZ|848 Valley View Road^^Rockford^MI^36272^^M~Thokozani Helmfrid^918 Rosewood Drive^^Ypsilanti^MI^091739^^O|||||||0105I30001^^^99DEF^AN"+"\r");
			defaultHL7SB.append("AL1|||99999998^No Known Drug Allergies\r");
			
			hl7content = defaultHL7SB.toString();
		}else{
			hl7content= allRegisteredInfo.get(hsId);
		}
		
		
		hl7content +="OBR|1|845439^GHH OE|1045813^"+dept+"|1554-5^"+testDetail+"|||"
		+Calendar.getInstance().get(Calendar.YEAR)
		+(Calendar.getInstance().get(Calendar.MONTH ) + 1)
		+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
		+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
		+Calendar.getInstance().get(Calendar.MINUTE)
		+"|||||||||\r";
		
		return hl7content;
	}
	
	public String recordObservation(String hl7content, String observation){
		
		return hl7content + "OBX|1|NM|^Clinic Observation||"+observation+"||||||F\r";
	}
	
	
	public String orderRadiology(String hisId){
		
		return "";
	}
	

	/*
	 * 
	 * */
	private void storeAllRegisteredInfo(String hsId, String hl7content){
		allRegisteredInfo.put(hsId, hl7content);
	}

	/*
	 * 
	 * */
	public String getAllRegisteredInfo(String hsId){
		return allRegisteredInfo.get(hsId);
	}
	
	public String getHL7Content(){
		
		return "";
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Clinic------->hsId:["+exchange.getIn().getHeader("hsId")+"]");
		System.out.println("Clinic------->familyname:["+exchange.getIn().getHeader("familyname")+"]");
		System.out.println("Clinic------->firstname:["+exchange.getIn().getHeader("firstname")+"]");
		System.out.println("Clinic------->emergency:["+exchange.getIn().getHeader("emergency")+"]");
		System.out.println("Clinic------->address:["+exchange.getIn().getHeader("address")+"]");
		System.out.println("Clinic------->emergencyaddress:["+exchange.getIn().getHeader("emergencyaddress")+"]");
		
		this.storeAllRegisteredInfo((String)exchange.getIn().getHeader("hsId"), exchange.getIn().getBody().toString());
	}
	
	
	
}
