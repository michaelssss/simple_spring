package com.michaelssss;

import org.junit.Test;

public class BeanDefinitionXMLLoaderTest {
    @Test
    public void testParse() {
        BeanDefinitionInXMLLoadder beanDefinitionInXMLLoadder = BeanDefinitionInXMLLoadder.newInstance("DemoXML.xml");
        beanDefinitionInXMLLoadder.parse();
    }
}
