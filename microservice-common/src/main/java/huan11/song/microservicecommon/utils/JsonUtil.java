package huan11.song.microservicecommon.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    public static String parseJsonStr(Object object) {
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.serializeNulls();
        Gson gson = gsonbuilder.create();
        String jsonStr = gson.toJson(object);
        return jsonStr;
    }

    public static <T> T parseObjectFromJson(String jsonStr, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

}
