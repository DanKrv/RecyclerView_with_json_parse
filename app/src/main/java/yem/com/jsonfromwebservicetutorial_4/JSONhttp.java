package yem.com.jsonfromwebservicetutorial_4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by DanKravitz on 11/01/2017.
 */
public class JSONhttp {

    private static final String JSONurl = "http://www.razor-tech.co.il/hiring/youtube-api.json";
    private JSONObject jsonObject = null;
    private JSONArray jsonArray = null;
    private String json = "";

    public JSONArray getJSONfromUrl() {

        try {
            URL url = new URL(JSONurl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
            json = stringBuilder.toString();
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("Playlists");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
