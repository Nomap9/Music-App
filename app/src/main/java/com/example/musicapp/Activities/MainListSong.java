package com.example.musicapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.example.musicapp.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainListSong extends AppCompatActivity {

    private RecyclerView rcvSong;
    private SongAdapter songAdapter;
    private ArrayList<Song> arraySong;
    private Button btnChangeLayout;

    private EditText editTextSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_song);

        rcvSong = findViewById(R.id.rcv_song);
        songAdapter = new SongAdapter(this, getFileSong());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvSong.setLayoutManager(linearLayoutManager);

        songAdapter.setData(getFileSong());
        rcvSong.setAdapter(songAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvSong.addItemDecoration(itemDecoration);

        editTextSearch = findViewById(R.id.editTextSearch);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần làm gì ở đây
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                List<Song> filteredSongs = new ArrayList<>();
                for (Song song : arraySong) {
                    if (song.getTitle().toLowerCase().contains(searchText) || song.getAuthor().toLowerCase().contains(searchText)) {
                        filteredSongs.add(song);
                    }
                }
                songAdapter.setData(filteredSongs);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Không cần làm gì ở đây
            }
        });


        btnChangeLayout = (Button)findViewById(R.id.btnPlayMusic);
        btnChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainListSong.this, PlaySong.class);
                startActivity(intent);
            }
        });
    }


    private List<Song> getFileSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Lạ lùng","Vũ", R.raw.la_lung,R.drawable.song6,0));
        arraySong.add(new Song("You are my crush","Quân AP", R.raw.youaremycrush, R.drawable.song1,1));
        arraySong.add(new Song("Waiting for you","Mono", R.raw.waitingforyou, R.drawable.song2,2));
        arraySong.add(new Song("Suýt nữa thì","Andiez", R.raw.suytnuathi,R.drawable.song3,3));
        arraySong.add(new Song("Bước qua nhau","Vũ", R.raw.buoc_qua_nhau, R.drawable.song6,4));
        arraySong.add(new Song("3107 full album","W/n", R.raw.nau, R.drawable.song4,5));
        arraySong.add(new Song("Bông hoa đẹp nhất","Quân AP", R.raw.bonghoadepnhat, R.drawable.song5,6));
        arraySong.add(new Song("Anh nhớ ra","Vũ", R.raw.anh_nho_ra, R.drawable.song6,7));
        return arraySong;
    }
}


