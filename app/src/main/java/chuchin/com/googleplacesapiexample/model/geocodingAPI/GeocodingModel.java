package chuchin.com.googleplacesapiexample.model.geocodingAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import chuchin.com.googleplacesapiexample.helper.PrivateAPI;

/**
 * Created by supermacbook on 3/28/18.
 */

public class GeocodingModel {

    //region Propiedades
    private ArrayList<ResultModel> results;
    private String status;

    //endregion

    //region Constructor

    public GeocodingModel() {

    }

    //endregion

    //region Parsing class methods

    public static GeocodingModel parseIntoModel(JSONObject jsonObject) throws JSONException {

        GeocodingModel parsedModel = new GeocodingModel();

        parsedModel.setResults(ResultModel.parseIntoArrayModel(jsonObject.getJSONArray(PrivateAPI.GeocodingModel.resultsKey)));
        parsedModel.setStatus(jsonObject.getString(PrivateAPI.GeocodingModel.statusKey));

        return parsedModel;

    }

    //endregion


    //region Encapsulation

    public ArrayList<ResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultModel> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //endregion

}
