package com.michaelssss;

import org.junit.Assert;
import org.junit.Test;

public class BeanTest {
    @Test
    public void test() {
        ApplicationContext applicationContext = new DemoApplicationContext();
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        Assert.assertNotNull(testBean);
        Assert.assertTrue(TestBean.class.isInstance(testBean));
    }
}
