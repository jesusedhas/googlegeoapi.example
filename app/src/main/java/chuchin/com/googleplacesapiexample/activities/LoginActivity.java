package chuchin.com.googleplacesapiexample.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import chuchin.com.googleplacesapiexample.R;
import chuchin.com.googleplacesapiexample.helper.NetworkManager;
import chuchin.com.googleplacesapiexample.helper.PrivateAPI;
import chuchin.com.googleplacesapiexample.model.RequestPackage;

public class LoginActivity extends AppCompatActivity {

    //region Propiedades
    private final String TAG = "login_activity";


    //endregion


    //region AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    //endregion

    //region Custom methods

    void setup() {

        //Create request package por Geocoding API

        String uri = PrivateAPI.endpointGeocodingAPI;

        String direccion = "\"- ALFONO  MARTINES DOMINGUEZ 105, - ZONA CENTRO, 65650, ABASOLO, NUEVO LEON\"";
        String apiKey = "AIzaSyAqeH5RBT-hy0pDD5jnI-J6zfE7AoNGp60";

        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("GET");
        requestPackage.setUri(uri);
        requestPackage.setParam(PrivateAPI.RequestParameters.addressKey, direccion);
        requestPackage.setParam(PrivateAPI.RequestParameters.apiKeyKey, apiKey);

        //execute request in AsyncTask
        GeocodingTask geocodingTask = new GeocodingTask();
        geocodingTask.execute(requestPackage);

    }

    //endregion


    //region GeocodingTask

    private class GeocodingTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected String doInBackground(RequestPackage... requestPackages) {

            String result;

            try {
                result = NetworkManager.simpleRequest(requestPackages[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {

                Log.d(TAG, "onPostExecute: " + result);

            } else {
                Toast.makeText(LoginActivity.this, "Hubo un error, verifica tu conexión", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //endregion



}
































