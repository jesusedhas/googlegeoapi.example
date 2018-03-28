package chuchin.com.googleplacesapiexample.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by supermacbook on 3/23/18.
 */

public class RequestPackage {


    //region Propiedades
    private String uri;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();
    private byte[] imageByteArray;
    //endregion


    //region Custom methods
    public void setParam(String key, String value) {
        params.put(key, value);
    }

    public String getEncodeParams() throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key: params.keySet()) {
            String value = null;
            value = URLEncoder.encode(params.get(key), "UTF-8");

            if (stringBuilder.length() > 0)
                stringBuilder.append("&");

            stringBuilder.append(key + "=" + value);
        }
        return stringBuilder.toString();
    }
    //endregion

    //region Encapsulation

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getRawParams() {
        return params;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }

    //endregion

}
