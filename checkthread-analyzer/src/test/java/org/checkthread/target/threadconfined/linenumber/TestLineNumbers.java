/*
Copyright (c) 2008 Joe Conti

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

package org.checkthread.target.threadconfined.linenumber;

import org.checkthread.annotations.*;

@ThreadConfined(ThreadName.MAIN)
public class TestLineNumbers {
	
    public static void m1() { boo(); } // line 33
    
    public static void m2() { 
    	boo(); // line 36 
    } 
    
    public static void m3() {
    	
    	boo(); // line 41
    	
    }

    public static void m4() {
    	System.out.println("");
    	boo(); // line 47    	
    }

    public static void m5() {
    	System.out.println("");
    	boo(); // line 52    	
    	System.out.println("");
    }

    // line 58
    public static void m6() {
    	System.out.println(""); boo();System.out.println("");
    }
    
    @ThreadConfined("foo")
    public static void boo() {}
}
