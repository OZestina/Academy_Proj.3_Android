package com.app.gitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 존재하는 layout
//        LinearLayout layoutSong = findViewById(R.id.tab1);
//        LinearLayout layoutArtist = findViewById(R.id.tab2);
//        LinearLayout layoutAlbum = findViewById(R.id.tab2Album);

        // layout 객체화
//        View songView = View.inflate(this, R.layout.song, null);
//        View artistView = View.inflate(this, R.layout.artist, null);
//        View albumView = View.inflate(this, R.layout.album, null);

//        layoutSong.addView(songView);
//        layoutArtist.addView(artistView);
//        layoutAlbum.addView(albumView);

        TabHost tabHost = findViewById(R.id.tab2host);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("1").setIndicator("1");
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("2").setIndicator("2");
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("3").setIndicator("3");
        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("4").setIndicator("4");
        TabHost.TabSpec tabSpec5 = tabHost.newTabSpec("5").setIndicator("5");

        tabSpec1.setContent(R.id.tab1);
        tabSpec2.setContent(R.id.tab2);
        tabSpec3.setContent(R.id.tab3);
        tabSpec4.setContent(R.id.tab4);
        tabSpec5.setContent(R.id.tab5);

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
        tabHost.addTab(tabSpec4);
        tabHost.addTab(tabSpec5);

//        Button b1 = songView.findViewById(R.id.tab2Button1);
//        ImageView i1 = songView.findViewById(R.id.tab2Image1);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i1.setImageResource(R.drawable.song2);
//            }
//        });
//
//        Button b2 = artistView.findViewById(R.id.tab2Button2);
//        ImageView i2 = artistView.findViewById(R.id.tab2Image2);
//        TextView t2 = artistView.findViewById(R.id.tab2Text1);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i2.setImageResource(R.drawable.joy2);
//                t2.setText("안녕 (Hello)");
//            }
//        });

    }
}