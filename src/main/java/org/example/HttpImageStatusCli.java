package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {

    public static void askStatus() {
        while(true) {
            System.out.println("Enter HTTP status code or \'0\' for exit");
            String stringCode = new Scanner(System.in).nextLine();

            try {
                int code = Integer.parseInt(stringCode);
                if (code == 0) {
                    return;
                }

                HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
                downloader.downloadStatusImage(code);

                System.out.println("Image has been downloaded");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter valid number");
            } catch (RuntimeException ex) {
                System.out.println(String.format("There is not image for HTTP status %s", stringCode));
            }
        }
    }
}
