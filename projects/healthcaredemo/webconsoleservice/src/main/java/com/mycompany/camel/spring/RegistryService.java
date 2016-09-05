package com.mycompany.camel.spring;

public class RegistryService {
	
	public String registryPatient(Patient patient){
		return createHL7NotificationString(patient);
	}
	
	private String createHL7NotificationString(Patient patient){

		
		StringBuffer sb = new StringBuffer();
		sb.append("MSH|^~\\&|||||20160314112147.232+0800||ADT^A01^ADT_A01|801|T|2.6"+"\r");
		sb.append("EVN||"+RegistryUtil.genEventId()+"||||"+RegistryUtil.genEventId()+"\r");
		sb.append("PID|||56782445^^^UAReg^PI||"+patient.getFamilyName()+"^"+patient.getName()+"^Q^JR||"+patient.getBirthday()+"|"+patient.getGender()+"||2028-9^^HL70005^"
		+patient.getHisId()+"^^XYZ|"+RegistryUtil.genAddress()+"^^M~"
		+patient.getEmergencycontact()+"^"+patient.getEmergencycontactAddress()+"^^O|||||||0105I30001^^^99DEF^AN"+"\r");
		sb.append("AL1|||99999998^No Known Drug Allergies");
		

		return sb.toString();
	}
	
	
	

}
