package com.michaelssss;

import org.junit.Assert;

public class Test {
    @org.junit.Test
    public void test() {
        ApplicationContext applicationContext = new DefaultApplicationContext();
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        Assert.assertNotNull(testBean);
        Assert.assertTrue(TestBean.class.isInstance(testBean));
    }
}
