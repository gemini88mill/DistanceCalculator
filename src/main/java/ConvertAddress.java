import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by raphael on 1/1/15.
 * reference: http://www.mindfiresolutions.com/Google-Geocoding-API-V3-implementation-in-Java-1766.php
 */
public class ConvertAddress {

    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    /**
     * GoogleJson - Uses google's Json classes to create a JsonObject from Google Maps
     * the method (using String fullAddress) takes a String and places it into the
     * URI.
     *
     * The method then Opens a connection with the website (String URL) and receives a String
     * Formatted to Json.
     *
     * method closes connection with URL
     *
     * Gson (Google's Json Library) then parses the string into a Json format. The method then
     * returns a JsonElement of the Lat and long coords of the Address given.
     *
     *
     * @param fullAddress String
     * @return JsonElement
     * @throws IOException
     */
    public JsonElement GoogleJSON(String fullAddress) throws IOException {

        //connection to URL
        URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.connect();

        //gathering of data
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        IOUtils.copy(conn.getInputStream(), output);

        //closing the stream
        output.close();

        //formatting to Json
        String data = output.toString();
        JsonObject jObj = (JsonObject)new JsonParser().parse(data);

        return jObj.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("bounds").getAsJsonObject().get("northeast");
    }

}
