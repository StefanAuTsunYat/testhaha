package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestParamUtil {
    private static ConcurrentHashMap<String, String> paramMap = new ConcurrentHashMap<>();

    public static synchronized void mapPut(String key, String value){
        paramMap.put(key,value);
    }
    public static synchronized String mapGet(String key){
        return paramMap.get(key);
    }
//    public static synchronized void initializeMap(){
//
//    }

}
