package mineserver;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Mine {

    public String getServerStatus(String serverAddress){

            String apiUrl = "https://api.mcsrvstat.us/2/" + serverAddress;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return parseServerStatus(response.toString());
                    //return response.toString(); //Prints the whole JSON.
                } else {
                    return "GET request failed. Response Code: " + responseCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception occurred: " + e.getMessage();
            }
    }
    private String parseServerStatus(String jsonResponse) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject jsonObject = jsonReader.readObject();

            boolean online = jsonObject.getBoolean("online", false);
            String motd = jsonObject.getJsonObject("motd").getString("clean", "N/A");
            int playersOnline = jsonObject.getJsonObject("players").getInt("online", 0);
            int maxPlayers = jsonObject.getJsonObject("players").getInt("max", 0);
            String version = jsonObject.getString("version", "N/A");

            return String.format("Online: %b\nMOTD: %s\nPlayers: %d/%d\nVersion: %s",
                    online, motd, playersOnline, maxPlayers, version);
        }
    }
}
