package com.mca.landmarketproject.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class LandMarketRespones extends ResponseEntity <Object>{

	public LandMarketRespones(Object body,HttpStatusCode status)
	{
		super(body,status);
		
	}
	

}
