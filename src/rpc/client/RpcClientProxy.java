package rpc.client;

import java.lang.reflect.Proxy;

/**
 * Date: 5/21/21
 * Time: 4:22 PM
 * Description: No Description
 */
public class RpcClientProxy {
  public <T> T clientProxy(Class<T> interfaceClass, String host, int port){
    return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new RemoteInvocationHandler(host,port));
  }
}
