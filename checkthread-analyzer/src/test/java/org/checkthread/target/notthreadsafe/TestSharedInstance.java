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

public class TestSharedInstance {
	public static TestBasicInstance sField;
}

class CallingClassINSTANCE1 {
	@NotThreadSafe(synchronize=Scope.INSTANCE)
	public void method1() {
		//ERROR: Method must synchronize calls to method1()
		//since sLibrary is a public field and can be shared
		//among many classes.
		TestSharedInstance.sField.threadUnsafeInstance();
		
		// Error (see above)
		TestSharedInstance.sField.threadUnsafeClass();
		
		// Error (see above)
		TestSharedInstance.sField.threadUnsafeLibrary();
	}
}

class CallingClassINSTANCE2 {
	@NotThreadSafe(synchronize=Scope.INSTANCE)
	public void method1() {
		//ERROR: Method must synchronize calls to method
		//since sLibrary is a public field and can be shared 
		//among many classes.
		TestSharedInstance.sField.threadUnsafeInstance();
	}
}

class CallingClassINSTANCE3 {
	@ThreadSafe
	public synchronized void method1() {
	   CallingClassINSTANCE1 c = new CallingClassINSTANCE1();
	   c.method1(); // OK
	}	
	
	@ThreadSafe
	public synchronized void method2() {
	   CallingClassINSTANCE2 c = new CallingClassINSTANCE2();
	   c.method1(); // OK
	}
}
