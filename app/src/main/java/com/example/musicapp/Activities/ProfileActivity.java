package com.example.musicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.musicapp.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", ""); // "" là giá trị mặc định nếu không tìm thấy giá trị
        Log.d("asdsad", "username" + username);

        try {
            JSONObject jsonObject = new JSONObject(username);
            String firstName = jsonObject.getString("firstname");
            String lastName = jsonObject.getString("lastname");
            String email = jsonObject.getString("email");

            TextView view =  findViewById(R.id.textView19);
            view.setText("First Name: " + firstName);

            TextView view1 =  findViewById(R.id.textView20);
            view1.setText("Last Name: " + lastName);

            TextView view2 =  findViewById(R.id.textView21);
            view2.setText( "Email: " + email);

            // Sử dụng các giá trị firstName và lastName tùy ý
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}