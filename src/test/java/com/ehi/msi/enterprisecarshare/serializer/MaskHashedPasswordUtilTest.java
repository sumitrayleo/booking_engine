package com.ehi.msi.enterprisecarshare.serializer;

import org.junit.Test;

import com.ehi.msi.test.AbstractSpringTest;

public class MaskHashedPasswordUtilTest extends AbstractSpringTest {

    private final static String TEST_URL = "http://www.site.com/api/someOperation?saltedHash=448763871943&param=3";
    private final static String EXPECTED_OUTPUT = "http://www.site.com/api/someOperation?saltedHash=****&param=3";

    @Test
    public void testGood() {
        assertEquals(EXPECTED_OUTPUT, MaskHashedPasswordUtil.mask(TEST_URL));
    }

}
