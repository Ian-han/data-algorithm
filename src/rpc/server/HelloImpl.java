package rpc.server;

/**
 * Date: 5/21/21
 * Time: 3:54 PM
 * Description: No Description
 */
public class HelloImpl implements IHelloServer{

  public String sayHello(String message){return "Hello" + message;}
}
