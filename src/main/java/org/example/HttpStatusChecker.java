package org.example;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HttpStatusChecker {
    private final OkHttpClient client;

    public HttpStatusChecker() {
        client = new OkHttpClient();
    }

    public String getStatusImage(int code) {
        String url = String.format("https://http.cat/%s.jpg", code);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format("Image for status code \'%s\' not found", code));
            }

            return url;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
