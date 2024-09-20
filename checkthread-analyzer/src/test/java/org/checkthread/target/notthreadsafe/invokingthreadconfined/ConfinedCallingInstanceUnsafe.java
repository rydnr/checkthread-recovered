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
package org.checkthread.target.notthreadsafe.invokingthreadconfined;

import org.checkthread.annotations.*;

public class ConfinedCallingInstanceUnsafe {

	Inner fField;

	@ThreadConfined("mythread")
	public void methodThreadConfined1() {
		//OK, no shared data, no need to synchronize on instance
		Inner n = new Inner();
		n.methodThreadUnsafe();
	}	

	@ThreadConfined("mythread")
	public void methodThreadConfined2() {
		//OK, no shared data, no need to synchronize on instance
		Inner n = new Inner();
		n.methodThreadUnsafe();
	}
	
	@ThreadConfined("mythread")
	public void methodThreadConfined3() {
		//ERROR, shared data, must synchronize on instance
		fField.methodThreadUnsafe();
	}

	@ThreadConfined("mythread")
	public void methodThreadConfined4() {
		//ERROR, must synchronize on class
		Inner.methodThreadUnsafe2();
	}
	
	public static class Inner {
		@NotThreadSafe(synchronize=Scope.INSTANCE)
		public void methodThreadUnsafe() {
			System.out.println("Hello World");
		}		
		
		@NotThreadSafe(synchronize=Scope.CLASS)
		public static void methodThreadUnsafe2() {
			System.out.println("hello world");
		}
	}
}
