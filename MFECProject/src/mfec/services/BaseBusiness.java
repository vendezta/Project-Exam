package mfec.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mfec.beans.CustomerHistoryCallBeans;
import mfec.util.StringUtil;

public class BaseBusiness {

	public static String calculatePhonCharge(InputStream rcvFileContent) throws Exception {

		final float unitTHB = 100; // หน่วยสตางค์
		final int secondPerMinute = 60;
		
		float chargePerSecondOfFirstMinute;
		float chargePerSecondOfNextMinute;
		float serviceCharge;
		long timeSecondDiff;
		
		DecimalFormat numberFormat = null;
		Date start = null;
		Date end = null;
		DateFormat df = null;
		String line = null;
		BufferedReader in = null;
		
		ArrayList<CustomerHistoryCallBeans> cHistoryCallList = null;
		CustomerHistoryCallBeans cHistoryCallBeans = null;
		
		try {
		
			in = new BufferedReader(new InputStreamReader(rcvFileContent, "UTF-8"));
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			numberFormat = new DecimalFormat("##.##");
			
			cHistoryCallList = new ArrayList<CustomerHistoryCallBeans>();
			
			while((line = in.readLine()) != null)
			{			
				System.out.println("line : "+line);
				start = df.parse(line.split("\\|")[0] + " " + line.split("\\|")[1]);
				end = df.parse(line.split("\\|")[0] + " " + line.split("\\|")[2]);
				
				// convert msec to sec
				timeSecondDiff = StringUtil.convertToSecondTimeDiff(start, end);
				
				chargePerSecondOfFirstMinute = (unitTHB*3)/60;
				chargePerSecondOfNextMinute = (unitTHB*1)/60;
				
				if(timeSecondDiff <= secondPerMinute) {
					serviceCharge = chargePerSecondOfFirstMinute * timeSecondDiff;
					serviceCharge = serviceCharge/unitTHB;
				}else {
					serviceCharge = chargePerSecondOfFirstMinute * secondPerMinute;
					timeSecondDiff = timeSecondDiff-secondPerMinute;
					serviceCharge += chargePerSecondOfNextMinute * timeSecondDiff;
					serviceCharge = serviceCharge/unitTHB;				
				}
				
				cHistoryCallBeans = new CustomerHistoryCallBeans();
				cHistoryCallBeans.setChargeAmount(numberFormat.format(Float.parseFloat(String.valueOf(serviceCharge))/60));
				cHistoryCallBeans.setMobileNo(line.split("\\|")[3]);
				cHistoryCallBeans.setPromotionType(line.split("\\|")[4]);
				cHistoryCallBeans.setTotalCallTime(StringUtil.totalCallTime(timeSecondDiff));
				cHistoryCallList.add(cHistoryCallBeans);
			}
			
			in.close();

		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if(in != null) {
				in.close();
				in=null;
			}
		}
		
		return StringUtil.parseObjToString(cHistoryCallList);
	}
	
}
