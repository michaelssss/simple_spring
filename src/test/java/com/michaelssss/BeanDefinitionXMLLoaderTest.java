package com.michaelssss;

import org.junit.Test;

public class BeanDefinitionXMLLoaderTest {
    @Test
    public void testParse() {
        BeanDefinitionLoader beanDefinitionLoader = BeanDefinitionLoader.newInstance(
            "DemoXML.xml");
        beanDefinitionLoader.parse();
    }
}
