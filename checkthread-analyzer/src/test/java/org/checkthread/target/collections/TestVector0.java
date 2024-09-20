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
package org.checkthread.target.collections;

import org.checkthread.annotations.*;

import java.util.Vector;
import java.util.Iterator;
import java.util.ListIterator;

public class TestVector0 {

	private final Vector<Boolean> list1= new Vector<Boolean>();

	@ThreadSafe
	public void method1() {
		// okay, vector is synchronized
		list1.get(0);
		list1.add(Boolean.TRUE);
		list1.addElement(Boolean.TRUE);
		list1.clear();
		list1.addAll(null);
		list1.contains(null);
		list1.containsAll(null);
		list1.elements();
		list1.setSize(0);
	}

	@ThreadSafe
	public void method2() {
		// okay, not shared data
		Vector<Boolean> list = new Vector<Boolean>();
		list.add(Boolean.TRUE);
	}

	@ThreadSafe
	public void method3() {
		// ERROR, using an iterator is not thread safe
		Iterator i = list1.iterator();
		
		// ERROR, not thread safe
		ListIterator i2 = list1.listIterator();
		
		// ERROR, not thread safe
		ListIterator i3 = list1.listIterator(0);	
	}
}
