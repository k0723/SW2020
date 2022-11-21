package com.example.threadtest;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button bt_add;
    ScrollView scrollView;
    Call<data_model> call;
    TextView textView;
    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;

    public int getCount() {
        return count;
    }

    private int count = -1;
    RecyclerView recyclerView;

    public ArrayList<Dictionary> getmArrayList() {
        return mArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_add = findViewById(R.id.bt_add);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mArrayList = new ArrayList<>();
        mAdapter = new CustomAdapter( mArrayList);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.activity_alarm_create, null, false);
                builder.setView(view);

                final NumberPicker numberPickerAM = (NumberPicker)
                        view.findViewById(R.id.numPickerAP);
                final NumberPicker numberPickerhour = (NumberPicker)
                        view.findViewById(R.id.numPickerhour);
                final NumberPicker numberPickermin = (NumberPicker)
                        view.findViewById(R.id.numPickerMin);
                final Button bt_save = (Button)
                        view.findViewById(R.id.bt_save);
                final Button bt_nosave = (Button)
                        view.findViewById(R.id.bt_noSave);
                final Button bt_delete = (Button)
                        view.findViewById(R.id.bt_del);
                final CheckBox cb0 = (CheckBox)
                        view.findViewById(R.id.mon);
                final CheckBox cb1 = (CheckBox)
                        view.findViewById(R.id.tue);
                final CheckBox cb2 = (CheckBox)
                        view.findViewById(R.id.wen);
                final CheckBox cb3 = (CheckBox)
                        view.findViewById(R.id.Thu);
                final CheckBox cb4 = (CheckBox)
                        view.findViewById(R.id.Fri);
                final CheckBox cb5 = (CheckBox)
                        view.findViewById(R.id.Sat);
                final CheckBox cb6 = (CheckBox)
                        view.findViewById(R.id.Sun);

                numberPickermin.setMinValue(0);
                numberPickermin.setMaxValue(59);

                numberPickerhour.setMinValue(1);
                numberPickerhour.setMaxValue(12);

                numberPickerAM.setMinValue(0);
                numberPickerAM.setMaxValue(1);
                numberPickerAM.setDisplayedValues(new String[]{"AM","PM"});
                int[] answer = new int[3];
                boolean[] Weekend = new boolean[6];
                final AlertDialog dialog = builder.create();

                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count++;
                        if(cb0.isChecked()) {Weekend[0] = TRUE;} //월 화 수 목 금 토 일
                        if(cb1.isChecked()) {Weekend[1] = TRUE;}
                        if(cb2.isChecked()) {Weekend[2] = TRUE;}
                        if(cb3.isChecked()) {Weekend[3] = TRUE;}
                        if(cb4.isChecked()) {Weekend[4] = TRUE;}
                        if(cb5.isChecked()) {Weekend[5] = TRUE;}
                        if(cb6.isChecked()) {Weekend[6] = TRUE;}

                        int AM = numberPickerAM.getValue();
                        int hour = numberPickerhour.getValue();
                        int min = numberPickermin.getValue();
                        String AM1;
                        if(AM ==0 )
                        {
                            AM1 = "AM";
                        }
                        else
                        {
                            AM1 = "PM";
                        }

                        Dictionary dict = new Dictionary(count,AM,hour,min,Weekend);
                        mArrayList.add(0,dict);
                        mAdapter.notifyItemInserted(0);

                        call = retrofit_client.getApiService().gethour(Integer.toString(hour));
                        call.enqueue(new Callback<data_model>(){
                            //콜백 받는 부분
                            @Override
                            public void onResponse(Call<data_model> call, Response<data_model> response) {
                                data_model result = response.body();
                                String str;
                                str= result.getDays() +"\n"+
                                        result.getHour2()+"\n"+
                                        result.getMinute()+"\n";
                                textView.setText(str);
                            }

                            @Override
                            public void onFailure(Call<data_model> call, Throwable t) {

                            }
                        });
                    }
                });
                dialog.show();
                bt_nosave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                bt_delete.setVisibility(View.GONE);

            }
        });
    }
}
