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
package org.checkthread.examples.javathreads.ch02.example7;

import java.util.*;

import org.checkthread.annotations.*;

import org.checkthread.examples.javathreads.ch02.CharacterEventHandler;
import org.checkthread.examples.javathreads.ch02.CharacterListener;
import org.checkthread.examples.javathreads.ch02.CharacterSource;

@ThreadConfined(ThreadName.MAIN)
public class RandomCharacterGenerator extends Thread implements CharacterSource {
    static char[] chars;
    static String charArray = "abcdefghijklmnopqrstuvwxyz0123456789";
    static {
        chars = charArray.toCharArray();
    }

    Random random;
    CharacterEventHandler handler;

    private volatile boolean done = false;
    final public static String THREAD_NAME = "javathreads.examples.ch02.example7.RandomCharacterGenerator";
    
    @ThreadConfined(ThreadName.EDT)
    public RandomCharacterGenerator() {
    	System.out.println("Random.const1: " + Thread.currentThread());
        random = new Random();
        handler = new CharacterEventHandler();
        this.setName("RandomCharacterGene");
    }

    @ThreadConfined(RandomCharacterGenerator.THREAD_NAME)
    public int getPauseTime() {
    	System.out.println("Random.getPauseTime: " + Thread.currentThread());
        return (int) (Math.max(1000, 5000 * random.nextDouble()));
    }

    @ThreadConfined(ThreadName.EDT)
    public void addCharacterListener(CharacterListener cl) {
    	System.out.println("Random.addCharacterListener: " + Thread.currentThread());
        handler.addCharacterListener(cl);
    }

    @ThreadConfined(ThreadName.EDT)
    public void removeCharacterListener(CharacterListener cl) {
        handler.removeCharacterListener(cl);
    }
    
    @ThreadConfined(RandomCharacterGenerator.THREAD_NAME)
    public void nextCharacter() {
    	System.out.println("Random.nextCharacter: " + Thread.currentThread());
        handler.fireNewCharacter(this,
                                (int) chars[random.nextInt(chars.length)]);
    }

    @ThreadConfined(RandomCharacterGenerator.THREAD_NAME)
    public void run() {
    	System.out.println("Random.run: " + Thread.currentThread());
        while (!done) {
            nextCharacter();
            try {
                Thread.sleep(getPauseTime());
            } catch (InterruptedException ie) {
                return;
            }
        }
    }

    @ThreadConfined(ThreadName.EDT)
    public void setDone() {
    	System.out.println("Random.setDone: " + Thread.currentThread());
        done = true;
    }
}
