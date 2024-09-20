/*
Copyright (c) 2008 Joe Conti

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

package org.checkthread;

import java.util.*;

import junit.framework.TestCase;
import org.checkthread.main.ICheckThreadError;

import org.checkthread.examples.helloworld.*;
import org.checkthread.examples.swing.example1.TestSwingExample1;
import org.checkthread.examples.swing.example2.ActionExample;
import org.checkthread.examples.swing.example2.ClockLabel;
import org.checkthread.examples.swing.example2.ClockTest;
import org.checkthread.examples.swing.example2.SimpleList;
import org.checkthread.examples.thread.example1.SimpleThread;
import org.checkthread.examples.thread.example2.DeadlockExample;
import org.checkthread.examples.thread.example4.DeadlockExample2;

public class TestCaseExamples extends TestCase {

	protected void setUp() {}
	protected void tearDown() {}

	public void testHelloWorld() {
		TestParseHandler handler = TestUtil.parseClassHelper(HelloWorldThread.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testHelloWorldClassAnnotation() {
		TestParseHandler handler = TestUtil.parseClassHelper(HelloWorldThreadClassAnnotation.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testHelloWorldWithBug() {
		TestParseHandler handler = TestUtil.parseClassHelper(HelloWorldThreadWithBug.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 1;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testHelloWorldWithBugFixed() {
		TestParseHandler handler = TestUtil.parseClassHelper(HelloWorldThreadWithBugFixed.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testActionExample() {
		TestParseHandler handler = TestUtil.parseClassHelper(ActionExample.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testClockLabel() {
		TestParseHandler handler = TestUtil.parseClassHelper(ClockLabel.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testClockTest() {
		TestParseHandler handler = TestUtil.parseClassHelper(ClockTest.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testSimpleList() {
		TestParseHandler handler = TestUtil.parseClassHelper(SimpleList.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;

		
		for(ICheckThreadError error : list) {
			error.printErr();
		}
		assertEquals(expectedValue, actualValue);
	}

	public void testSimpleThread() {
		TestParseHandler handler = TestUtil.parseClassHelper(SimpleThread.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testDeadlock() {
		TestParseHandler handler = TestUtil.parseClassHelper(DeadlockExample.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testDeadlock2() {
		TestParseHandler handler = TestUtil.parseClassHelper(DeadlockExample2.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testSwingExample1() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestSwingExample1.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify that there are no thread policy errors
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
}

