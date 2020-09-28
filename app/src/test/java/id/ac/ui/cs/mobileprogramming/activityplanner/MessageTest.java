package id.ac.ui.cs.mobileprogramming.activityplanner;

import org.junit.Test;

import static id.ac.ui.cs.mobileprogramming.activityplanner.MainActivity.EXTRA_MESSAGE;
import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void message_isCorrect() { assertEquals("MESSAGE", EXTRA_MESSAGE); }
}
