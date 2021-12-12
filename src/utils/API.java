package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class provides method to send request to server and receive response
 * Date: 11/12/2000
 * @author Nguyen Ngoc Thai Chau - 20183869
 * @version 1.0
 *
 */
public class API {
	/**
	 * String date format
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * log data to console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());
	
	/**
	 * setup connection
	 * @param url path to server
	 * @param method api
	 * @param token authenticate
	 * @return HttpURlConnection
	 * @throws IOException
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException {
		// setup
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url); 
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection(); 
		conn.setDoInput(true); 
		conn.setDoOutput(true); 
		conn.setRequestMethod (method); 
		conn.setRequestProperty("Content-Type", "application/json"); 
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn; 
	}
	
	/**
	 * read response from server
	 * @param conn : connection to server
	 * @return response from server type String
	 * @throws IOException
	 */
	private static String readRespone (HttpURLConnection conn) throws IOException {
		// get data sent back from server
		BufferedReader in; 
		String inputLine; 
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			} 
		else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder(); 
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine); 
		response.append(inputLine + "\n"); 
		in.close(); 
		LOGGER.info("Response Info: " + response.substring(0, response. length() - 1).toString()); 
		return response.substring(0, response. length() - 1).toString();
	}
	
	
	
	/**
	 * GET method
	 * @param url path to server
	 * @param token to authenticate
	 * @return response from server type String
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		// setup
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		// get data from server
		String response = readRespone(conn);
		
		return response;
	}

	int var;
	/**
	 * POST method
	 * @param url path to server
	 * @param data send to server
	 * @return respose from server type String
	 * @throws IOException
	 */
	public static String post(String url, String data
			, String token
	) throws IOException {
		// setup
		allowMethods("PATCH");
		HttpURLConnection conn = setupConnection(url, "PATCH", token);
		
		
		// send data to server
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		
		// read data 
		String response = readRespone(conn);
		return response;
	}
	/**
	 * PATCH, PUT, ... API in Java 11
	 * @deprecated only Java 11
	 * @param methods is PATCH, PUT request
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
