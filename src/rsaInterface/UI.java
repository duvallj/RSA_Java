package rsaInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import rsaCrypt.Decoder;
import rsaCrypt.Encoder;
import javax.swing.JComboBox;

public class UI {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextArea textArea;
	private static Encoder e = new Encoder();
	private static Decoder d = new Decoder();
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}
	

	private static void encodeClick(){
		try {
			writeMessage(e.encode(getMessage(textArea)), getFileName(textField));
		} catch (FileNotFoundException derp) {
			newFile(Paths.get(getFileName(textField)));
		}
		refresh();
	}
	private static void decodeClick(){
		String message = d.decode(getFile(Paths.get((String)comboBox.getSelectedItem())));
		textArea.setText(message);
		refresh();
	}
	private static void newFile( Path filename ){
		try {
		    Files.createFile(filename);
		} catch (IOException ex) {
			//nothing, just keeps regular file
		}
		refresh();
	}
	private static String getFileName( JTextField t){
		return t.getText().concat(".bef3");
	}
	private static String getMessage( JTextArea a ){
		return a.getText();
	}
	private static void writeMessage( String m, String p ) throws FileNotFoundException{
		PrintWriter printWriter = new PrintWriter(p);
		printWriter.print(m);	
		printWriter.close();
	}
	private static String getFile( Path p ){
		List<String> messageInList = null;
		try {
			messageInList = Files.readAllLines(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageInList.toArray(new String[messageInList.size()])[0];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void refresh(){
		
		frame.getContentPane().remove(comboBox);
		comboBox = new JComboBox();
		comboBox.setBounds(410, 48, 150, 25);
		frame.getContentPane().add(comboBox);
		
		File[] listOfFiles = new File(".").listFiles();
		
		for(int x=0; x<listOfFiles.length; x++){
			if(listOfFiles[x].getName().endsWith(".bef3") && listOfFiles[x].isFile()){
				comboBox.addItem(listOfFiles[x].getName());
			}
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		JFrame frame_1 = new JFrame();
		setFrame(frame_1);
		frame_1.getContentPane().setLayout(null);
		setFrame(frame);
		
		comboBox = new JComboBox();
		comboBox.setBounds(410, 48, 150, 25);
		getFrame().getContentPane().add(comboBox);
		getFrame().setBounds(100, 100, 580, 380);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		
		
		JLabel lblInputoutput = new JLabel("Input/Output");
		lblInputoutput.setBounds(10, 11, 89, 14);
		getFrame().getContentPane().add(lblInputoutput);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 36, 382, 294);
		getFrame().getContentPane().add(textArea);
		
		JButton btnEncode = new JButton("Encode");
		btnEncode.setBounds(149, 7, 89, 23);
		//when someone clicks encode
		btnEncode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				encodeClick();
			}
		});
		getFrame().getContentPane().add(btnEncode);
		
		JButton btnDecode = new JButton("Decode");
		btnDecode.setBounds(443, 100, 89, 23);
		//when someone clicks decode
		btnDecode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				decodeClick();
			}
		});
		
		getFrame().getContentPane().add(btnDecode);
		
		JLabel lblFilename = new JLabel("Filename");
		lblFilename.setBounds(465, 11, 67, 14);
		getFrame().getContentPane().add(lblFilename);
		
		/*list = new JList();
		list.setBounds(423, 40, 109, 146);
		frame.getContentPane().add(list);*/
		refresh();
		
		/*JButton btnMakeNewFile = new JButton("Make new file");
		btnMakeNewFile.setBounds(402, 197, 152, 23);
		btnMakeNewFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				newFile(Paths.get(getFileName(textField)));
			}
		});
		frame.getContentPane().add(btnMakeNewFile);*/
		
		textField = new JTextField();
		textField.setBounds(248, 8, 152, 20);
		getFrame().getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UI.class.getResource("/rsaInterface/logo-lowres.png")));
		label.setBounds(465, 259, 20, 46);
		getFrame().getContentPane().add(label);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		UI.frame = frame;
	}
}
