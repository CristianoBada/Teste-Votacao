package com.elecao.teste.java.teste.java.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvercoesDeDatas {

	public static String convertDateUStoDataBR(String dataUS) {
		try {
			DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
			Date date = formatUS.parse(dataUS);
			DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
			String dateBR = formatBR.format(date);
			return dateBR;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataUS;
	}

	public static String convertDateBRtoDataUS(String dataBR) {
		try {
			DateFormat formatUS = new SimpleDateFormat("dd/mm/yyyy");
			Date date = formatUS.parse(dataBR);
			DateFormat formatBR = new SimpleDateFormat("yyyy-mm-dd");
			String dateUS = formatBR.format(date);
			return dateUS;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataBR;
	}
	
	public static String dataAtualUS() {
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
		String dateUS = formatarDate.format(data);
		return dateUS;
	}

	public static boolean comparaDatas(String data1, String data2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date3 = sdf.parse(data1);
			Date date4 = sdf.parse(data2);

			if (date3.compareTo(date4) >= 0) {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}