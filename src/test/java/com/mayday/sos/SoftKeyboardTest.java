package com.mayday.sos;


import com.mayday.sos.common.SoftKeyboard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SoftKeyboardTest {
    @Test
    public void shouldReturnSoftKeyboard() {
        assertNotNull(new SoftKeyboard());
    }
}
