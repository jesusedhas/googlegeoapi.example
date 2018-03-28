package chuchin.com.googleplacesapiexample.helper;

import chuchin.com.googleplacesapiexample.model.geocodingAPI.GeocodingModel;

/**
 * Created by supermacbook on 3/23/18.
 */

public class PrivateAPI {

    public final static String endpointGeocodingAPI = "https://maps.googleapis.com/maps/api/geocode/json";


    public static class RequestParameters {

        public final static String imageKey = "image";
        public final static String addressKey = "address";
        public final static String apiKeyKey = "key";

    }

    public static class GeocodingModel {

        public final static String resultsKey = "results";
        public final static String statusKey = "status";
        public final static String addressComponentsKey = "address_components";
        public final static String formattedAddressKey = "formatted_address";
        public final static String geometryKey = "geometry";
        public final static String partialMatchKey = "partial_match";
        public final static String placeIdKey = "place_id";
        public final static String typesKey = "types";
        public final static String longNameKey = "long_name";
        public final static String shortNameKey = "short_name";
        public final static String locationKey = "location";
        public final static String latKey = "lat";
        public final static String lngKey = "lng";
        public final static String locationTypeKey = "location_type";
        public final static String viewportKey = "viewport";
        public final static String northeastKey = "northeast";
        public final static String southwestKey = "southwest";


    }



}
