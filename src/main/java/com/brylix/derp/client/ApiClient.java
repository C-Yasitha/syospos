package com.brylix.derp.client;

import com.brylix.derp.ConfigLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class ApiClient {

    private String apiUrl;

    public ApiClient() {
        loadConfig();
    }

    public String callAPI(String endpoint, String jsonInputString, String method) throws Exception {
        URL url = new URL(apiUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP method (GET, POST, PUT, DELETE)
        connection.setRequestMethod(method);

        // If the method is POST or PUT, send the JSON input string in the request body
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        return response.toString();
    }

    private void loadConfig() {
        Properties properties = ConfigLoader.loadConfig();
        apiUrl = properties.getProperty("api.url");
    }
}
