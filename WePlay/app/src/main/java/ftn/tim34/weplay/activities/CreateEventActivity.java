package ftn.tim34.weplay.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.service.ServiceUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button btnCreate;
    Calendar calendar ;
    DatePickerDialog datePickerDialog ;
    int Year, Month, Day ;
    private EditText et_name;
    private EditText et_numbOfPlayers;
    private Spinner game_select;
    private RatingBar rb_minSkillLevel;
    private EditText et_deadline;
    private String[] arraySpinner = new String[] {
            "CS GO", "Fortnite", "LOL", "Call of Duty", "GTA V", "Fifa 2020"
    };

    private Long roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        //Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Bundle extras = getIntent().getExtras();

        roomId = extras.getLong("roomId");

        btnCreate = findViewById(R.id.btn_create);
        et_name = findViewById(R.id.eName);
        et_numbOfPlayers = findViewById(R.id.ePlayers);
        game_select = findViewById(R.id.game_select);

        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        game_select.setAdapter(adapterr);

        rb_minSkillLevel = findViewById(R.id.rb_minSkillLevel);
        et_deadline = findViewById(R.id.et_deadline);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gamingRooms, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //spinner.setAdapter(adapter);
        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        Button dialog_bt_date = (Button)findViewById(R.id.buttonDate);
        dialog_bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog = DatePickerDialog.newInstance(CreateEventActivity.this, Year, Month, Day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);

                datePickerDialog.setAccentColor(Color.parseColor("#0072BA"));

                datePickerDialog.setTitle("Select Date From DatePickerDialog");

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
               /* Date date1;
                String strDate = et_deadline.getText().toString().replaceAll("\\.","/");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date1 = formatter.parse(strDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    date1 = new Date();
                }*/
                Event e = new Event(et_name.getText().toString(), game_select.getSelectedItem().toString(),Integer.parseInt(et_numbOfPlayers.getText().toString()),0,"3",et_deadline.getText().toString(),"katicmilan7@gmail.com","Strava turnir - 20kRSD");
                final Call<ResponseBody> call = ServiceUtils.eventService.createEvent(roomId,e);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 200){
                            Toast.makeText(getApplicationContext(), "Uspešno kreiran event.",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Greška.",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Greška.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
       ++monthOfYear;
        String date = dayOfMonth + "." + monthOfYear + "." + year;
        et_deadline.setText(date);
    }
}
