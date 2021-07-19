package com.example.app_dari.Interest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_dari.Login.LoginActivity;
import com.example.app_dari.MainActivity;
import com.example.app_dari.R;
import com.example.app_dari.RetrofitClient;
import com.example.app_dari.SetProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interests_Activity extends AppCompatActivity {

    private RecyclerView my_interests;
    InterestAdapter adapter;
    private ArrayList<Interests> interests;
    private List<String> str_interests;
    private int position=0;
    private String myId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        my_interests =findViewById(R.id.iterests_view);
        interests = new ArrayList<>();
        str_interests = new ArrayList<>();

        adapter = new InterestAdapter();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        my_interests.setLayoutManager(layoutManager);
        my_interests.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        ToggleButton A = (ToggleButton) findViewById(R.id.A);
        ToggleButton B = (ToggleButton) findViewById(R.id.B);
        ToggleButton C = (ToggleButton) findViewById(R.id.C);
        ToggleButton D = (ToggleButton) findViewById(R.id.D);
        ToggleButton E = (ToggleButton) findViewById(R.id.E);
        ToggleButton F = (ToggleButton) findViewById(R.id.F);
        ToggleButton G = (ToggleButton) findViewById(R.id.G);
        ToggleButton H = (ToggleButton) findViewById(R.id.H);
        Interests a = new Interests("러닝",R.drawable.running);
        Interests b = new Interests("게임",R.drawable.game);
        Interests c = new Interests("자동차",R.drawable.car);
        Interests d = new Interests("빵만들기",R.drawable.baking);
        Interests e = new Interests("기차",R.drawable.train);
        Interests f = new Interests("식당투어",R.drawable.eat);
        Interests g = new Interests("영화",R.drawable.movie);
        Interests h = new Interests("자전거",R.drawable.cycle);

        Intent intent = getIntent();
        myId = intent.getExtras().getString("myId");





        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(A.isChecked()==true){

                    position++;
                    interests.add(a);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("러닝");

                }
                else{
                    position--;
                    interests.remove(a);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("러닝"));
                }

            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(B.isChecked()==true){
                    interests.add(b);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("게임");
                }
                else{
                    interests.remove(b);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position--;
                    str_interests.remove(String.valueOf("게임"));
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C.isChecked()==true){
                    interests.add(c);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    position++;
                    str_interests.add("자동차");
                }
                else{
                    interests.remove(c);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("자동차"));
                    position--;
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(D.isChecked()==true){
                    interests.add(d);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("빵만들기");
                    position++;
                }
                else{
                    interests.remove(d);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("빵만들기"));
                    position--;
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E.isChecked()==true){
                    interests.add(e);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("기차");
                    position++;
                }
                else{
                    interests.remove(e);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("기차"));
                    position--;
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F.isChecked()==true){
                    interests.add(f);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("식당투어");
                    position++;
                }
                else{
                    interests.remove(f);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("식당투어"));
                    position--;
                }
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(G.isChecked()==true){
                    interests.add(g);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("영화");
                    position++;
                }
                else{
                    interests.remove(g);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("영화"));
                    position--;
                }
            }
        });
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(H.isChecked()==true){
                    interests.add(h);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.add("자전거");
                    position++;
                }
                else{
                    interests.remove(h);
                    adapter.setting(interests);
                    my_interests.setAdapter(adapter);
                    my_interests.scrollToPosition(adapter.getItemCount()-1);
                    str_interests.remove(String.valueOf("자전거"));
                    position--;
                }
            }
        });
        String[] result_interests = str_interests.toArray(new String[0]);

        ImageButton change = (ImageButton)findViewById(R.id.change_text);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_Activity.this, Interests_text_Activity.class);
                intent.putExtra("interests", (Serializable) str_interests);
                startActivity(intent);
            }
        });
        ImageButton next = (ImageButton)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>=3 && position<6) {
                    String[] interests = str_interests.toArray(new String[str_interests.size()]);
                    Intent intent = new Intent(Interests_Activity.this, SetProfile.class);
                    intent.putExtra("interests", interests);
                    intent.putExtra("myId",myId);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Interests_Activity.this, "3~5개의 관심사를 설정해주세요.", Toast.LENGTH_LONG).show();
                }
            }

        });

        ImageButton back = (ImageButton)findViewById(R.id.interests_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Interests_Activity.this, LoginActivity.class);
                startActivity(intent);
                Interests_Activity.this.finish();
            }
        });
    }

}
