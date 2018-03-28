package chuchin.com.googleplacesapiexample.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import chuchin.com.googleplacesapiexample.model.RequestPackage;

public class NetworkManager {

    //region Propiedades
    static String lineEnd = "\r\n";
    static String twoHyphens = "--";
    static String boundary = "*****";

    //endregion

    //region Class methods

    public static String simpleRequest(RequestPackage requestPackage) throws IOException {
        BufferedReader bufferedReader = null;
        String uri = requestPackage.getUri();
        if (requestPackage.getMethod().equals("GET")) {
            uri += "?" + requestPackage.getEncodeParams();
        }
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestPackage.getMethod());

            if (requestPackage.getMethod().equals("POST")) {
                httpURLConnection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(requestPackage.getEncodeParams());
                outputStreamWriter.flush();
            }
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String registrarUsuario(RequestPackage requestPackage) throws Exception {
        BufferedReader bufferedReader = null;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dataOutputStream = null;
        String uri = requestPackage.getUri();
        try {
            URL url = new URL(uri);
             httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setRequestProperty("Connection", "close");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            writeRegistroParamData(dataOutputStream, requestPackage.getRawParams());
            writeFileParamData(dataOutputStream, requestPackage);

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MalformedURLException";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
                dataOutputStream.close();
                httpURLConnection.disconnect();

            }
        }
    }

    public static Bitmap descargarImagen (String imageUri){
        URL imageUrl;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return imagen;
    }

    //endregion


    //region Aux methods

    private static void writeRegistroParamData(DataOutputStream os, Map<String, String> params) throws Exception {
        Iterator<String> keys = params.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = params.get(key);
            os.writeBytes(twoHyphens + boundary + lineEnd);
            os.writeBytes("Content-Disposition: form-data; name=\""+ key +"\""+ lineEnd);
            os.writeBytes(lineEnd);
            os.writeBytes(value);
            os.writeBytes(lineEnd);
            os.writeBytes(twoHyphens + boundary + lineEnd);
        }
    }

    private static void writeFileParamData(DataOutputStream os, RequestPackage requestPackage) {
        try {
            os.writeBytes("Content-Disposition: form-data; name=\""+ PrivateAPI.RequestParameters.imageKey +"\";filename=\"photo.jpg\""+ lineEnd);
            os.writeBytes(lineEnd);
            os.write(requestPackage.getImageByteArray());
            os.writeBytes(lineEnd);
            os.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //endregion


}