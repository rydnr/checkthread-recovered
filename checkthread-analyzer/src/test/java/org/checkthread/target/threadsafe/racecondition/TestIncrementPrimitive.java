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
package org.checkthread.target.threadsafe.racecondition;

import org.checkthread.annotations.*;

public class TestIncrementPrimitive {

	volatile private int var1 =1;
	private Object obj;
	
	@ThreadSafe 
	public void method1A() {
		// ERROR, read/write operation and no synchronization
		// Ref: Java Threads, page 43
		// For now, checkthread will *not* detect read/write race conditions
		// due to the large amount of false positives
		var1++; 
	}

	@ThreadSafe 
	public void method1B() {
		//ERROR, write/read race condition.
		var1 = 2; 
		System.out.println(var1);
	}
	
	@ThreadSafe 
	public synchronized void method1C() {
		//OK, method is synchronized
		var1++; 
	}
	
	@ThreadSafe 
	public void method1D() {
		synchronized(this) {
		    //OK, method is synchronized
		    var1++; 
		}
	}

	@ThreadSafe 
	public void method1E() {
		// OK
		synchronized(this) {
			var1 = 2; 
		}
		synchronized(this) {
			System.out.println(var1);
		}
	}
	
	@ThreadSafe
	public void method2A() {
		obj = new Object(); // OK, write only 
	}
	
	@ThreadSafe
	public void method2B() {
		obj = new Object(); 
		System.out.println(obj); // ERROR, write/read operation
	}
	
	@ThreadSafe
	public void method2() {
		var1 = 3; // OK, write only operation
	}

	@ThreadSafe
	public void method3() {
		int foo = var1; // OK, read only operation
	}
	
	@NotThreadSafe(synchronize=Scope.INSTANCE)
	public void method21() {
		// OKAY, method declares that it requires synchronization 
		var1++; 
	}
}
