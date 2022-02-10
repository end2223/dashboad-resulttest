package local.caongocson.project.utils;


import com.google.gson.Gson;
import local.caongocson.project.model._SummaryResult;
import org.json.JSONObject;

public class JsonUtil {
    public static JSONObject convertStringToJsonObject(String data){
        return new JSONObject(data);
    }

    public static _SummaryResult convertDataToSummaryResult(String data){
        Gson gson = new Gson();
        return gson.fromJson(data, _SummaryResult.class);
    }
}
