package com.example.musicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

//    String api = "https://roomradar.onrender.com/api/v1/auth/register";
    String api = "https://be-prm-v4.onrender.com/api/v1/auth/register";
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button Register = findViewById(R.id.register);
        EditText Email = findViewById(R.id.registerEmail4);
        EditText Password = findViewById(R.id.registerPassword4);
        EditText Firstname = findViewById(R.id.Firstname);
        EditText Lastname = findViewById(R.id.Lastname);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String firstname = Firstname.getText().toString();
                String lastname = Lastname.getText().toString();
                sendApiRequest(email,password,firstname,lastname);
            }
        });
    }

    public void onTextViewClick(View view) {
        // Thực hiện các thao tác khi TextView được nhấp
        // Ví dụ: Chuyển hướng sang màn hình khác
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void sendApiRequest(String email, String password, String firstname, String lastname) {
        // ... Code gửi yêu cầu API sử dụng requestQueue ...
        // truyền dữ liệu bào body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("firstname",firstname);
            requestBody.put("lastname",lastname);
            requestBody.put("email", email);
            requestBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, api, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Xử lý phản hồi từ API
                        Toast.makeText(getApplicationContext(),"dang ki thanh cong",Toast.LENGTH_LONG);
                        Log.d("API Response", response.toString());
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                        Toast.makeText(getApplicationContext(),"dang ki that bai",Toast.LENGTH_LONG);
                        Log.e("API Error", error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}