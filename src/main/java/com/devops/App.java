package com.devops;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.printCpuLoad(); // code smell: uses system-dependent command
        app.checkServiceHealth("http://localhost:8080/health"); // bug: missing try/catch
        app.saveCredentials("admin", "devops123"); // security hotspot: hardcoded password
    }

    public void printCpuLoad() {
        try {
            Process process = Runtime.getRuntime().exec("uptime"); // Linux-specific
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println("CPU Load Info: " + line);
            }
        } catch (Exception e) {
            System.out.println("Error reading CPU load");
        }
    }

    public void checkServiceHealth(String urlStr) {
        // Bug: no exception handling
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                System.out.println("Service is healthy");
            } else {
                System.out.println("Service returned status: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Service check failed: " + e.getMessage());
        }
    }

    public void saveCredentials(String username, String password) {
        // Security Hotspot: hardcoded credential logic
        System.out.println("Saving credentials for " + username);
        System.out.println("Password is: " + password);
    }
}
