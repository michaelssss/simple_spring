package com.michaelssss;

import org.junit.Assert;
import org.junit.Test;

public class XMLApplicationContextTest {

  @Test
  public void testGetBean() {
    ApplicationContext applicationContext = new XMLApplicationContext("DemoXML.xml");
    TestBean testBean = (TestBean) applicationContext.getBean("testBean");
    Assert.assertNull(applicationContext.getBean("nullbean"));
    Assert.assertNotNull(testBean);
    Assert.assertTrue(TestBean.class.isInstance(testBean));
    testBean.print();
  }
}
