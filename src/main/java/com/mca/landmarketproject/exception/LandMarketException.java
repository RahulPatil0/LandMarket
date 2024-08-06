//package com.mca.landmarketproject.exception;
//
//public class LandMarketException extends Exception {
//
//	private static final long serialVersionUID = -3971807160644936404L;
//	
//	
//	public LandMarketException(String message) {
//		super(message);
//	}
//
//}
package com.mca.landmarketproject.exception;

public class LandMarketException extends Exception {
    public LandMarketException(String message) {
        super(message);
    }

    public LandMarketException(String message, Throwable cause) {
        super(message, cause);
    }
}
