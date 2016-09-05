package com.mycompany.camel.spring;

import java.util.Random;

public class FHIRUtil {
	
	public static String genRandomNumber(){
		Random r = new Random(System.currentTimeMillis());
	    return 1000000000 + r.nextInt(2000000000)+"";
	}
	
	public static String genTestCode(){
		Random r = new Random(System.currentTimeMillis());
	    return 100 + r.nextInt(999)+"-"+10000 + r.nextInt(99999);
	}

}
