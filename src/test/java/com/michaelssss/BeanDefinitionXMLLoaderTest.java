package com.michaelssss;

import org.junit.Test;

public class BeanDefinitionXMLLoaderTest {
    @Test
    public void testParse() {
        BeanDefinitionInLoader beanDefinitionInLoader = BeanDefinitionInLoader.newInstance(
            "DemoXML.xml");
        beanDefinitionInLoader.parse();
    }
}
