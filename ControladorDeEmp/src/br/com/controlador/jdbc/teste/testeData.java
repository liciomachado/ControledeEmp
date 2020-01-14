package br.com.controlador.jdbc.teste;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class testeData {

	public static void main(String[] args) {
		Date d = new Date();

		Calendar cal = new GregorianCalendar();

		cal.setTime(d);
		System.out.println(cal.getTime());

	}

}
