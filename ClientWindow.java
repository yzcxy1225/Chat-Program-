package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ClientWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();

	private Client client;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientWindow() {
		initialize();

		String name = JOptionPane.showInputDialog("Enter Name");

		client = new Client(name, "localhost", 52864);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Chat Pargarm\r\n");
		frame.setBounds(100, 100, 600, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		messageField = new JTextField();
		panel.add(messageField);
		messageField.setColumns(50);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(e -> {
			if (!messageField.equals("")) {
				client.send(messageField.getText());
				messageField.setText("");
			}
		});
		panel.add(btnSend);

		frame.setLocationRelativeTo(null);

	}

	public static void printToConsole(String message) {
		textArea.setText(textArea.getText() + message + "\n");

	}

}
