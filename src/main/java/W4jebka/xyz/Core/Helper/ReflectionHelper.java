package W4jebka.xyz.Core.Helper;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static Object getValue(Object instance, String fieldName) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}