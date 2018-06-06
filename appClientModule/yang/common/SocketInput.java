package yang.common;

import java.net.*;

import yang.common.util.Model;

import java.io.*;

/**
 * 服务器：接受从服务器发送的数据
 * 
 * @author yxl
 *
 */
public class SocketInput<T extends Model> extends Thread {
	public Socket socket = null;
	public ServerSocket serverSocket = null;
	public int port;
    public boolean execute = true;
    
	public SocketInput(int port) {
		this.port = port;
	}
	
	public void stopThread(){
		try {
			if(!serverSocket.isClosed()){
				serverSocket.close();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		this.execute = false;
	}

	@Override
	public void run() {

		try {
			serverSocket = new ServerSocket(port);
			while (execute) {
				socket = serverSocket.accept();
				new ThreadInputStream<T>(socket).start();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	
	class ThreadInputStream<T extends Model> extends Thread {
		private Socket socket;

		public ThreadInputStream(Socket socket) {
			this.setName("ThreadInputStream");
			this.socket = socket;
		}

		@Override
		public void run() {

			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				T model = (T) ois.readObject();
				model.inHandle();

			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}