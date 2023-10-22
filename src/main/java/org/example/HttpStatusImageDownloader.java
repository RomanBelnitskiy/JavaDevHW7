package org.example;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpStatusImageDownloader {
    private final OkHttpClient client;
    private final HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        client = new OkHttpClient();
        checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) {
        String url = checker.getStatusImage(code);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);

            byte[] bytes = response.body().source().readByteArray();
            OutputStream os = new FileOutputStream(String.format("%s.jpg", code));
            os.write(bytes);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
