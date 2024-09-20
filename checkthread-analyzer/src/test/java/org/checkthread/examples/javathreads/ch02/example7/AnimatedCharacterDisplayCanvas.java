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

import java.awt.*;

import org.checkthread.annotations.*;

import org.checkthread.examples.javathreads.ch02.CharacterDisplayCanvas;
import org.checkthread.examples.javathreads.ch02.CharacterEvent;
import org.checkthread.examples.javathreads.ch02.CharacterListener;
import org.checkthread.examples.javathreads.ch02.CharacterSource;

@ThreadConfined(ThreadName.MAIN)
public class AnimatedCharacterDisplayCanvas extends CharacterDisplayCanvas implements CharacterListener, Runnable {

	final public static String THREAD_NAME = "javathreads.examples.ch02.example7.AnimatedCharacterDisplayCanvas";

    private volatile boolean done = false;
    private int curX = 0;

    @ThreadConfined(ThreadName.EDT)
    public AnimatedCharacterDisplayCanvas() {
    	System.out.println("Animated.const2: " + Thread.currentThread());
    }
    
    @ThreadConfined(ThreadName.EDT)
    public AnimatedCharacterDisplayCanvas(CharacterSource cs) {
        super(cs);
        System.out.println("Animated.const2: " + Thread.currentThread());
    }

    @ThreadConfined(RandomCharacterGenerator.THREAD_NAME)
    public synchronized void newCharacter(CharacterEvent ce) {
    	System.out.println("Animated.newCharacter: " + Thread.currentThread());
        curX = 0;
        tmpChar[0] = (char) ce.character;
        repaint();
    }

    @ThreadConfined(ThreadName.EDT)
    protected synchronized void paintComponent(Graphics gc) {
    	System.out.println("Animated.paintComponent: " + Thread.currentThread());
        Dimension d = getSize();
        gc.clearRect(0, 0, d.width, d.height);
        if (tmpChar[0] == 0)
            return;
        int charWidth = fm.charWidth(tmpChar[0]);
        gc.drawChars(tmpChar, 0, 1,
                     curX++, fontHeight);
    }

    @ThreadConfined(AnimatedCharacterDisplayCanvas.THREAD_NAME)
    public void run() {
    	System.out.println("Animated.run: " + Thread.currentThread());
        while (!done) {
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                return;
            }
        }
    }

    @ThreadConfined(ThreadName.EDT)
    public void setDone(boolean b) {
    	System.out.println("Animated.setDone: " + Thread.currentThread());
        done = b;
    }
}
