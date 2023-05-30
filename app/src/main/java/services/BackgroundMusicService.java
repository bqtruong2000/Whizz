package services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.quizchic.whizz.R;

import java.security.Provider;

public class BackgroundMusicService extends Service {
    MediaPlayer sbg;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}
    @Override
    public int onStartCommand(Intent intent, int flag, int startID){
        Log.d("mylog","Starting playing");
        sbg = MediaPlayer.create(this, R.raw.soundbackground);
        sbg.start();
        return super.onStartCommand(intent, flag, startID);
    }
     @Override
    public boolean stopService(Intent name){
        return super.stopService(name);
     }
     @Override
    public void onDestroy(){
        super.onDestroy();
        sbg.stop();
        sbg.release();
        sbg = null;
    }
}
