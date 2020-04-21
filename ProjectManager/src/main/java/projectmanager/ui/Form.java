package projectmanager.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TextInputControl;

public class Form {
    public List<TextInputControl> controls;

    public Form() {
        this(new ArrayList<TextInputControl>());
    }

    public Form(List<TextInputControl> controls) {
        this.controls = controls;
    }

    public Form(TextInputControl... controls) {
        this.controls = Arrays.asList(controls);
    }
    
    public Map<String, String> getValues() {
        Map<String, String> values = new HashMap<>();
        
        controls.forEach((control) -> {
            String id = control.idProperty().get().replace("Field", "");
            String text = control.textProperty().get();
            
            values.put(id, text);
        });
        
        return values;
    }
}
