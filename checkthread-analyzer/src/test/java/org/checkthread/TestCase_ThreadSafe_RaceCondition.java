/*
Copyright (c) 2009 Joe Conti

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

import junit.framework.*;

import org.checkthread.main.ICheckThreadError;
import org.checkthread.target.threadsafe.racecondition.*;

public class TestCase_ThreadSafe_RaceCondition extends TestCase {
	protected void setUp() {}

	protected void tearDown() {}

	public void test0() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestIncrementPrimitiveBasic.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();
		
		// Verify
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void testConstructor() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestIncrementPrimitiveConstructor.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();
		
		// Verify
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}

	public void testConstructorStaticField() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestIncrementStaticPrimitiveConstructor.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();
		
		// Verify
		int actualValue = list.size();
		int expectedValue = 1; 
		assertEquals(expectedValue, actualValue);
	}
	
	public void test1() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestIncrementPrimitive.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify
		int actualValue = list.size();
		int expectedValue = 2;
		assertEquals(expectedValue, actualValue);
	}
	
	public void test2() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestReadPrimitive.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify 
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
	
	public void test3() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestReadWritePrimitive1.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		
		// Verify
		int actualValue = list.size();
		int expectedValue = 3;
		assertEquals(expectedValue, actualValue);
	}
	
	public void test4() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestReadWritePrimitive2.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify 
		int actualValue = list.size();
		int expectedValue = 1;
		assertEquals(expectedValue, actualValue);
	}
	
	public void test5() {
		TestParseHandler handler = TestUtil.parseClassHelper(TestWritePrimitive.class);
		ArrayList<ICheckThreadError> list = handler.getThreadPolicyErrors();

		// Verify
		int actualValue = list.size();
		int expectedValue = 0;
		assertEquals(expectedValue, actualValue);
	}
}

