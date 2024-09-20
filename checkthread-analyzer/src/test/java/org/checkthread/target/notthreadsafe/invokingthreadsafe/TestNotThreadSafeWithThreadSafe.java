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
package org.checkthread.target.notthreadsafe.invokingthreadsafe;

import java.util.concurrent.locks.*;
import org.checkthread.annotations.*;

public class TestNotThreadSafeWithThreadSafe {

	// The caller of this method must synchronize so that
	// all calls to this class instance occur on the same thread
	@NotThreadSafe(synchronize=Scope.INSTANCE)
	public void notThreadSafeMethod() {
		methodThreadSafe(); // OK, calling thread safe method
	}
	
	@ThreadSafe
	public void methodThreadSafe() {
	   System.out.println("hello world");	
	}
	
	@ThreadSafe
	public void methodThreadSafe2() {
		notThreadSafeMethod(); // ERROR: calling not thread safe from thread safe
	}
	
	@ThreadSafe
	public synchronized void methodThreadSafe3() {
        notThreadSafeMethod(); // OK, method is synchronized
	}
	
	@ThreadSafe
	public void methodThreadSafe4() {
		synchronized(this) {
			notThreadSafeMethod(); // OK, call is synchronized
		}
	}

	@ThreadSafe
	public void methodThreadSafe5() {
		notThreadSafeMethod(); // ERROR, call is not synchronized
		synchronized(this) {
             System.out.println("Hello World");
		}
	}

	@ThreadSafe
	public void methodThreadSafe6() {
		synchronized(this) {
             System.out.println("Hello World");
		}	
		notThreadSafeMethod(); // ERROR, call is not synchronized
	}

	@ThreadSafe
	public void methodThreadSafe7() {
	     Lock  l= new ReentrantLock();
	     l.lock();
	     try {
	         notThreadSafeMethod(); // OK, 
	     } finally {
	         l.unlock();
	     }
	}
	
	@ThreadSafe
	public void methodThreadSafe8() {
	     Lock  l= new ReentrantLock();
	     l.lock();
	     try {
             System.out.println("Hello World");
	     } finally {
	         l.unlock();
	     }
         notThreadSafeMethod(); // ERROR, call is not synchronized
	}
		
}
