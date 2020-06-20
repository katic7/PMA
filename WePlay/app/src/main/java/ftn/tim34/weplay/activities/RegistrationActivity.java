package ftn.tim34.weplay.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.User;
import ftn.tim34.weplay.service.ServiceUtils;

import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class RegistrationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void registerButtonAction(View view) {

        EditText tf_name = findViewById(R.id.tfName);
        String name = tf_name.getText().toString();
        EditText tf_surname = findViewById(R.id.ftSurname);
        String surname = tf_surname.getText().toString();
        EditText tf_email = findViewById(R.id.tfEmailReg);
        String email = tf_email.getText().toString();
        EditText tf_password = findViewById(R.id.tfPasswordReg);
        String password = tf_password.getText().toString();
        RatingBar ratingBar = findViewById(R.id.ratingBar2);
        Float rating = ratingBar.getRating();

        if (name.trim().isEmpty() || surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Morate popuniti sva polja!", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User("Petar", "Petrovic", "petar.petrovic@gmail.com", "123", 5);

            Call<ResponseBody> call = ServiceUtils.userService.register(user);
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("res code", String.valueOf(response.code()));
                    if (response.code() == 201) {
                        System.out.println(response.toString());
                        Log.d("response", response.toString());
                        Toast.makeText(getApplicationContext(), "Korisik je uspesno kreiran", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Greska, kod: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Doslo je do greske", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
