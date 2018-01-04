package Reflection;

import Annotation.Inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldReflector {

    public static List<Field> collectUpTo(Class<?> clazz, Class<?> upperBound){
        if(clazz == upperBound) throw new IllegalArgumentException();
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static ArrayList<Field> filterList(List<Field> fields){
        ArrayList<Field> list = new ArrayList<>();
        for(Field field : fields){
            if(field.getAnnotation(Inject.class) != null){
                list.add(field);
            }
        }
        return list;
    }
}
