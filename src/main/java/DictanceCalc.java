import com.google.gson.JsonElement;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Distance Between Two Cities – Calculates the distance between two cities
 * and allows the user to specify a unit of distance.
 *
 *
 * Created by raphael on 1/1/15.
 */

public class DictanceCalc {

    private final String LAT_LONG_WEBSITE = "http://www.latlong.net/";
    private final String API_KEY = "AIzaSyCA38wF1sLypi4OUPZw-w9nVaUdbArDR6Q";

    private final JsonElement coord1 = null;
    private final JsonElement coord2 = null;

    private final String searchVal1 = null;
    private final String searchVal2 = null;

    public static void main(String[] args){
        DictanceCalc dc = new DictanceCalc();

        String firstCity = null, secondCity = null;

        System.out.println("Please Enter First City:");
        firstCity = dc.enterCityNames();

        System.out.println("Please enter Second city:");
        secondCity = dc.enterCityNames();

        dc.latLong(firstCity);
        dc.latLong(secondCity);



        System.out.println(firstCity);
        System.out.println(secondCity);
    }

    private void latLong(String cityA) {
        ConvertAddress conv = new ConvertAddress();

        JsonElement latlong = null;
        try {
            latlong = conv.GoogleJSON(cityA);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(latlong);

    }


    private String enterCityNames() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
