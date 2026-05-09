package com.sismics.docs.core.util;

import com.sismics.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test of {@link SecurityUtil}.
 */
public class TestSecurityUtil extends BaseTest {
    @Test
    public void skipAclCheckAdminTest() {
        Assert.assertTrue(SecurityUtil.skipAclCheck(Arrays.asList("admin")));
    }

    @Test
    public void skipAclCheckAdministratorsTest() {
        Assert.assertTrue(SecurityUtil.skipAclCheck(Arrays.asList("administrators")));
    }

    @Test
    public void skipAclCheckRegularUserTest() {
        Assert.assertFalse(SecurityUtil.skipAclCheck(Arrays.asList("user")));
    }
}