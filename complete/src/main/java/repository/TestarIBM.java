package repository;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestarIBM {
	public static void main(String[] args) {
		
		String sessionId = null;
		String autenticacaoEncoded = null;

		//###################################################### criar sessao
		try {
			autenticacaoEncoded = Base64.getEncoder().encodeToString(
					("apikey:6Cxlyu6vPmJPox8ffN-5b7SQVNVPwSw6t_mwZ9dhHoKn").getBytes(StandardCharsets.UTF_8));

			String url = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/ad119c4f-b237-4779-8a18-88a1a3814941/v2/assistants/23cb2a6a-b01d-4e66-9599-699570718b29/sessions?version=2020-04-01";

			HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

			// add reuqest header
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("content-type", "application/json");
			httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpClient.setRequestProperty("Authorization", "Basic " + autenticacaoEncoded);

			String urlParameters = "{\"input\": {\"text\": \"boa noite\"}}";

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

				sessionId = response.toString();
				sessionId = sessionId.replace("{\"session_id\":\"", "");
				sessionId = sessionId.replace("\"}", "");
				System.out.println(sessionId);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		//###################################################### enviar pro bot
		try {
			String url = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/ad119c4f-b237-4779-8a18-88a1a3814941/v2/assistants/23cb2a6a-b01d-4e66-9599-699570718b29/sessions/" + sessionId  + "/message?version=2020-04-01";

			HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

			// add reuqest header
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("content-type", "application/json");
			httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpClient.setRequestProperty("Authorization", "Basic " + autenticacaoEncoded);

			String urlParameters = "{\"input\": {\"text\": \"boa noite\"}}";

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
				
				String[] retornoArray = response.toString().split("\"text\":\"");
				retornoArray = retornoArray[1].split("\"}],");
				String respostaFinal = retornoArray[0];
				System.out.println(respostaFinal);



			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
}
