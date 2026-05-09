package com.sismics.util.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test of {@link ThreadLocalContext}.
 */
public class TestThreadLocalContext {
    @After
    public void tearDown() {
        ThreadLocalContext.cleanup();
    }

    @Test
    public void getReturnsSameInstanceUntilCleanupTest() {
        ThreadLocalContext first = ThreadLocalContext.get();
        ThreadLocalContext second = ThreadLocalContext.get();

        Assert.assertSame(first, second);
    }

    @Test
    public void cleanupCreatesNewInstanceTest() {
        ThreadLocalContext first = ThreadLocalContext.get();
        ThreadLocalContext.cleanup();
        ThreadLocalContext second = ThreadLocalContext.get();

        Assert.assertNotSame(first, second);
    }
}