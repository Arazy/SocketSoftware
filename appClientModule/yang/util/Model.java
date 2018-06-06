package yang.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息数据
 * 
 * @author Administrator
 *
 */
public class Model implements Serializable {

	public Model() {
		new RealTime().start();
	}

	public void inHandle(){}
	
	public void outHandle(){}

	class RealTime extends Thread {
		private SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		@Override
		public void run() {
			while (true) {
				try {
					date = dfDate.format(new Date());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 休眠1秒
			}
		}
	}

	private String name;
	private String date;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
