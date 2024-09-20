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

public class TestSharedPrimitive1 {
	
	// ERROR: Unsynchronized data shared across multiple threads 
	// in 'methodSharedWrite' and 'methodSharedReadWrite'. 
	// Possible options:
	// 1) Confine this data to a specific thread. See
	// ThreadConfinedData for more info.
	// 2) Synchronize this data using synchronization (e.g. locks). See
	// a Java reference on synchronization for more information.
	private int fSharedPrimitive = 1;

	// ERROR: See above
	volatile int fSharedVolatilePrimitive = 2;
	
	@ThreadSafe
	public void methodSharedWrite(int v) {
		// atomic putfield operation
		fSharedPrimitive = v;
		fSharedVolatilePrimitive = v;
	}

	@ThreadSafe
	public void methodSharedRead() {
		// atomic getfield operation
		System.out.println(fSharedPrimitive);
		System.out.println(fSharedVolatilePrimitive);
	}
	
	@ThreadSafe
	public void methodSharedReadWrite(int v) {
		// not atomic, getfield, operation, then putfield
		fSharedPrimitive+=v;
		fSharedVolatilePrimitive+=v;
	}
}
