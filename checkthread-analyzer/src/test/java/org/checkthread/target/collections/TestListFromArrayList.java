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

import java.util.*;

public class TestListFromArrayList {

	private final List<Boolean> list1 = new ArrayList<Boolean>();
	private List<Boolean> list2 = new ArrayList<Boolean>();
	
	public TestListFromArrayList() {}
	
	@ThreadSafe
	public void method1() {
		// ERROR, list is an ArrayList
		list1.add(null);
	}
	
	@ThreadSafe
	public void method2() {
		// OK, field is not marked final, not enough information
		// in the future, it would be nice if checkthread could detect this bug
		// by doing a 2nd pass through and counting the number of putfields
		list2.add(null);
	}
}
