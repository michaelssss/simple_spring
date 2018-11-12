package com.michaelssss.utils;

/**
 * @author Michaelssss
 * @date 2018/11/12
 */
public class StringUtils {

  public static boolean isEmpty(CharSequence sequence) {
    return sequence == null || sequence.length() == 0;
  }

  public static boolean isNotEmpty(CharSequence sequence) {
    return !isEmpty(sequence);
  }
}
