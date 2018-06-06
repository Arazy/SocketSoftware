package yang;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import yang.util.FrameModel;
import yang.util.Model;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Frame extends JFrame {

	private int port = 8189;
	private SocketOutput output;
	private SocketInput<FrameModel> input;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;
	public static JTextArea textArea;
	private JTextArea textArea_1;
	private JButton button_1;
	private JLabel lblPort_1;
	private JTextField textField_2;
	private JLabel lblPort_2;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPort = new JLabel("\u7528\u6237\u540D");
		contentPane.add(lblPort);

		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(5);
		
		lblPort_2 = new JLabel("PORT");
		contentPane.add(lblPort_2);
		
		textField_3 = new JTextField();
		contentPane.add(textField_3);
		textField_3.setColumns(3);

		button = new JButton("\u542F\u52A8\u670D\u52A1");
		contentPane.add(button);

		textArea = new JTextArea();
		textArea.setColumns(29);
		textArea.setRows(18);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		contentPane.add(new JScrollPane(textArea));

		textArea_1 = new JTextArea();
		textArea_1.setRows(5);
		textArea_1.setColumns(29);
		textArea_1.setLineWrap(true);
		contentPane.add(new JScrollPane(textArea_1));
		
		JLabel lblIp = new JLabel("IP");
		contentPane.add(lblIp);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPort_1 = new JLabel("Port");
		contentPane.add(lblPort_1);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);
		textField_2.setColumns(3);

		button_1 = new JButton("\u63D0\u4EA4");
		button_1.setEnabled(false);
		contentPane.add(button_1);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (null == input) {
					input = new SocketInput<FrameModel>(Integer.valueOf(textField_3.getText()));
					input.start();
					button.setText("关闭服务");
					button_1.setEnabled(true);
					textArea_1.requestFocus();
				} else {
					if (input.execute) {
						input.stopThread();
						button.setText("开启服务");
						button_1.setEnabled(false);
					} else {
						input = new SocketInput<FrameModel>(port);
						input.start();
						button.setText("关闭服务");
						button_1.setEnabled(true);
						textArea_1.requestFocus();
					}
				}

			}
		});

		Model model = new FrameModel();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				model.setName(textField_1.getText());
				model.setContent(textArea_1.getText());
				output = new SocketOutput(textField.getText(), Integer.valueOf(textField_2.getText()), model);
				output.start();
				
				textArea_1.setText("");
				textArea_1.requestFocus();
			}
		});
	}

}
