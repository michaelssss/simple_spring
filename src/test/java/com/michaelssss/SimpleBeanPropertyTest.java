package com.michaelssss;

import com.michaelssss.impl.SimpleBeanProperty;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michaelssss
 * @date 2018/11/13
 */
public class SimpleBeanPropertyTest {

  @Test
  public void testGetSetMethod() {
    SimpleBeanProperty simpleBeanProperty = new SimpleBeanProperty("property");
    Assert.assertEquals("setProperty", simpleBeanProperty.getSetMethod());
    Assert.assertEquals("getProperty", simpleBeanProperty.getGetMethod());
  }

}
