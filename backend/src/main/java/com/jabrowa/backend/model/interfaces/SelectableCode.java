package com.jabrowa.backend.model.interfaces;

/**
 * <strong>SelectableCode</strong> - interface<br><br>
 * An interface from which a default marked constant can be selected to use for pre-selecting that constant.
 * The interface forces the implementation of the following elements:
 * <ol>
 *     <liGetter for the key value (database storage code) of the constant.</li>
 *     <li>Getter for mandatory code attribute of the constant.</li>
 *     <li>Getter for the display value (short description) of the constant.</li>
 *     <li>Getter for the indicator that determines if the constant is active.</li>
 *     <li>Getter for the indicator that determines if the constant is the default set value.</li>
 *     <li>Method for the generic retrieval of the default set value.</li>
 * </ol>
 */
public interface SelectableCode {

    int getKeyValue();

    String getCode();

    String getDisplay();

    boolean isActive();

    boolean isDefault();

    Enum selectDefault();


}


