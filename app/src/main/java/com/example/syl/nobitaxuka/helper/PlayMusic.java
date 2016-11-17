package com.example.syl.nobitaxuka.helper;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Ngoc_MiikoDesu on 11/2/2016.
 */

public class PlayMusic {
    public static void playMusic(Context context, String name) {
        MediaPlayer mp3 = MediaPlayer.create(context, context.getResources().getIdentifier(name, "raw", context.getPackageName()));
        mp3.start();
        mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}
