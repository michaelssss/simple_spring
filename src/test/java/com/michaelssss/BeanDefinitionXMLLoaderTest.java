package com.michaelssss;

import org.junit.Test;

public class BeanDefinitionXMLLoaderTest {
    @Test
    public void testParse() {
        BeanDefinitionInXMLLoader beanDefinitionInXMLLoader = BeanDefinitionInXMLLoader.newInstance("DemoXML.xml");
        beanDefinitionInXMLLoader.parse();
    }
}
