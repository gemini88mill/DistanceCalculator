import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.Scanner;

/**
 * Distance Between Two Cities – Calculates the distance between two cities
 * and allows the user to specify a unit of distance.
 *
 *
 * Created by raphael on 1/1/15.
 */
public class DictanceCalc {

    private JsonElement coord1 = null;
    private JsonElement coord2 = null;

    private String searchVal1 = null;
    private String searchVal2 = null;

    public static void main(String[] args){
        DictanceCalc dc = new DictanceCalc();

        String firstCity = null, secondCity = null;

        System.out.println("Please Enter First City:");
        firstCity = dc.enterCityNames();
        dc.setSearchVal1(firstCity);

        System.out.println("Please enter Second city:");
        secondCity = dc.enterCityNames();
        dc.setSearchVal2(secondCity);

        dc.setCoord1(dc.latLong(firstCity));
        dc.setCoord2(dc.latLong(secondCity));



        //---------------------output to shell----------------------------
        System.out.println(dc.getSearchVal1() + ": " + dc.getCoord1());
        System.out.println(dc.getSearchVal2() + ": " + dc.getCoord2());
    }

    private JsonElement latLong(String cityA) {
        ConvertAddress conv = new ConvertAddress();
        JsonElement latlong = null;
        try {
            latlong = conv.GoogleJSON(cityA);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return latlong;
    }


    private String enterCityNames() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }


    //-----------Getters and setters -------------------
    public JsonElement getCoord1() {
        return coord1;
    }

    public void setCoord1(JsonElement coord1) {
        this.coord1 = coord1;
    }

    public JsonElement getCoord2() {
        return coord2;
    }

    public void setCoord2(JsonElement coord2) {
        this.coord2 = coord2;
    }

    public String getSearchVal1() {
        return searchVal1;
    }

    public void setSearchVal1(String searchVal1) {
        this.searchVal1 = searchVal1;
    }

    public String getSearchVal2() {
        return searchVal2;
    }

    public void setSearchVal2(String searchVal2) {
        this.searchVal2 = searchVal2;
    }
}
