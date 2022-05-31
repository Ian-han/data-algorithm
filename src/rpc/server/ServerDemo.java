package rpc.server;

/**
 * Date: 5/21/21
 * Time: 4:16 PM
 * Description: No Description
 */
public class ServerDemo {
  public static void main(String[] args){
    IHelloServer hello = new HelloImpl();
    RpcServer rpcServer = new RpcServer();
    rpcServer.publisher(hello,58983);
  }
}
