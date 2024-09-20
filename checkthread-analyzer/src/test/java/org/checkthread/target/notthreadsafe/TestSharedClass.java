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
package org.checkthread.target.notthreadsafe;

import java.util.concurrent.locks.*;

import org.checkthread.annotations.*;

public class TestSharedClass {

	static class CallingClass1 {
		@NotThreadSafe(synchronize=Scope.CLASS)
		public static void methodThreadUnsafeClass() {
			TestBasicStatic.threadUnsafeClass();
		}
	}

	static class CallingClass2 {
		@NotThreadSafe(synchronize=Scope.CLASS)
		public static void methodThreadUnsafeClass() {
			TestBasicStatic.threadUnsafeClass();
		}
	}

	class CallingClass3 {

		private final ReentrantLock lock = new ReentrantLock();
		
		@ThreadSafe
		public void method1() {
			synchronized (CallingClass1.class) {
				synchronized (this) {
				     CallingClass1.methodThreadUnsafeClass(); // OK
				}
			}
		}

		@ThreadSafe
		public void method2() {
			synchronized (CallingClass2.class) {
				CallingClass2.methodThreadUnsafeClass(); // OK
			}
		}
		
		@ThreadSafe
		public void method3() {
		     lock.lock();  // block until condition holds
		     try {
		    	 CallingClass2.methodThreadUnsafeClass(); // OK  
		     } finally {
		       lock.unlock();
		     }
		}

		@ThreadSafe
		public void method3A() {
		     lock.lock();   
		     CallingClass2.methodThreadUnsafeClass(); // OK  
		     lock.unlock();
		}
		
		@ThreadSafe
		public void method3B() {
		     CallingClass2.methodThreadUnsafeClass(); // ERROR 
		     lock.lock(); 
		     lock.unlock();
		}

		@ThreadSafe
		public void method3C() {
		     lock.lock(); 
		     lock.unlock();
		     CallingClass2.methodThreadUnsafeClass(); // ERROR 
		}

		
		@ThreadSafe
		public void methodYY() {
			synchronized (CallingClass2.class) {
               System.out.println("Hello World");
			}
			CallingClass2.methodThreadUnsafeClass(); // ERROR
		}

		@ThreadSafe
		public void methodXX() {
			CallingClass2.methodThreadUnsafeClass(); // ERROR 
		}
	}
}
