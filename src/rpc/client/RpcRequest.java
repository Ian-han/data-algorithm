package rpc.client;

import java.io.Serializable;

/**
 * Date: 5/21/21
 * Time: 3:57 PM
 * Description: No Description
 */
public class RpcRequest implements Serializable {
  private static final long serialVersionUID = -1L;

  private String className;
  private String methodName;
  private Object[] parameters;

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getParameters() {
    return parameters;
  }

  public void setParameters(Object[] parameters) {
    this.parameters = parameters;
  }
}
