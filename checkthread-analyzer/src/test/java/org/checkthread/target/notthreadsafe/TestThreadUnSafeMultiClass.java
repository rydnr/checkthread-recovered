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

import org.checkthread.annotations.*;

public class TestThreadUnSafeMultiClass {

	class CallingClass1 {
		@NotThreadSafe(synchronize=Scope.INSTANCE)
		public void method1() {
			TestBasicInstance lib = new TestBasicInstance();
			lib.threadUnsafeInstance(); // OK, method1 has a smaller sync requirement	
		}
	}

	class CallingClass2 {
		@NotThreadSafe(synchronize=Scope.INSTANCE)
		public void method1() {
			TestBasicInstance lib = new TestBasicInstance();
			lib.threadUnsafeInstance(); // OK, method1 has a smaller sync requirement	
		}
	}

	class CallingClass3 {

		@NotThreadSafe(synchronize=Scope.INSTANCE)
		public void method1() {
			CallingClass1 c1 = new CallingClass1();
			c1.method1(); // OK, no shared data

			CallingClass2 c2 = new CallingClass2();
			c2.method1(); //OK, no shared data
		}
	}
}
