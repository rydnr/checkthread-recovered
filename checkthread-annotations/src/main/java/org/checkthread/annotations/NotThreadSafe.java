/*
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

package org.checkthread.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for methods and classes which can not be
 * safely executed from concurrent threads.
 * 
 * This annotation is inspired by the book
 * "Java Concurrency In Practice" by Brian Goetz
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,
	     ElementType.CONSTRUCTOR,
	     ElementType.TYPE})
public @interface NotThreadSafe {
	public final static String UNDEFINED = "UNDEFINED";
	public final static int INDEX_UNDEFINED = -1;
	
	/**
	 * @deprecated
	 */
	@Deprecated
	Scope synchronize() default Scope.UNDEFINED;
	
	/**
	 * @deprecated
	 */
	@Deprecated
	String fieldName() default UNDEFINED;
	
	/**
	 * @deprecated
	 */
	@Deprecated
	boolean suppressErrors() default false;
	
	/**
	 * @deprecated
	 */
	@Deprecated
	int[] paramIndex() default INDEX_UNDEFINED;
}
