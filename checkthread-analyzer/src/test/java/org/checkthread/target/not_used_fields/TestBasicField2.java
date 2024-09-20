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
package org.checkthread.target.not_used_fields;

import org.checkthread.annotations.*;

public class TestBasicField2 implements Runnable {

	private static class MyInnerClass {	
		public MyInnerClass() {
			System.out.println("MyInnerClass: " + Thread.currentThread());
		}
		
		public void finalize() {
			System.out.println("finalize: " + Thread.currentThread());
		}
	}

	private static class MyInnerClass2 {
		private static MyInnerClass foo = new MyInnerClass();
		private MyInnerClass foo2 = new MyInnerClass();

		public MyInnerClass2() {
			MyInnerClass f= new MyInnerClass();
			f = null;
			System.gc();
		}
	}

	@ThreadConfined("testThread")
	public void run() {
		System.out.println("run: " + Thread.currentThread());
		new MyInnerClass2();
	}
	
	@ThreadConfined(ThreadName.MAIN)
	public static void main(String[] args) {
		MyInnerClass f= new MyInnerClass();
		f = null;
		System.gc();
		Thread t = new Thread(new TestBasicField2());
		t.start();
	}
}
