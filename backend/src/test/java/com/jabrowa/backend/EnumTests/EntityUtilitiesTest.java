package com.jabrowa.backend.EnumTests;

import org.junit.platform.commons.annotation.Testable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jabrowa.backend.model.codes.ladis.SourceOfIncome;

@Testable
public class EntityUtilitiesTest {
 private final Logger logger = LoggerFactory.getLogger(EntityUtilitiesTest.class);

    public void CorrectlyFormatsLadisCodeEnumToPrettyString() {

        
        System.out.println(SourceOfIncome._SourceOfIncome.LOON.toNiceString());

    }

}
