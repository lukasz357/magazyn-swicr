package siwc.magazyn.thirdparty;

// File:   textclock/Clock.java
//Description: This implements a text clock by subclassing
//           JTextField, changing its appearance and
//           adding a timer to update itself.
//           Regarding subclassing JTextField
//           PROS: It's simple.
//                 All JTextField methods are available
//                     to the user, so they can easily
//                     customize it (fonts, colors, ...);
//           CONS: It can't become more complicated, eg,
//                     to add buttons, etc. because the
//                     users may already be using the
//                     JTextField methods.
//Author: Fred Swartz,
//Date:   15 Feb 2005
//Possible Enhancements: 
//      Appearance: center, leading zeros, uneditable, 
//      Function:   12/24 hour, alarm, timer, stop and start.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.Calendar;

///////////////////////////////////////////////////////////// Clock
public class Clock extends JTextField {
	private static final long serialVersionUID = -6293204417639905310L;
	javax.swing.Timer m_t;

	private int h;
	private int m;
	private int s;
	// ================================================== constructor
	public Clock() {
		// ... Set some attributes.
		setColumns(6);
		setFont(new Font("sansserif", Font.PLAIN, 48));
		setEditable(false);
		setBackground(Color.WHITE);
		h = 0;
		m = 0;
		s = 0;
		setText(Clock.formatTime(h, m, s));
		// ... Create a 1-second timer.
		m_t = new javax.swing.Timer(1000, new ClockTickAction());
	}

	// ///////////////////////////////////////// inner class listener
	private class ClockTickAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ... Get the current time.
//			Calendar now = Calendar.getInstance();
//			int h = now.get(Calendar.HOUR_OF_DAY);
//			int m = now.get(Calendar.MINUTE);
//			int s = now.get(Calendar.SECOND);

			s++;
			if(s > 59){
				s = 0;
				m++;
				if(m > 59){
					m = 0;
					h++;
					if(h > 23){
						h = 0;
					}
				}
			}
			
			setText(Clock.formatTime(h, m, s));
		}
	}
	
	public static String formatTime(int h, int m, int s){
		String hour;
		String minute;
		String second;
		hour = (h >= 0 && h <= 9) ? "0"+h : ""+h;
		minute = (m >= 0 && m <= 9) ? "0"+m : ""+m;
		second = (s >= 0 && s <= 9) ? "0"+s : ""+s;
		
		return hour + ":" + minute + ":" + second;
	}
	
	public void rescaleTime(int miliseconds){
		if(m_t.isRunning()){
			m_t.stop();
			
			m_t = new javax.swing.Timer(miliseconds, new ClockTickAction());
			m_t.start(); // Start the timer
		}
		else{
			m_t = new javax.swing.Timer(miliseconds, new ClockTickAction());
		}
	}
	
	public int getTimeIntValue(){
		return h * 86400 + m * 3600 + s;
	}
	
	public void startClock(){
		m_t.start(); // Start the timer
	}
	
	public void stopClock(){
		m_t.stop();
	}
}