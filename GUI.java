package uk.co.kishan.Alarm;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI {

	private JFrame frame;
	private JLabel lblClock;
	private JTextField setAlarmTIme;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clock() {
		Thread clock=new Thread() {
			public void run () {
				try { 
					for(;;) { // infinite loop 
						Calendar cal=new GregorianCalendar();
						int second = cal.get(Calendar.SECOND);
						int minute = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR);
						
						lblClock.setText("   Time "+hour+":"+minute+":"+second);
						
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				}; 
				clock.start();
	}
	

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		clock();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 567, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblClock = new JLabel("Clock");
		lblClock.setBackground(Color.LIGHT_GRAY);
		lblClock.setForeground(Color.GREEN);
		lblClock.setFont(new Font("Heiti TC", Font.BOLD, 73));
		
		JSplitPane splitPane = new JSplitPane();
		
		JButton btnStop = new JButton("STOP");
		splitPane.setLeftComponent(btnStop);
		
		JButton btnSnooze = new JButton("SNOOZE");
		btnSnooze.setBackground(UIManager.getColor("Button.light"));
		splitPane.setRightComponent(btnSnooze);
		
		JButton btnSetAlarm = new JButton("Set Alarm");
		btnSetAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSetAlarm.setBackground(UIManager.getColor("Button.light"));
		
		setAlarmTIme = new JTextField();
		setAlarmTIme.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(92)
							.addComponent(setAlarmTIme, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSetAlarm, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
						.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClock, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(lblClock, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(setAlarmTIme, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btnSetAlarm, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
