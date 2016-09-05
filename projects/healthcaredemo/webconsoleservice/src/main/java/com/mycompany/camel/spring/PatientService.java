package com.mycompany.camel.spring;

import java.util.HashMap;



public class PatientService {
	HashMap<String, Patient> allPatients = new HashMap<String, Patient>();

	public Patient getPatientInfo(String familyName, String firstName, String hisId){
		Patient patient = new Patient();
		
		if(allPatients.containsKey(hisId)){
			return allPatients.get(hisId);
		}
		
		patient.setHisId(hisId);
		patient.setFamilyName(familyName);
		patient.setName(firstName);
		patient.setGender(RegistryUtil.genRandomGender());
		patient.setAddress(RegistryUtil.genAddress());
		patient.setBirthday(RegistryUtil.genRandomBirthday());
		patient.setEmergencycontact(RegistryUtil.genAddress());
		patient.setEmergencycontactAddress(RegistryUtil.genAddress());
		
		allPatients.put(hisId, patient);
		
		return patient;
	}
	
	public String getAllPatientsInTxt(){
		
		StringBuffer sb = new StringBuffer();
		for(Patient patient: allPatients.values()){
			sb.append(patient.getFamilyName());
			sb.append("\t");
			sb.append(patient.getName());
			sb.append("\t");
			sb.append(patient.getHisId());
			sb.append("\t");
			sb.append(patient.getGender());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
}
