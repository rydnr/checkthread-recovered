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
package org.checkthread.target.threadsafe.racecondition.not_used;

import org.checkthread.annotations.ThreadSafe;

public class TestSharedPrimitive_Write_Multiple_Lock_External {

	// ERROR 
	// Access to shared data is synchronized
	// with different locks
	private int fSharedPrimitive = 1;
	private volatile int fSharedVolatilePrimitive = 2;
	
	// Locks
	private Object fLock1 = new Object();
	private Object fLock2 = new Object();
	
	@ThreadSafe
	public void methodSharedWrite1(int v) {
		synchronized (fLock1) {
			// atomic putfield operation
			fSharedPrimitive = v;
			fSharedVolatilePrimitive = v;
		}
	}
	
	@ThreadSafe
	public void methodSharedWrite2(int v) {
		synchronized (fLock2) {
			// atomic putfield operation
			fSharedPrimitive = v;
			fSharedVolatilePrimitive = v;
		}
	}
}
