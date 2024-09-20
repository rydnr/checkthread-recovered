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

import org.checkthread.annotations.*;

public class TestSharedPrimitive_Write_No_Lock {
	
	//ERROR: shared data accessed without synchronization
	private int fSharedPrimitive = 1;
	private volatile int fSharedVolatilePrimitive = 2;
	
	@ThreadSafe
	public void methodSharedWrite(int v) {
		// According to "Java Threads", the following is an atomic 
		// putfield operation for non-double/long primitive (page 42). 
		// Throw error anyway to keep the user model simple and 
		// not varying depending on primitive datatypes.
		fSharedPrimitive = v;
		fSharedVolatilePrimitive = v;
	}
}
