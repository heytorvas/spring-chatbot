package repository;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestarHTTP {
	public static void main(String[] args) {
		try {
			String url = "https://api.chatbot.com/query";

			HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

			// add reuqest header
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("content-type", "application/json");
			httpClient.setRequestProperty("authorization", "Bearer ZBmX0dsEU_mJ_FTUkXI4LEszEcKH3_tu");
			httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "{\"query\": \"boa noite\",\"sessionId\": \"1034021451\"}";

			// Send post request
			httpClient.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
				wr.writeBytes(urlParameters);
				wr.flush();
			}

			int responseCode = httpClient.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

				String line;
				StringBuilder response = new StringBuilder();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}

				// print result
				System.out.println(response.toString()); 
				
				String[] retorno_array = response.toString().split("\"message\":\"");
				retorno_array = retorno_array[1].split("\"}]},");
				String resposta = retorno_array[0];
				System.out.println(resposta);
						
				

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
