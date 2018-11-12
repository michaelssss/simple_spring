package com.michaelssss;

import com.michaelssss.impl.BeanDefinitionInXMLLoader;
import java.util.Set;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public interface BeanDefinitionInLoader {

  static BeanDefinitionInLoader newInstance(String relatePath) {
    return new BeanDefinitionInXMLLoader(relatePath);
  }

  Set<BeanDefinition> parse();
}
