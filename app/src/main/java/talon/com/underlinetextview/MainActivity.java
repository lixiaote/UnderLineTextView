package talon.com.underlinetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import talon.com.underlineText.UnderLineTextView;


public class MainActivity extends AppCompatActivity {
private UnderLineTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(UnderLineTextView)findViewById(R.id.tv_underline);

    }
}
