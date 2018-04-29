package mfec.util;

import java.util.Date;

import com.google.gson.Gson;

public class StringUtil {
	
	public static boolean isEmpty(String rcvData) {
		if (rcvData == null) {
		    return true;		
		    
		} else if (rcvData.trim().length() == 0) {
		    return true;
		    
		} else {
		    return false;		
		}
	}
	
	public static long convertToSecondTimeDiff(Date rcvDateTimeBegin, Date rcvDateTimeEnd) throws Exception {

		long timeDiff = 0;
		
		try {
			
			if(rcvDateTimeBegin == null || rcvDateTimeEnd == null) {
				throw new Exception("Paramiter incomplete");
			}

			// convert msec to sec
			timeDiff = (rcvDateTimeEnd.getTime() - rcvDateTimeBegin.getTime()) / 1000;

		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		return timeDiff;
	}
	
	public static String totalCallTime(long rcvTimeSecondDiff) throws Exception {

		StringBuilder totalCallTime = null;
		
		try {
		
			int hourDiff = (int) (rcvTimeSecondDiff / (60 * 60) % 24);
			int minuteDiff = (int) (rcvTimeSecondDiff / 60 % 60);
			int secondDiff = (int) (rcvTimeSecondDiff % 60);
			totalCallTime = new StringBuilder();
			totalCallTime.append(String.format("%02d", hourDiff));
			totalCallTime.append(":");
			totalCallTime.append(String.format("%02d", minuteDiff));
			totalCallTime.append(":");
			totalCallTime.append(String.format("%02d", secondDiff));
			
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		return totalCallTime.toString();

	}
	
	public static String parseObjToString(Object rcvObj){
		Gson gson = null;
		String jsonData = null;
		if(rcvObj != null){
			gson = new Gson();
			jsonData = gson.toJson(rcvObj);				
		}					
		return jsonData;
	}

}
