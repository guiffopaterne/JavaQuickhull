package fenetre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./image/logo.png"));
		setTitle("Polygone convexe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		JMenuItem sampleData ; 
		sampleData =new JMenuItem("Generer donnees");
		sampleData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 final int  col =2;
				 final int  row =1000;
				 Random RANDOM = new Random();
				File ouputFile = new File("data/data_output.csv");
				try {
					PrintWriter writer = new PrintWriter(ouputFile);
					for(int i=0;i<row;i++) {
						
						writer.printf("%d",RANDOM.nextDouble());
					}
				}catch(FileNotFoundException e1) {
					System.err.println(e1);
					
				}
			}
		});
		
		menuBar.add(mnNewMenu);
		mnNewMenu.add(sampleData);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
