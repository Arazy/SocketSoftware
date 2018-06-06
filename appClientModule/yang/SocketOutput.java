package yang;

import java.net.*;

import yang.util.Model;

import java.io.*;

/**
 * �ͻ��ˣ����������������
 * @author yxl
 *
 */
public class SocketOutput extends Thread {
	public Socket socket = null;
	private Model model = null;
	
	/**
	 * �����ӣ�ÿ���½�socket
	 * @param ip
	 * @param port
	 * @param model
	 */
	public SocketOutput(String ip, int port,Model model) {
		this.setName("SocketOutput");
		this.model = model;
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ������
	 * @param socket
	 * @param model
	 */
	public SocketOutput(Socket socket,Model model) {
		this.setName("SocketOutput");
		this.socket = socket;
		this.model = model;

	}

	@Override
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(model);
			out.flush();
			out.close();
			model.outHandle();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}