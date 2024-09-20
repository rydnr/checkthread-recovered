/*
Copyright (c) 2009 Joe Conti CheckThread.org

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/
package org.checkthread.examples.swing.example2;

//Example comes from "Java Swing, 2nd Edition"
//http://examples.oreilly.com/jswing2/code/

//ClockTest.java
//A demonstration framework for the Timer driven ClockLabel class

import javax.swing.*;
import java.awt.*;

import org.checkthread.annotations.*;

@ThreadConfined(ThreadName.EDT)
public class ClockTest extends JFrame {

	@ThreadConfined(ThreadName.EDT)
	public ClockTest() {
		super("Timer Demo");
		setSize(300, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ClockLabel clock = new ClockLabel();
		getContentPane().add(clock, BorderLayout.NORTH);
	}

	@ThreadConfined(ThreadName.MAIN)
	public static void main(String args[]) {
     SwingUtilities.invokeLater(new Runnable()
     {
     	@ThreadConfined(ThreadName.EDT)
     	public void run() {
     		ClockTest ct = new ClockTest();
     		ct.setVisible(true);
     	}
     });
	}
}

