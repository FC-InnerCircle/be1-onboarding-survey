package lshh.be1onboardingsurvey.common.lib.diff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapConverter {
    public static <T> Map<String, T> toMap(T obj){
        var fields = obj.getClass().getDeclaredFields();
        Map<String, T> map = new HashMap<>();
        Arrays.stream(fields).forEach(field -> {
            try{
                field.setAccessible(true);
                T value = (T) field.get(obj);
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return map;
    }
}
