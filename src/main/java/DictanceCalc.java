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

    private double lat1 = 0.0;
    private double lng1 = 0.0;
    private double lat2 = 0.0;
    private double lng2 = 0.0;

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

        dc.distance();

        //---------------------output to shell----------------------------
        System.out.println(dc.getSearchVal1() + ": " + dc.getCoord1());
        System.out.println(dc.getSearchVal2() + ": " + dc.getCoord2());
    }

    private void distance() {
        //uses Json elements from google to calculate distance.
        elementToDouble(getCoord1(), 1);
        elementToDouble(getCoord2(), 2);

        double radius = 6378.1; //approx radius of earth in miles
        double phi1 = Math.toRadians(getLat1());
        double phi2 = Math.toRadians(getLat2());
        double deltaPhi = Math.toRadians(getLat2() - getLat1());
        double deltaLambda = Math.toRadians(getLng2() - getLng1());

        double a = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 -a));

        double d = radius * c;

        System.out.println(d);
    }

    private void elementToDouble(JsonElement coord1, int marker) {
        double lat = coord1.getAsJsonObject().get("lat").getAsDouble();
        double lng = coord1.getAsJsonObject().get("lng").getAsDouble();
        System.out.println(lat + " " + lng);

        if(marker == 1){
            setLat1(lat);
            setLng1(lng);
        } else if (marker == 2){
            setLat2(lat);
            setLng2(lng);
        }
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

    public double getLat1() {
        return lat1;
    }

    public void setLat1(double lat1) {
        this.lat1 = lat1;
    }

    public double getLng1() {
        return lng1;
    }

    public void setLng1(double lng1) {
        this.lng1 = lng1;
    }

    public double getLat2() {
        return lat2;
    }

    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    public double getLng2() {
        return lng2;
    }

    public void setLng2(double lng2) {
        this.lng2 = lng2;
    }
}
