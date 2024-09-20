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
package org.checkthread.target.deadlocks;

import org.checkthread.annotations.*;

public class TestSimple2 {

	private Object f1 = new Object();
	private Object f2 = new Object();
	
	@ThreadSafe
	public void method1A() {	
		System.out.println("hello world");
		// ERROR: locking loop wrt method2
		synchronized(f1) {
			System.out.println("hello world");
            method1B();
		}
	}
	
	@ThreadSafe
	public void method1B() {
		System.out.println("hello world");
		method1C();
	}
	
	@ThreadSafe
	public void method1C() {
		System.out.println("hello world");
		synchronized(f2) {
		    System.out.println("Hello World");	
		}
	}
	
	@ThreadSafe
	public void method2A() {		
		// ERROR: locking loop wrt method2
		synchronized(f2) {
            method2B();
		}
	}
	
	@ThreadSafe
	public void method2B() {
		method2C();
	}
	
	@ThreadSafe
	public void method2C() {
		synchronized(f1) {
		    System.out.println("Hello World");	
		}
	}
}
