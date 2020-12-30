package org.silverbulleters.bsl.platform.context.platform;

import org.silverbulleters.bsl.platform.context.internal.BaseMethod;
import org.silverbulleters.bsl.platform.context.internal.GeneralMethodBuilder;

public class Method extends BaseMethod {

  private Method() {
    // noop
  }

  public static MethodBuilder builder() {
    return new MethodBuilder();
  }

  public static class MethodBuilder extends GeneralMethodBuilder<Method> {
    private MethodBuilder() {
      method = new Method();
    }
  }
}
