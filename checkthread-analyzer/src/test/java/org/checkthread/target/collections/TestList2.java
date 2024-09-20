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

import java.util.ArrayList;
import java.util.List;

public class TestList2 {

	private final List<Boolean> list1 = new ArrayList<Boolean>();
	
	@ThreadConfined("thread1")
	public void method1a() {
		list1.add(Boolean.TRUE);
	}

	@ThreadConfined("thread1")
	public void method1b() {
		// OK, same ThreadConfined name policy
		list1.add(Boolean.TRUE);
	}


	@ThreadConfined("thread2")
	public void method1c() {
		// OK, read only method
		list1.get(0);
	}
	
	@ThreadConfined("thread2") 
	public void method2() {
		// Should be error, for now, checkthread will ignore java.util.List
		list1.add(Boolean.TRUE);
	}
	
	
	@ThreadConfined("thread2") 
	public void method3() {
		//OK, synchronized
		synchronized(list1) {
		    list1.add(Boolean.TRUE);
		}
	}
	
}
