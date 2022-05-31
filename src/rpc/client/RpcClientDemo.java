package rpc.client;

/**
 * Date: 5/21/21
 * Time: 4:43 PM
 * Description: No Description
 */
public class RpcClientDemo {
  public static void main(String[] args){
    RpcClientProxy proxy = new RpcClientProxy();
    IHelloClient hello = proxy.clientProxy(IHelloClient.class,"localhost",58983);
    System.out.println(hello.sayHello("RPC"));
  }
}
