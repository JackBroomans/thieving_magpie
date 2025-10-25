package com.jabrowa.backend.model.interfaces;

/**
 * <strong>Selectable</strong> - interface<br><br>
 * This interface should be implemented by each enumerator that contains:
 * <ol>
 *     <li>A default marking for a particular constant, so this constant can be pre-selected, and</li>
 *     <li>A default marking for a particular constant, so this constant can be pre-selected.</li>
 *
 *
 * </ol> It also subscribes
 */
public interface Selectable {
    Boolean isDefaultValue();
    String getDisplay();

}

