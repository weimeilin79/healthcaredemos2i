package com.mycompany.camel.spring;

public class FHIRConoverter {
	private StringBuffer sb = new StringBuffer();
	public String convertFHIRformat(String name, 
									String familyname,
									String hisId,
									String testcontent){
		
		String fhircontent = sb.toString();
		
		fhircontent = fhircontent.replaceFirst("REPLACE_FRISTNAME", name);
		fhircontent = fhircontent.replaceFirst("REPLACE_FAMILYNAME", familyname);
		fhircontent = fhircontent.replaceFirst("REPLACE_HISID", hisId);
		fhircontent = fhircontent.replaceFirst("REPLACE_TESTDETAIL", testcontent);
		
		fhircontent = fhircontent.replaceFirst("REPLACE_RANDOMNUMBER", FHIRUtil.genRandomNumber());
		fhircontent = fhircontent.replaceFirst("REPLACE_REPORTID", FHIRUtil.genRandomNumber());
		fhircontent = fhircontent.replaceFirst("REPLACE_TESTCODE", FHIRUtil.genTestCode());
		
		return fhircontent;
	}
	
	
	
	public FHIRConoverter(){
		super();
		
		sb.append("<Bundle xmlns=\"http://hl7.org/fhir\">");
		sb.append("				  <id value=\"REPLACE_RANDOMNUMBER\"/>");
		sb.append("				  <type value=\"message\"/>");
		sb.append("				  <entry>				");
		sb.append("				    <resource>");
		sb.append("				      <MessageHeader>");
		sb.append("				        <id value=\"CNTRL-3456\"/> ");
		sb.append("				        <meta>");
		sb.append("		          <tag>");
		sb.append("				            <system value=\"urn:oid:2.16.840.1.113883.5.100\"/>");
		sb.append("				            <code value=\"P\"/>");
		sb.append("				            <display value=\"Production\"/>");
		sb.append("		          </tag>");
		sb.append("		        </meta>");
		sb.append("		        <identifier value=\"CNTRL-3456\"/>");
		sb.append("		        <timestamp value=\"2002-02-15T09:30:00-04:00\"/>");
		sb.append("		        <event>");
		sb.append("		          <system value=\"http://hl7.org/fhir/message-type\"/>");
		sb.append("		          <code value=\"lab-test\"/>");
		sb.append("		        </event>");
		sb.append("		        <source>");
		sb.append("		          <name value=\"GHH LAB\"/>");
		sb.append("		          <endpoint value=\"urn:GHH-LAB\"/>");
		sb.append("		        </source>");
		sb.append("		        <destination>");
		sb.append("		          <name value=\"GHH OE\"/>");
		sb.append("		          <endpoint value=\"urn:GHH-OE\"/>");
		sb.append("		        </destination>");
		sb.append("		        <data> ");
		sb.append("		          <reference value=\"DiagnosticReport/REPLACE_REPORTID\"/> ");
		sb.append("		        </data>");
		sb.append("		      </MessageHeader>");
		sb.append("		    </resource>");
		sb.append("		  </entry>");
		
		sb.append("  <entry>");
		sb.append("    <resource>");
		sb.append("      <DiagnosticOrder>");
		sb.append("        <id value=\"845439\"/>");
		sb.append("        <subject>");
		sb.append("          <reference value=\"Patient/REPLACE_HISID\"/>");
		sb.append("          <display value=\"REPLACE_FRISTNAME REPLACE_FAMILYNAME\"/>");
		sb.append("        </subject>");
		sb.append("        <orderer>");
		sb.append("          <reference value=\"Practitioner/555-55-5555\"/>");
		sb.append("          <display value=\"The DEMO P.A.\"/>");
		sb.append("        </orderer>");
		sb.append("        <identifier>");
		sb.append("          <system value=\"http://ghh.org/oe/placerorder\"/>");
		sb.append("          <value value=\"845439\"/>");
		sb.append("        </identifier>");
		sb.append("        <status value=\"requested\"/>");
		sb.append("        <event>");
		sb.append("          <status value=\"requested\"/>");
		sb.append("        </event>");
		sb.append("        <item>");
		sb.append("          <code>");
		sb.append("            <coding>");
		sb.append("              <system value=\"http://www.redhat.com\"/>");
		sb.append("              <code value=\"REPLACE_TESTCODE\"/>");
		sb.append("              <display value=\"REPLACE_TESTDETAIL\"/>");
		sb.append("            </coding>");
		sb.append("          </code>");
		sb.append("          <status value=\"requested\"/>");
		sb.append("        </item>");
		sb.append("      </DiagnosticOrder>");
		sb.append("    </resource>");
		sb.append("  </entry>  ");
		sb.append("</Bundle>");
	}

}
