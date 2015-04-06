import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import widgets.ChooseRandom;

import javax.swing.JLabel;

import rnd.Negexp;
import rnd.Norm;
import rnd.Triangular;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;

import widgets.Diagram;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.SwingConstants;
import javax.xml.parsers.FactoryConfigurationError;

import process.Dispatcher;
import widgets.ChooseData;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import widgets.experiments.ExperimentControl;
import widgets.regres.RegresAnaliser;
import widgets.trans.TransMonitorView;
import widgets.trans.ParmFinderView;


public class Main extends JFrame {

	private JPanel contentPane;
	private ChooseRandom chooseRandom;
	private JTextArea textArea;
	private Diagram diagram;
	private Diagram diagram_2;
	private Diagram diagram_1;
	private JButton btnStart;
	private ChooseRandom chooseRandom_1;
	private ChooseRandom chooseRandom_2;
	private ChooseData chooseData_1;
	private ChooseData chooseData_2;
	private ChooseData chooseData_3;
	private ChooseData chooseData_4;
	private JLabel label_2;
	private ChooseRandom chooseRandom_3;
	private JButton btnNewButton;
	private Diagram diagram_3;
	private JPanel panel_3;
	private Diagram diagram_4;
	private ExperimentControl experimentControl;
	private RegresAnaliser regresAnaliser;
	private JPanel panel_4;
	private Diagram diagram_5;
	private TransMonitorView transMonitorView;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 304, 507);
		contentPane.add(panel);
		panel.setLayout(null);
		
		chooseRandom = new ChooseRandom();
		chooseRandom.setBounds(0, 32, 304, 39);
		chooseRandom.setRandom(new Negexp(2));
		panel.add(chooseRandom);
		
		JLabel lblNewLabel = new JLabel("\u0406\u043D\u0442\u0435\u0440\u0432\u0430\u043B");
		lblNewLabel.setBounds(10, 11, 63, 25);
		panel.add(lblNewLabel);
		
		chooseRandom_1 = new ChooseRandom();
		chooseRandom_1.setBounds(0, 96, 304, 39);
		chooseRandom_1.setRandom(new Norm(5,1));
		panel.add(chooseRandom_1);
		
		JLabel label = new JLabel("\u0427\u0430\u0441 \u043E\u0431\u0441\u043B\u0443\u0433\u043E\u0432\u0443\u0432\u0430\u043D\u043D\u044F");
		label.setBounds(10, 82, 181, 14);
		panel.add(label);
		
		chooseRandom_2 = new ChooseRandom();
		chooseRandom_2.setBounds(0, 159, 304, 39);
		chooseRandom_2.setRandom(new Triangular(0.5,1,1.5));
		panel.add(chooseRandom_2);
		
		JLabel label_1 = new JLabel("\u0427\u0430\u0441 \u0432\u0438\u0457\u0437\u0434\u0443");
		label_1.setBounds(10, 146, 111, 14);
		panel.add(label_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(324, 11, 412, 719);
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Тест моделі", panel_1);
		panel_1.setLayout(null);
		
		diagram = new Diagram();
		diagram.setPainterColor(Color.BLUE);
		diagram.setHorizontalMaxText("100");
		diagram.setTitleText("\u0427\u0435\u0440\u0433\u0430 \u043D\u0430 \u043E\u0431\u0441\u043B\u0443\u0433\u043E\u0432\u0443\u0432\u0430\u043D\u043D\u044F");
		diagram.setBounds(10, 11, 302, 136);
		panel_1.add(diagram);
		
		diagram_1 = new Diagram();
		diagram_1.setPainterColor(Color.BLUE);
		diagram_1.setHorizontalMaxText("100");
		diagram_1.setTitleText("\u0427\u0435\u0440\u0433\u0430 \u043D\u0430 \u0432\u0438\u0457\u0437\u0434");
		diagram_1.setBounds(10, 158, 302, 136);
		panel_1.add(diagram_1);
		
		diagram_2 = new Diagram();
		diagram_2.setPainterColor(Color.BLUE);
		diagram_2.setHorizontalMaxText("100");
		diagram_2.setTitleText("\u0412\u0442\u0440\u0430\u0447\u0435\u043D\u0456 \u043A\u043B\u0456\u0454\u043D\u0442\u0438");
		diagram_2.setBounds(10, 305, 302, 136);
		panel_1.add(diagram_2);
		
		btnStart = new JButton("\u0421\u0442\u0430\u0440\u0442");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onClick();
			}
		});
		btnStart.setBounds(10, 445, 302, 23);
		panel_1.add(btnStart);
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Статистика", panel_2);
		panel_2.setLayout(null);
		
		diagram_3 = new Diagram();
		diagram_3.setPainterColor(Color.BLUE);
		diagram_3.setTitleText("\u0413\u0456\u0441\u0442\u043E\u0433\u0440\u0430\u043C\u0430 \u0447\u0435\u0440\u0433\u0438 \u0434\u043E \u043A\u0430\u0441\u0438\u0440\u0430");
		diagram_3.setBounds(10, 11, 302, 171);
		panel_2.add(diagram_3);
		
		btnNewButton = new JButton("\u041F\u043E\u0447\u0430\u0442\u0438 \u0435\u043A\u0441\u043F\u0435\u0440\u0438\u043C\u0435\u043D\u0442");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onBtnStat();
			}
		});
		btnNewButton.setBounds(10, 445, 302, 23);
		panel_2.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 302, 241);
		panel_2.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		contentPane.add(tabbedPane);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("\u0417\u0430\u043B\u0435\u0436\u043D\u0456\u0441\u0442\u044C", null, panel_3, null);
		panel_3.setLayout(null);
		diagram_4 = new Diagram();
		diagram_4.setHorizontalMaxText("13");
		diagram_4.setVerticalMaxText("2");
		diagram_4.setVerticalMinText("-2");
		diagram_4.setBounds(10, 303, 302, 261);
		panel_3.add(diagram_4);
		
		ExperimentControl experimentControl = new ExperimentControl();
		experimentControl.setFactory(new Factory(this));
		experimentControl.setDiagram(diagram_4);
		experimentControl.setTextFactors("7 8 9 10");
		experimentControl.setBounds(10, 11, 302, 129);
		panel_3.add(experimentControl);
		
		regresAnaliser = new RegresAnaliser();
		regresAnaliser.setBounds(10, 142, 302, 150);
		regresAnaliser.setDiagram(diagram_4);
		regresAnaliser.setIRegresable(experimentControl);
		regresAnaliser.addFunction(new MyClass1());
		regresAnaliser.addFunction(new MyClass2());
		panel_3.add(regresAnaliser);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("\u041F\u0435\u0440\u0435\u0445. \u043F\u0440\u043E\u0446\u0435\u0441\u0438", null, panel_4, null);
		panel_4.setLayout(null);
		
		diagram_5 = new Diagram();
		diagram_5.setBounds(10, 320, 387, 360);
		diagram_5.setPainterColor(Color.BLUE);
		panel_4.add(diagram_5);
		
		transMonitorView = new TransMonitorView();
		transMonitorView.setBounds(21, 11, 376, 298);
		transMonitorView.setFactory(new Factory(this));
		transMonitorView.setDiagram(diagram_5);
		panel_4.add(transMonitorView);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("\u0422\u0417", null, panel_5, null);
		panel_5.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 387, 669);
		panel_5.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		
		
		
		chooseData_1 = new ChooseData();
		chooseData_1.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				onChangeLength();
			}
		});
		chooseData_1.setText("3");
		chooseData_1.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0441\u043C\u0443\u0433");
		chooseData_1.setBounds(10, 269, 284, 46);
		panel.add(chooseData_1);
		
		chooseData_2 = new ChooseData();
		chooseData_2.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				onChangeLength();
			}
		});
		chooseData_2.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043C\u0456\u0441\u0446\u044C \u043D\u0430 \u0441\u043C\u0443\u0437\u0456");
		chooseData_2.setText("3");
		chooseData_2.setBounds(10, 326, 284, 46);
		panel.add(chooseData_2);
		
		chooseData_3 = new ChooseData();
		chooseData_3.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					diagram_1.setVerticalMaxText(String.valueOf((Integer.parseInt(getChooseData_3().getText())+1)));
				}catch(Exception e){};}
		});
		chooseData_3.setText("4");
		chooseData_3.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043C\u0456\u0441\u0446\u044C \u043D\u0430 \u0432\u0438\u0457\u0437\u0434\u0456");
		chooseData_3.setBounds(10, 379, 284, 46);
		panel.add(chooseData_3);
		
		chooseData_4 = new ChooseData();
		chooseData_4.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				diagram.setHorizontalMaxText(getChooseData_4().getText());
				diagram_1.setHorizontalMaxText(getChooseData_4().getText());
				diagram_2.setHorizontalMaxText(getChooseData_4().getText());
			}
		});
		chooseData_4.setTitle("\u0427\u0430\u0441 \u043C\u043E\u0434\u0435\u043B\u044E\u0432\u0430\u043D\u043D\u044F");
		chooseData_4.setText("100");
		chooseData_4.setBounds(10, 438, 284, 46);
		panel.add(chooseData_4);
		
		chooseRandom_3 = new ChooseRandom();
		chooseRandom_3.setBounds(0, 219, 304, 39);
		chooseRandom_3.setRandom(new Norm(5,1));
		panel.add(chooseRandom_3);
		
		label_2 = new JLabel("\u0427\u0430\u0441 \u0437\u0430\u0439\u043D\u044F\u0442\u043E\u0441\u0442\u0456 \u0434\u043E\u0440\u043E\u0433\u0438");
		label_2.setBounds(10, 205, 155, 14);
		panel.add(label_2);
		
		met();	
		
	}
	
	private void onBtnStat() {
		final Dispatcher dispatcher = new Dispatcher();
		dispatcher.setProtocolFileName("Console");
		Factory factory = new Factory(Main.this);
		final Model model = factory.createModel(dispatcher);
		model.initForStat();
		getBtnNewButton_1().setEnabled(false);
		getDiagram_3().clear();
		dispatcher.start();
		new Thread() {
			public void run() {
				try {
					dispatcher.getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getBtnNewButton_1().setEnabled(true);
				model.getHistoForQueueToCassir().showRelFrec(getDiagram_3());
				
				getTextArea().setText(model.getHistoForQueueToCassir().toString());
				getTextArea().select(0, 0);
			}
		}.start();
	}
	
	protected void onClick() {
			final Dispatcher dispatcher = new Dispatcher();
			dispatcher.setProtocolFileName("Console");
			Factory factory = new Factory(Main.this);
			Model model = factory.createModel(dispatcher);
			model.initForTest();
			btnStart.setEnabled(false);
			getDiagram().clear();
			getDiagram_1().clear();
			getDiagram_2().clear();
			dispatcher.start();
			new Thread(){
				public void run(){
					try {
						dispatcher.getThread().join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					btnStart.setEnabled(true);
				}
			}.start();

	}
	
	public void met(){
		try {
			String s = "";
			Scanner in = new Scanner(new File("res//tz.txt"));
			while(in.hasNext()){
				s += in.nextLine() + "\r\n";
			}	
			in.close();
			getTextArea_1().setText(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	protected void onChangeLength() {
		try{
			int x = Integer.parseInt( getChooseData_2().getText());
		int y = Integer.parseInt(getChooseData_1().getText());
		diagram.setVerticalMaxText(String.valueOf(x*y));
		}catch(Exception e){};
		
	}

	public ChooseRandom getChooseRandom() {
		return chooseRandom;
	}
	public Diagram getDiagram() {
		return diagram;
	}
	public Diagram getDiagram_2() {
		return diagram_2;
	}
	public Diagram getDiagram_1() {
		return diagram_1;
	}
	public JButton getBtnNewButton() {
		return btnStart;
	}
	public ChooseRandom getChooseRandom_1() {
		return chooseRandom_1;
	}
	public ChooseRandom getChooseRandom_2() {
		return chooseRandom_2;
	}
//	public ChooseData getChooseData() {
//		return chooseData;
//	}
	public ChooseData getChooseData_1() {
		return chooseData_1;
	}
	public ChooseData getChooseData_2() {
		return chooseData_2;
	}
	public ChooseData getChooseData_3() {
		return chooseData_3;
	}
	public ChooseData getChooseData_4() {
		return chooseData_4;
	}
	public ChooseRandom getChooseRandom_3() {
		return chooseRandom_3;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton;
	}
	public Diagram getDiagram_3() {
		return diagram_3;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextArea getTextArea_1() {
		return textArea_1;
	}
	public Diagram getDiagram_4() {
		return diagram_4;
	}
	public ExperimentControl getExperimentControl() {
		return experimentControl;
	}
	public RegresAnaliser getRegresAnaliser() {
		return regresAnaliser;
	}
	public Diagram getDiagram_5() {
		return diagram_5;
	}
//	public ParmFinderView getParmFinderView() {
//		return parmFinderView;
//	}
//	public TransMonitorView getTransMonitorView() {
//		return transMonitorView;
//	}
	public TransMonitorView getTransMonitorView() {
		return transMonitorView;
	}
}
