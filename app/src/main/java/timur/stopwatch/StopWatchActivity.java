package timur.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class StopWatchActivity extends AppCompatActivity {
private int second=0;
private boolean running;
private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        if(savedInstanceState!=null){
            second= savedInstanceState.getInt("second");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
       RunTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("second",second);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    protected void onResume(){
        super.onResume();
        wasRunning=running;
        running=false;
    }
        @Override
    protected void onPause(){
        super.onPause();
        if(wasRunning) running=true;
    }
    public void onClickStart(View view){
        running=true;
    }
    public void onClickStop(View view){
        running=false;
    }
    public void onClickReset(View view){
        running=false;
        second=0;
    }
    private void RunTimer(){
        final TextView timeview=(TextView)findViewById(R.id.timeView);
final Handler handler=new Handler();
handler.post(new Runnable() {
    @Override
    public void run() {
        int hours=second/3600;
        int minutes=(second%3600)/60;
        int sec=second%60;
        String time=String.format("%d:%02d:%02d",hours, minutes, sec);
        timeview.setText(time);
        if (running) {
            second++;
        }
        handler.postDelayed(this,1000);
    }
});



    }
}
