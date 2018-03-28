package chuchin.com.googleplacesapiexample.model.geocodingAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import chuchin.com.googleplacesapiexample.helper.PrivateAPI;

/**
 * Created by supermacbook on 3/28/18.
 */

public class AddressComponentModel {

    //region Propiedades
    private String longName;
    private String shortName;
    private ArrayList<String> types;

    //endregion

    //region Constructor

    public AddressComponentModel() {

    }

    //endregion

    //region Class parsing methods

    public static AddressComponentModel parseIntoModel(JSONObject jsonObject) throws JSONException {

        AddressComponentModel parsedModel = new AddressComponentModel();

        parsedModel.setLongName(jsonObject.getString(PrivateAPI.GeocodingModel.longNameKey));
        parsedModel.setShortName(jsonObject.getString(PrivateAPI.GeocodingModel.shortNameKey));

        ArrayList<String> types = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray(PrivateAPI.GeocodingModel.typesKey);

        for (int i = 0; i < jsonArray.length(); i++) {
            types.add(jsonArray.getString(i));
        }

        parsedModel.setTypes(types);

        return parsedModel;
    }


    public static ArrayList<AddressComponentModel> parseIntoArrayModel(JSONArray jsonArray) throws JSONException {

        ArrayList<AddressComponentModel> parsedArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            AddressComponentModel parsedModel = parseIntoModel(jsonObject);
            parsedArray.add(parsedModel);

        }
        return parsedArray;

    }

    //endregion


    //region Encapsulation

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    //endregion

}
