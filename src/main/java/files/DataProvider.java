package files;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataProvider {

    public static Object[][] combineTwoObjects(Object[][] a1, Object[][] a2){
        List<Object[]> objectCodesList = new LinkedList();
        for(Object[] o : a1){
            for(Object[] o2 : a2){
                objectCodesList.add(concatAll(o, o2));
            }
        }
        return objectCodesList.toArray(new Object[0][0]);
    }

    public static Object[][] combineThreeObjects(Object[][] a1, Object[][] a2, Object[][] a3){
        List<Object[]> objectCodesList = new LinkedList();
        for(Object[] o : a1){
            for(Object[] o2 : a2){
                for(Object[] o3 : a3) {
                    objectCodesList.add(concatAll(o, o2, o3));
                }
            }
        }
        return objectCodesList.toArray(new Object[0][0]);
    }

    public static Object[][] combineFourObjects(Object[][] a1, Object[][] a2, Object[][] a3, Object[][] a4){
        List<Object[]> objectCodesList = new LinkedList();
        for(Object[] o : a1){
            for(Object[] o2 : a2){
                for(Object[] o3 : a3) {
                    for(Object[] o4 : a4) {
                        objectCodesList.add(concatAll(o, o2, o3, o4));
                    }
                }
            }
        }
        return objectCodesList.toArray(new Object[0][0]);
    }


    @SafeVarargs
    private static <T> T[] concatAll(T[] first, T[]... rest) {

        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }

        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
}
