package com.jabrowa.backend.model.interfaces;

/**
 * <strong>SelectableCode</strong> - interface<br><br>
 * An interface from which a default marked constant can be selected to use for pre-selecting that constant.
 * The interface forces the implementation of the following elements:
 * <ol>
 *     <li>A getter for the default marking indicator of the constant/li>
 *     <li>A getter for the display value of the constant.</li>
 *     <li>A  method that selects the default marked constant in the enumerator.</li>
 *     <li>The (common) method to present the current selected constant and the atrributes in a readable manner.</li>
 * </ol>
 */
public interface SelectableCode {

    String getCode();
    String getDisplay();
    boolean isActive();
    boolean isDefault();

    Enum selectDefault();


}


