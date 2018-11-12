package com.michaelssss;

import com.michaelssss.impl.XMLApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class XMLApplicationContextTest {

  ApplicationContext applicationContext = new XMLApplicationContext(
      "DemoXML.xml");

  @Test
  public void testGetBean() {
    TestBean testBean = (TestBean) applicationContext.getBean("refBean");
    Assert.assertNotNull(testBean);
    testBean.print();
  }

  @Test(expected = InitialBeanFailedException.class)
  public void getNullBeanShouldThrowException() {
    applicationContext.getBean("nullbean");
  }
}
