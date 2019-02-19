package edu.miracostacollege.cs134.sandiegomusicevents.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads MusicEvent data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (MusicEvent) with data.
 */

public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<MusicEvent> loadJSONFromAsset(Context context) throws IOException {
        List<MusicEvent> allMusicEvents = new ArrayList<>();
        String json;
            //TODO: Complete the file name
            InputStream is = context.getAssets().open("MusicEvents");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            //TODO: Complete the name of the root object in the JSON file
            JSONArray allMusicEventsJSON = jsonRootObject.getJSONArray("MusicEvents");
            int numberOfEvents = allMusicEventsJSON.length();

            for (int i = 0; i < numberOfEvents; i++) {
                JSONObject musicEventJSON = allMusicEventsJSON.getJSONObject(i);

                MusicEvent event = new MusicEvent();

                //extract artist from JSON Object
                event.setArtist(musicEventJSON.getString("Artist"));
                event.setCity(musicEventJSON.getString("City"));
                event.setDate(musicEventJSON.getString("Date"));
                event.setDay(musicEventJSON.getString("Day"));
                event.setImageName(musicEventJSON.getString("ImageName"));
                event.setState(musicEventJSON.getString("State"));
                event.setTime(musicEventJSON.getString("Time"));
                event.setVenue(musicEventJSON.getString("Venue"));

                //TODO: Complete the information about the event by parsing the JSON file

            allMusicEvents.add(event);
        }
        }
        catch (JSONException e)
        {
            Log.e("SD Music Events", e.getMessage());
        }

        return allMusicEvents;
    }
}
