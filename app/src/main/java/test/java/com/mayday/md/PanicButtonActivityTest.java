package com.mayday.md;

import android.view.ViewGroup;


import org.hamcrest.core.Is;
import com.mayday.md.CalculatorActivity;
import com.mayday.md.alert.PanicAlert;
import com.mayday.md.test.support.PanicButtonRobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowViewGroup;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(PanicButtonRobolectricTestRunner.class)
public class PanicButtonActivityTest {
    @Mock
    private PanicAlert mockPanicAlert;
    private ShadowViewGroup alertStatusStrip;
    private CalculatorActivity activity;

    @Before
    public void setUp() {
        initMocks(this);
        activity = new CalculatorActivity() {
            @Override
            PanicAlert getPanicAlert() {
                return mockPanicAlert;
            }
        };
        activity.onCreate(null);
        activity.onPostCreate(null);
        ViewGroup alertStatusStripOrig = (ViewGroup) activity.findViewById(R.id.alert_status_strip);
        alertStatusStrip = shadowOf(alertStatusStripOrig);
    }


    @Test
    public void shouldShowRedAlertStripWhenAlertStatusIsActive(){
    	mockPanicAlert.activate();
    	when(mockPanicAlert.isActive()).thenReturn(true);
        activity.onResume();
//        assertThat(alertStatusStrip.getBackgroundColor(), Is.is(Robolectric.application.getResources().getColor(R.color.active_color)));
    }

    @Test
    public void shouldShowAmberAlertStripWhenAlertStatusIsStandby(){
        when(mockPanicAlert.isActive()).thenReturn(false);
        activity.onResume();
        assertThat(alertStatusStrip.getBackgroundColor(), Is.is(Robolectric.application.getResources().getColor(R.color.standby_color)));
    }
}
