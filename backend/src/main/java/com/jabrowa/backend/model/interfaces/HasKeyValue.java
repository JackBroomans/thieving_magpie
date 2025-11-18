package com.jabrowa.backend.model.interfaces;

/**
 * <strong>HasKeyValue</strong> - interface<br><br>
 * Interface which must be implemented by enumerators to define the (mandatory) key value (Number). This key value is
 * persisted in the database to construct a compact storage while the enumerator is used throughout the backend.
 * The interface requires implementation of the following method:
 * <ol>
 *     <li>The key value (number) of the enumerated constant, which is a mandatory property a constant.</li>
 * </ol>
 */
public interface HasKeyValue {
    short getNumber();
}
