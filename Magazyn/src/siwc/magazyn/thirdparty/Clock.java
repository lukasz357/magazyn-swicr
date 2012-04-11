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
import java.util.Calendar;

///////////////////////////////////////////////////////////// Clock
public class Clock extends JTextField {
	private static final long serialVersionUID = -6293204417639905310L;
	javax.swing.Timer m_t;

	// ================================================== constructor
	public Clock() {
		// ... Set some attributes.
		setColumns(6);
		setFont(new Font("sansserif", Font.PLAIN, 48));

		// ... Create a 1-second timer.
		m_t = new javax.swing.Timer(1000, new ClockTickAction());
		m_t.start(); // Start the timer
	}

	// ///////////////////////////////////////// inner class listener
	private class ClockTickAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ... Get the current time.
			Calendar now = Calendar.getInstance();
			int h = now.get(Calendar.HOUR_OF_DAY);
			int m = now.get(Calendar.MINUTE);
			int s = now.get(Calendar.SECOND);
			setText("" + h + ":" + m + ":" + s);
		}
	}
}