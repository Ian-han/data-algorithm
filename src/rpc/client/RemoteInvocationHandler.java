package rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date: 5/21/21
 * Time: 4:25 PM
 * Description: No Description
 */
public class RemoteInvocationHandler implements InvocationHandler {
  private String host;
  private int port;

  public RemoteInvocationHandler(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    RpcRequest request = new RpcRequest();
    request.setClassName(method.getDeclaringClass().getName());
    request.setMethodName(method.getName());
    request.setParameters(args);

    TcpTransport tcpTransport = new TcpTransport(host,port);

    return tcpTransport.send(request);
  }
}
