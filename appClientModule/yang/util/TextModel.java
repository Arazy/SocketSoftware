package yang.util;

import java.awt.EventQueue;

import yang.Test;

/**
 * ���ڶ�socketͨѶ��Ĳ���
 * @author yxl
 *
 */
public class TextModel extends Model {
	
	@Override
	public void inHandle(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test.textArea.append(getName() + "	" + getDate() + "\n");
					Test.textArea.append(getContent() + "\n");
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
					Test.textArea.append(getName() + "	" + getDate() + "\n");
					Test.textArea.append(getContent() + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}