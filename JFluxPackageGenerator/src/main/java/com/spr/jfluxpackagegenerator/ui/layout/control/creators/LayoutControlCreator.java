package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.vaadin.ui.CustomLayout;

/**
 * The interface is designed to maintain a component creation process for layout.
 * 
 * @author Alexey Dergalev
 */
public interface LayoutControlCreator {
    
    /**
     * Create components on the layout.
     * 
     * @param layout UI layout
     */
    void createComponents(CustomLayout layout, MainUIController controller);
    
}
