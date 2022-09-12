package es.orc.socialmedia;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {

	public static String init() {
		String buffer = "";
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(stream);
		try {
			buffer = reader.readLine();
		} catch (Exception e) {
			System.out.append("Dato no válido.");
		}
		return buffer;
	}

	public static int integer() {
		return Input.integer(null);
	}

	public static int integer(String message) {
		if (message != null) {
			System.out.print(message);
		}
		int value = Integer.parseInt(Input.init());
		return value;
	}

	public static double real() {
		return Input.real(null);
	}

	public static double real(String message) {
		if (message != null) {
			System.out.print(message);
		}
		double value = Double.parseDouble(Input.init());
		return value;
	}

	public static String string() {
		return Input.string(null);
	}

	public static String string(String message) {
		if (message != null) {
			System.out.print(message);
		}
		String value = Input.init();
		return value;
	}

	public static char character() {
		return Input.character(null);
	}

	public static char character(String message) {
		if (message != null) {
			System.out.print(message);
		}
		String valor = Input.init();
		return valor.charAt(0);
	}
}