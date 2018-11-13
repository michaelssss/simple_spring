package com.michaelssss;

import com.michaelssss.impl.SimpleBeanPropertyAccessor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michaelssss
 * @date 2018/11/13
 */
public class SimpleBeanPropertyAccessorTest {

  @Test
  public void testGetProperty() {
    TestBean testBean = new TestBean();
    testBean.setWord("test");
    BeanPropertyAccessor beanPropertyAccessor = SimpleBeanPropertyAccessor
        .newInstance(TestBean.class);
    Assert.assertEquals("test", beanPropertyAccessor.getProperty(testBean, "word"));
  }

}
