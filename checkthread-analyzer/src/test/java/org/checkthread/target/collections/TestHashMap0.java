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

import java.util.HashMap;

import org.checkthread.annotations.ThreadSafe;

public class TestHashMap0 {
	private final HashMap<String,Boolean> map = new HashMap<String,Boolean>();
	
	@ThreadSafe
	public void method1() {
		// okay, read only
	    map.get(null);
	    
	    // okay if there is already a mapping for key
	    // ambiguous, marking as thread safe
	    map.put(null,Boolean.TRUE);
	}

	@ThreadSafe
	public void method2() {
		// ERROR, modifying structure not thread safe
		map.putAll(null);
		map.remove(null);
		map.clear();
	}
}
