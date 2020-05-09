package projectmanager.utils;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import projectmanager.domain.IExampleData;


public class ListViewUtils {
    /**
     * Development purposes only!
     * Populate given with automatically created example data.
     * @param element An element which receives example data.
     * @param exampleInstance A example instance.
     * @param callback Callback for creating a new item
     */
    public static void populateListWithExampleData(ListView element, Class<?> exampleInstance, final IPopulateListCallback callback) {
        populateListWithExampleData(element, exampleInstance, 10, callback);
    }
    
    /**
     * Development purposes only!
     * Populate given with automatically created example data.
     * @param element An element which receives example data.
     * @param exampleInstance A example instance.
     * @param count An amount of generated items.
     * @param callback Callback for creating a new item
     */
    public static void populateListWithExampleData(ListView element, Class<?> exampleInstance, int count, final IPopulateListCallback callback) {
        if (!IExampleData.class.isAssignableFrom(exampleInstance)) {
            return;
        }
        
        List items = new ArrayList(count);
        
        for (int i = 0; i < count; i++) {
            IExampleData instance = callback.generateItem(i);
            items.add(instance);
        }
        
        element.setItems(FXCollections.observableList(items));
    }
}
