package com.jabrowa.backend.model.interfaces;

/**
 * <strong>Selectable</strong> - interface<br><br>
 * This interface must be implemented by each enumerator which applies a default setting for an element which can be
 * pre-selected. It forces to implement:
 * <ul>
 *     <li>An attribute which points to the element what is set as the default one, enabling pre-selection.</li>
 *     <li>A (setter) method, which sets the isSelected flag when an element is selected.</li>
 * </ul>
 */
public interface Selectable {
    boolean defaultElement();

    void selectDefaultElement();
}
