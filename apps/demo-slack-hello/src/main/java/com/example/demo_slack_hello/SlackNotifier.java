package com.example.demo_slack_hello;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackNotifier {

    //環境変数からwebhookURLを呼び出す
    private static final String WEBHOOK_URL = System.getenv("SLACK_WEBHOOK_URL");

    public static void send(String message) {
        try {
            if (WEBHOOK_URL == null || WEBHOOK_URL.isEmpty()) {
                throw new IllegalStateException("SLACK_WEBHOOK_URL is not set");
            }

            String payload = String.format("{\"text\":\"%s\"}", message);

            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes("UTF-8"));
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Slack response: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
