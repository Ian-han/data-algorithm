package rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Date: 5/21/21
 * Time: 3:59 PM
 * Description: No Description
 */
public class ProcessorHandler implements Runnable{

  private Object service;
  private Socket socket;

  public ProcessorHandler(Object service, Socket socket) {
    this.service = service;
    this.socket = socket;
  }

  @Override
  public void run() {
    ObjectInputStream objectInputStream = null;
    try{
      objectInputStream = new ObjectInputStream(socket.getInputStream());
      RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
      Object object = invoke(rpcRequest);

      ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
      outputStream.writeObject(object);
      outputStream.flush();
      outputStream.close();
      objectInputStream.close();

    }catch (Exception e){
      throw new RuntimeException(e);
    }finally{
      if (objectInputStream != null){
        try{
          objectInputStream.close();
        }catch (IOException e){
          e.printStackTrace();
        }
      }
    }

  }


  public Object invoke(RpcRequest rpcRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Object[] args = rpcRequest.getParameters();
    Class<?>[] types = new Class[args.length];
    for (int i=0; i<args.length; i++){
      types[i] = args[i].getClass();
    }
    Method method = service.getClass().getMethod(rpcRequest.getMethodName(),types);
    return method.invoke(service,args);
  }


}
