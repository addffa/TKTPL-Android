package id.ac.ui.cs.mobileprogramming.activityplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    private long startTime = 0;
    private long deltaTime = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void onClickStart(View v) {
        running = true;
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis() - deltaTime;
        }
    }

    public void onClickStop(View v) {
        running = false;
    }

    public void onClickReset(View v) {
        running = false;
        startTime = 0;
        deltaTime = 0;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    deltaTime = System.currentTimeMillis() - startTime;
                }
                long hours = (deltaTime / 1000) / 3600;
                long minutes = ((deltaTime / 1000) % 3600) / 60;
                long secs = (deltaTime / 1000) % 60;
                long millis = (deltaTime / 10) % 100;
                String time = String.format(
                        Locale.getDefault(),
                        "%d:%02d:%02d:%02d",
                        hours,
                        minutes,
                        secs,
                        millis
                );
                timeView.setText(time);
                handler.postDelayed(this,10);
            }
        });
    }
}