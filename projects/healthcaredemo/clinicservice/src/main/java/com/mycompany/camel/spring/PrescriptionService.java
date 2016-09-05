package com.mycompany.camel.spring;

import java.util.HashMap;

public class PrescriptionService {

	public HashMap<String, String> allPrescriptions = new HashMap<String, String>();
	
	
	public void addPrescription(String hisId, String interval,String prescription){
		allPrescriptions.put(hisId, interval+"\t"+prescription);
	}
	
	public String getPrescription(String hisId){
		
		if(allPrescriptions.get(hisId) == null){
			return "";
		}
		
		return hisId+"\t"+allPrescriptions.get(hisId);
	}
}
