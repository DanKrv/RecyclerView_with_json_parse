package yem.com.jsonfromwebservicetutorial_4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DanKravitz on 11/01/2017.
 */
public class JSONParser {
    String listTitle = "";

    public List<YoutubeData> parse(JSONArray jsonArray) {

        List<YoutubeData> parsedYoutubeData = new ArrayList<>();
        YoutubeData youtubeData;

        JSONObject jsonObjects;
        JSONArray jsonArrays;

        try {
            String title = "", link = "", thumb = "";

            jsonObjects = jsonArray.getJSONObject(0);

            listTitle = jsonObjects.getString("ListTitle");


            jsonArrays = jsonObjects.getJSONArray("ListItems");

            for (int i = 0; i < jsonArrays.length(); i++){

                youtubeData = new YoutubeData();

                title = jsonArrays.getJSONObject(i).getString("Title");
                link = jsonArrays.getJSONObject(i).getString("link");
                thumb = jsonArrays.getJSONObject(i).getString("thumb");

                youtubeData.setTitle(title);
                youtubeData.setThumb(thumb);
                youtubeData.setLink(link);
                parsedYoutubeData.add(youtubeData);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return parsedYoutubeData;
    }
}


