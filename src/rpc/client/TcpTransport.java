package rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Date: 5/21/21
 * Time: 4:37 PM
 * Description: No Description
 */
public class TcpTransport {
  private String host;
  private int port;

  public TcpTransport(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public Object send(RpcRequest rpcRequest){
    Socket socket = null;

    try{
      socket = new Socket(host,port);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectOutputStream.writeObject(rpcRequest);
      objectOutputStream.flush();

      ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
      Object result = objectInputStream.readObject();
      objectInputStream.close();
      objectOutputStream.close();

      return result;
    }catch (Exception e){
      throw new RuntimeException(e);
    }finally {
      if (socket != null){
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
