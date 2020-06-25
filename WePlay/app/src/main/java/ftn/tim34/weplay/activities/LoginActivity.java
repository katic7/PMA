package ftn.tim34.weplay.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.User;
import ftn.tim34.weplay.service.ServiceUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private SharedPreferences sharedPreferences;
    String fcmid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    public void loginButtonAction(View view) {
        final EditText tf_email = findViewById(R.id.tfEmailLog);
        final String email = tf_email.getText().toString();
        EditText tf_password = findViewById(R.id.tfPasswordLog);
        String password = tf_password.getText().toString();

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                             fcmid = task.getResult().getToken();
            }
        });
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Call<ResponseBody> call = ServiceUtils.userService.login(user,fcmid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    Log.d("login", response.toString());
                    final SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userEmail", tf_email.getText().toString());
                    editor.commit();
                    Toast.makeText(getApplicationContext(), response.body() + " je uspesno ulogovan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Greska, kod: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Doslo je do greske", Toast.LENGTH_SHORT).show();
            }
        });



        TextView tvSignUp = (TextView) findViewById(R.id.linkSignUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
