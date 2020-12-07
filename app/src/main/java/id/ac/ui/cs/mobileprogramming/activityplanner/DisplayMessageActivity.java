package id.ac.ui.cs.mobileprogramming.activityplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.activityplanner.R;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        boolean isPrime = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE, false);

        String message;
        if (isPrime) {
            message = "Prime number";
        } else {
            message = "Not Prime number";
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}