package com.mayday.md;


import com.mayday.md.common.SoftKeyboard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SoftKeyboardTest {
    @Test
    public void shouldReturnSoftKeyboard() {
        assertNotNull(new SoftKeyboard());
    }
}
