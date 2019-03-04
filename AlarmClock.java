package uk.co.kishan.Alarm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class AlarmClock extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel clockLabel;
	
	private String time;
	private String _hours;
    private String _minutes;
    private String _seconds;
    private String currentHour;
    private String currentMin;
    private String currentSec;
    
    int alarmHour;
    int alarmMinute;
    int alarmSecond ;
    int snoozeCount = 0;
    
	private ClockLogic myClock;
	
	private JTextArea txtrHour;
	private JTextArea txtrMinute;
	private JTextArea textArea_sec;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlarmClock frame = new AlarmClock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application 
	 */
	public AlarmClock() {
		setBackground(Color.WHITE);
		myClock = new ClockLogic(AlarmClock.this);
        myClock.execute();//execute the Clock 
		
		setTitle("Alarm Clock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlarm = new JButton("Set The Alarm");
		btnAlarm.setForeground(UIManager.getColor("Button.light"));
		btnAlarm.setFont(new Font("Arial", Font.BOLD, 14));
		btnAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentHour = new String (txtrHour.getText());   
				currentMin = new String (txtrMinute.getText());           /// Gets Values from textArea 
				currentSec = new String (textArea_sec.getText());
								
			     int _alarmHour = Integer.parseInt(currentHour);
			     int _alarmMinute = Integer.parseInt(currentMin); /// Converts then into Integers 
			     int _alarmSecond = Integer.parseInt(currentSec);
				
			    alarmHour = _alarmHour;
			    alarmMinute = _alarmMinute;   /// Sets the alarm from entered values 
			    alarmSecond = _alarmSecond;
			    
			    myClock.setBoolean(true);
			    myClock.setAlarm(alarmHour, alarmMinute, alarmSecond); // sets the alarm
			      System.out.println("Alarm set at: " + alarmHour +":"+ alarmMinute + ":" + alarmSecond); // prints the time the alarm has been set to   
			}
		});
		
		JButton btnSnooze = new JButton("SNOOZE!!");
		btnSnooze.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnSnooze.setForeground(new Color(255, 0, 0));
		btnSnooze.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				 
				currentHour = new String (currentHour);   
				currentMin = new String (currentMin);          
				currentSec = new String (currentSec);
								
			     int _alarmHour = Integer.parseInt(currentHour);
			     int _alarmMinute = Integer.parseInt(currentMin); /// into Integers 
			     int _alarmSecond = Integer.parseInt(currentSec);
				
			    alarmHour = _alarmHour;
			    alarmMinute = _alarmMinute++;   /// Sets the alarm from entered values 
			    alarmSecond = _alarmSecond;
			    
			    myClock.setBoolean(true);
			    myClock.setAlarm(alarmHour, alarmMinute++, alarmSecond); // resets the alarm
			      System.out.println(" ");
			      System.out.println("Snooze set at: " + alarmHour +":"+ alarmMinute + ":" + alarmSecond); // prints the time the alarm has been set to   
				
				snoozeCount++;
			    System.out.println("Snooze Count: " + snoozeCount);
			    if (snoozeCount >= 3) {
			    	System.out.println("CANT SNOOZE, GET UP!!");
			    } 
			    //System.out.println("Alarm Snoozed till: " + alarmHour +":"+ alarmMinute + ":" + alarmSecond);
			}
		});
		
		
		///// GUI STYLING 
		btnAlarm.setBounds(216, 12, 117, 59);
		contentPane.add(btnAlarm);
		
		btnSnooze.setBounds(6, 222, 327, 74);
		contentPane.add(btnSnooze);
		
		clockLabel = new JLabel("Time");
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setFont(new Font("Arial", Font.BOLD, 79));
		clockLabel.setBounds(6, 70, 327, 152);
		contentPane.add(clockLabel);
		                                                        //// Input Hour style 
		JLabel lblHrs = new JLabel("Hour");
		lblHrs.setBounds(6, 12, 61, 16);
		contentPane.add(lblHrs);
		                                                     //// Input Minute style
		JLabel lblMin = new JLabel("Minute");
		lblMin.setBounds(79, 12, 61, 16);
		contentPane.add(lblMin);
		                                                     //// Input Second style
		JLabel lblSec = new JLabel("Second");
		lblSec.setBounds(152, 12, 61, 16);
		contentPane.add(lblSec);
		
		txtrHour = new JTextArea();
		txtrHour.setFont(new Font("Arial", Font.BOLD, 50));
		txtrHour.setBounds(6, 12, 58, 59);
		contentPane.add(txtrHour);   
		
		txtrMinute = new JTextArea();
		txtrMinute.setFont(new Font("Arial", Font.BOLD, 50));
		txtrMinute.setBounds(76, 12, 58, 59);
		contentPane.add(txtrMinute);
		
		textArea_sec = new JTextArea();
		textArea_sec.setFont(new Font("Arial", Font.BOLD, 50));
		textArea_sec.setBounds(146, 12, 58, 59);
		contentPane.add(textArea_sec);
	}/////
	 
	
	public void setTime(int hour, int minutes, int seconds){ // lets the clock run on the clockLabel 
        _hours=Integer.toString(hour);
        _minutes=Integer.toString(minutes);
        _seconds=Integer.toString(seconds);
        
        if(hour < 10){          //// Places 0 in front of time is less than 10 to emulate 24 hours 
	    	   
	    	   _hours = "0" + _hours;
	       }
	       
	       if(minutes < 10)
	       {
	    	   _minutes = "0" + _minutes;
	       }
	       if(seconds < 10)
	       {
	    	   _seconds = "0" + _seconds;
	       }
        
        time = _hours + ":" +   _minutes + ":" + _seconds;
        
        clockLabel.setText(time); //Places the clock on the ClockLabel
		
	}
	
	public void invokeAlarmAlert(){ //// Flashes colour of background when alarm starts/ is invoked at runtime 
		Color d = new Color((float) Math.random(), (float) Math.random(),(float) Math.random()); // Randomised colour each time 
        contentPane.setBackground(d);
        txtrHour.setText("");
	    txtrMinute.setText("");
	    textArea_sec.setText("");		
	}
	
	public void invokeAlarmAlert1(){ //// Flashes colour of background when alarm starts/ is invoked at runtime 
		// Color d = new Color((float) Math.random(), (float) Math.random(),(float) Math.random()); // Randomised colour each time 
        contentPane.setBackground(Color.red);
        txtrHour.setText("");
	    txtrMinute.setText("");
	    textArea_sec.setText("");		
	}
}