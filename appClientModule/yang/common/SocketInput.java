package yang.common;

import java.net.*;

import yang.common.util.Model;

import java.io.*;

/**
 * �����������ܴӷ��������͵�����
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
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}