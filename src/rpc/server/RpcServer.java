package rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 5/21/21
 * Time: 4:11 PM
 * Description: No Description
 */
public class RpcServer {
  private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  public void publisher(Object service, int port){
    ServerSocket serverSocket = null;
    try{
      serverSocket = new ServerSocket(port);
      while (true){
        Socket socket = serverSocket.accept();
        EXECUTOR_SERVICE.execute(new ProcessorHandler(service,socket));
      }
    }catch (Exception e){
      throw new RuntimeException(e);
    }finally{
      if (serverSocket != null){
        try{
          serverSocket.close();
        }catch (IOException e){
          e.printStackTrace();
        }
      }
    }
  }
}
