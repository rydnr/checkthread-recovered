/*
Copyright (c) 2008 Joe Conti CheckThread.org

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

public class TestArrayList0 {

	private final ArrayList<Boolean> list1= new ArrayList<Boolean>();
	
	@ThreadSafe
	public void method1() {
		// okay, read only
	    list1.get(0);
	}

	@ThreadSafe
	public void method2() {
		// okay, not shared data
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		list.add(Boolean.TRUE);
	}
}
