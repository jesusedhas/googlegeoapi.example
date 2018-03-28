package chuchin.com.googleplacesapiexample.model.geocodingAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import chuchin.com.googleplacesapiexample.helper.PrivateAPI;

/**
 * Created by supermacbook on 3/28/18.
 */

public class ResultModel {


    //region Propiedades
    private ArrayList<AddressComponentModel> addressComponents;
    private String formattedAddress;
    private GeometryModel geometry;
    private boolean partialMatch;
    private String placeId;
    private ArrayList<String> types;

    //endregion

    //region Constructor

    public ResultModel() {


    }

    //endregion

    //region Parsing class methods

    public static ResultModel parseIntoModel(JSONObject jsonObject) throws JSONException {

        ResultModel parsedModel = new ResultModel();

        parsedModel.setAddressComponents(AddressComponentModel.parseIntoArrayModel(jsonObject.getJSONArray(PrivateAPI.GeocodingModel.addressComponentsKey)));
        parsedModel.setFormattedAddress(jsonObject.getString(PrivateAPI.GeocodingModel.formattedAddressKey));
        parsedModel.setGeometry(GeometryModel.parseIntoModel(jsonObject.getJSONObject(PrivateAPI.GeocodingModel.geometryKey)));
        parsedModel.setPartialMatch(jsonObject.getBoolean(PrivateAPI.GeocodingModel.partialMatchKey));
        parsedModel.setPlaceId(jsonObject.getString(PrivateAPI.GeocodingModel.placeIdKey));

        ArrayList<String> types = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray(PrivateAPI.GeocodingModel.typesKey);

        for (int i = 0; i < jsonArray.length(); i++) {
            types.add(jsonArray.getString(i));
        }

        parsedModel.setTypes(types);

        return parsedModel;

    }


    public static ArrayList<ResultModel> parseIntoArrayModel(JSONArray jsonArray) throws JSONException {

        ArrayList<ResultModel> parsedArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            ResultModel parsedModel = parseIntoModel(jsonObject);

            parsedArray.add(parsedModel);

        }
        return parsedArray;
    }

    //endregion


    //region Encapsulation

    public ArrayList<AddressComponentModel> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(ArrayList<AddressComponentModel> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeometryModel getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryModel geometry) {
        this.geometry = geometry;
    }

    public boolean isPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(boolean partialMatch) {
        this.partialMatch = partialMatch;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    //endregion




}
