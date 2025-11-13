package com.jabrowa.backend.EnumTests;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.utilities.EnumUtilities;
import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.InheritanceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostLoad_PrePersistTests {

    @Basic(fetch = FetchType.LAZY)
    private int genderKeyValue;

    @Test
    public void postLoadGenderTest() {
    }

}
