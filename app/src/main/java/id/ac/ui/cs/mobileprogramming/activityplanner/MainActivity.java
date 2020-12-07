package id.ac.ui.cs.mobileprogramming.activityplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.activityplanner.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String numStr = editText.getText().toString();
        int number = Integer.parseInt(numStr);
        boolean isPrime = isPrime(number);
        intent.putExtra(EXTRA_MESSAGE, isPrime);
        startActivity(intent);
    }

    public native boolean isPrime(int number);

    static {
        System.loadLibrary("native-lib");
    }
}