package com.mcmod.ai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public class GoogleAIClient {
    public static String sendMessage(String apiKey, String model, String message) {
        try {
            URI uri = URI.create("https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey);
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = "{\"contents\":[{\"parts\":[{\"text\":\"" + message.replace("\"", "\\\"") + "\"}]}]}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            BufferedReader br;
            if (code >= 200 && code < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            br.close();
            // Eğer cevap boşsa veya hata varsa, hata mesajı döndür
            if (response.length() == 0) {
                return "Hata: Sunucudan cevap alınamadı.";
            }
            return response.toString();
        } catch (Exception e) {
            return "Hata: " + e.getMessage();
        }
    }
}
