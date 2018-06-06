package yang.util;

import java.awt.EventQueue;

import yang.Frame;

/**
 * ���ڶ�socketͨѶ��Ĳ���
 * @author yxl
 *
 */
public class FrameModel extends Model {
	
	@Override
	public void inHandle(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame.textArea.append(getName() + "	" + getDate() + "\n");
					Frame.textArea.append(getContent() + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void outHandle(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame.textArea.append(getName() + "	" + getDate() + "\n");
					Frame.textArea.append(getContent() + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}