package ftn.tim34.weplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ftn.tim34.weplay.model.User;
import ftn.tim34.weplay.service.ServiceUtils;

import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class RegistrationActivity extends AppCompatActivity {

    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("Petar", "Petrovic", "EMAIl", "PASS", 5);
                final Call<ResponseBody> call = ServiceUtils.userService.register(user);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            Toast.makeText(getApplicationContext(), "Korisik je uspesno kreiran", Toast.LENGTH_SHORT);
                        }else{
                            Toast.makeText(getApplicationContext(), "Greska, kod: " + response.code(), Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Doslo je do greske",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
