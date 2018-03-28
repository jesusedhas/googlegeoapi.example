package chuchin.com.googleplacesapiexample.model.geocodingAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import chuchin.com.googleplacesapiexample.helper.PrivateAPI;

/**
 * Created by supermacbook on 3/28/18.
 */

public class GeometryModel {

    //region Propiedades
    private HashMap<String, Double> location;
    private String locationType;
    private HashMap<String, Object> viewport;

    //endregion

    //region Constructor

    public GeometryModel() {


    }

    //endregion

    //region Class parsing methods

    public static GeometryModel parseIntoModel(JSONObject jsonObject) throws JSONException {

        GeometryModel parsedModel = new GeometryModel();

        //parse location
        JSONObject jsonLocation = jsonObject.getJSONObject(PrivateAPI.GeocodingModel.locationKey);
        HashMap<String, Double> mapaLocation = new HashMap<>();
        mapaLocation.put(PrivateAPI.GeocodingModel.latKey, jsonLocation.getDouble(PrivateAPI.GeocodingModel.latKey));
        mapaLocation.put(PrivateAPI.GeocodingModel.lngKey, jsonLocation.getDouble(PrivateAPI.GeocodingModel.lngKey));
        parsedModel.setLocation(mapaLocation);

        //location type
        parsedModel.setLocationType(jsonObject.getString(PrivateAPI.GeocodingModel.locationTypeKey));

        parsedModel.setViewport(null);

        return parsedModel;

    }

    //endregion


    //region Encapsulation

    public HashMap<String, Double> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, Double> location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public HashMap<String, Object> getViewport() {
        return viewport;
    }

    public void setViewport(HashMap<String, Object> viewport) {
        this.viewport = viewport;
    }

    //endregion

}












