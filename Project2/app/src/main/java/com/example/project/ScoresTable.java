package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ScoresTable extends AppCompatActivity {

    DatabaseReference myRef;
    String userName;
    String gameTime;
    private ListView listView;
    private TextView textViewName;
    private TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list);
        //FirebaseDatabase database = FirebaseDatabase.getInstance("https://project-b9b73-default-rtdb.firebaseio.com/");
        Intent intent2 = getIntent();
        userName = intent2.getExtras().getString("UserName");
        gameTime = intent2.getExtras().getString("UserTime");
        textViewName = findViewById(R.id.textViewName);
        textViewTime = findViewById(R.id.textViewTime);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if(gameTime!=null && userName!=null) {
            myRef = database.getReference("users").push();
            User user = new User(gameTime, userName);
            myRef.setValue(user);
        }

        ArrayList<User> users = new ArrayList<User>();
// Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(ScoresTable.this, users);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

// Attach the adapter to a ListView

        database = FirebaseDatabase.getInstance("https://project-b9b73-default-rtdb.firebaseio.com/");
        myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                adapter.clear();

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User currentUser = new User();
                    currentUser = userSnapshot.getValue(User.class);
                    adapter.add(currentUser);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ScoresTable.this, "error reading from firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.back) {
            Intent i=new Intent(ScoresTable.this, GameActivity.class);
            i.putExtra("UserName", userName);
            startActivity(i);
        }
        if(id==R.id.exit)
        {
            Intent intent = new Intent(ScoresTable.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        return true;
    }
}
