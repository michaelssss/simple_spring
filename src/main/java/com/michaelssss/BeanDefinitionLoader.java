package com.michaelssss;

import com.michaelssss.impl.BeanDefinitionXMLLoader;
import java.util.Set;

/**
 * 用来加载BeanDefinition
 *
 * @author Michaelssss
 * @date 2018/11/12
 */
public interface BeanDefinitionLoader {

  /**
   * 建造者模式
   *
   * @param relatePath Resource的相对路径
   * @return 一个BeanDefinition的实现
   */
  static BeanDefinitionLoader newInstance(String relatePath) {
    return new BeanDefinitionXMLLoader(relatePath);
  }

  /**
   * @return 解析后的BeanDefinition集合
   */
  Set<BeanDefinition> parse();
}
