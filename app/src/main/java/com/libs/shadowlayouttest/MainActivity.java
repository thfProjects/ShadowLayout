package com.libs.shadowlayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.libs.shadowlayout.ShadowLayout;

public class MainActivity extends AppCompatActivity {

    ShadowLayout shadowlayout;
    Switch shadowswitch;
    SeekBar sbopacity;
    SeekBar sboffset;

    Switch.OnCheckedChangeListener onCheckListener = new Switch.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean isChecked){
            shadowlayout.setShadowed(isChecked);
            shadowlayout.invalidate();
        }
    };

    SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar == sbopacity) shadowlayout.setShadowOpacity(progress);
            else if(seekBar == sboffset) shadowlayout.setShadowOffset(progress);
            shadowlayout.invalidate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shadowlayout = (ShadowLayout)findViewById(R.id.shadowlayout);

        shadowswitch = (Switch)findViewById(R.id.shadowswitch);
        shadowswitch.setOnCheckedChangeListener(onCheckListener);

        sbopacity = (SeekBar)findViewById(R.id.opacity);
        sbopacity.setOnSeekBarChangeListener(seekBarListener);
        sboffset = (SeekBar)findViewById(R.id.offset);
        sboffset.setOnSeekBarChangeListener(seekBarListener);
    }
}
