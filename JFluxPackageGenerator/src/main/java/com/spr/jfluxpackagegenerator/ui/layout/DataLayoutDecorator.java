package com.spr.jfluxpackagegenerator.ui.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;

/**
 * The class is designed to provide methods for component search an getting of layout controls data.
 * It is a "Decorator" of Layout.
 * 
 * @author Alexey Dergalev
 */
public class DataLayoutDecorator {
    
    private final CustomLayout layout;
    
    public DataLayoutDecorator(final CustomLayout lt) {
        layout = lt;
    }
    
    public CustomLayout getLayout() {
        return layout;
    }
    
    public Component getComponentById(final String id) {
        if (id == null) {
            return null;
        }
        final Iterator<Component> iter = layout.iterator();
        while (iter.hasNext()) {
            final Component comp = iter.next();
            if (id.equalsIgnoreCase(comp.getId())) {
                return comp;
            }
        }
        return null;
    }
    
    public Map<String, String> getFieldAndSelectValues() {
        final Map<String, String> map = new HashMap<String, String>();
        final Iterator<Component> iter = layout.iterator();
        while (iter.hasNext()) {
            final Component comp = iter.next();
            if (comp instanceof AbstractSelect || comp instanceof AbstractTextField) {
                final String id = comp.getId();
                if (id != null) {
                    final String value =
                            (comp instanceof AbstractSelect)
                                    ? (((AbstractSelect) comp).getValue() != null
                                            ? ((AbstractSelect) comp).getValue().toString() : "")
                                    : ((AbstractTextField) comp).getValue();
                    map.put(id, value);
                }
            }
        }
        
        return map;
    }
}
