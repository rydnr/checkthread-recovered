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

//ClockLabel.java
//An extension of the JLabel class that listens to events from
//a Timer object to update itself with the current date & time.
//

import java.util.Date;
import java.awt.event.*;
import javax.swing.*;

import org.checkthread.annotations.*;

@ThreadConfined(ThreadName.EDT)
public class ClockLabel extends JLabel implements ActionListener {

	public ClockLabel() {
		super("" + new Date());
		Timer t = new Timer(1000, this);
		t.start();
	}

	@ThreadConfined(ThreadName.EDT)
	public void actionPerformed(ActionEvent ae) {
		setText((new Date()).toString());
	}
}
