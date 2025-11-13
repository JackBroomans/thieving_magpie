package com.jabrowa.backend.model.interfaces;

/**
 * <strong>SelectableCode</strong> - interface<br><br>
 * Interface which should be implemented by enumerators to find and select a default marked constant used for
 * pre-selecting that constant. The interface needs also to be implemented for enumerators which use mapped compact
 * data persistence by a compact key value.
 * The interface requires implementation of the following methods:
 * <ol>
 *     <li>Getter for the key value (database storage code) of the constant.</li>
 *     <li>Getter for mandatory code attribute of the constant.</li>
 *     <li>Getter for the display value (short description) of the constant.</li>
 *     <li>Getter for the indicator that determines if the constant is active.</li>
 *     <li>Getter for the indicator that determines if the constant is the default set value.</li>
 *     <li>Method for the generic retrieval of the default set value.</li>
 * </ol>
 *  @param <K> Type of the parameter which is mapped to the type of the mapped database field as compact key value.
 */
public interface SelectableCode<K> {

    short getNumber();

    String getCode();

    String getDisplay();

    boolean isActive();

    boolean isDefault();

    Enum<?> selectDefault();


}


