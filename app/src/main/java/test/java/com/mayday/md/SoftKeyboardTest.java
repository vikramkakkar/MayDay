package main.java.com.mayday.md.


import main.java.com.mayday.md.common.SoftKeyboard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SoftKeyboardTest {
    @Test
    public void shouldReturnSoftKeyboard() {
        assertNotNull(new SoftKeyboard());
    }
}
