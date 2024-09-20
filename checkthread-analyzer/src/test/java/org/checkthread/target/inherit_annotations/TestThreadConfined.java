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
package org.checkthread.target.inherit_annotations;

import javax.swing.JButton;
import javax.swing.border.Border;

import org.checkthread.annotations.ThreadConfined;
import org.checkthread.annotations.ThreadSafe;
import org.checkthread.annotations.NotThreadSafe;

public class TestThreadConfined extends JButton  {
	
	// OK
	// Implicit EDT should not throw error here
	public void setBorder(Border border) {}
	
	@ThreadConfined("foo")
	public void methodThreadConfined1() {}

	@ThreadConfined("foo")
	public void methodThreadConfined2() {}
	
	@ThreadConfined("foo")
	public void methodThreadConfined3() {}
	
	@ThreadConfined("foo")
	public void methodThreadConfined4() {}
	
	public class InnerClass extends TestThreadConfined {
		
		// ERROR
		@ThreadSafe
		public void methodThreadConfined1() {}

		// ERROR
		@NotThreadSafe
		public void methodThreadConfined2() {}
		
		// OK
		@ThreadConfined("foo")
		public void methodThreadConfined3() {}

		// ERROR
		@ThreadConfined("bar")
		public void methodThreadConfined4() {}		
	}
}
