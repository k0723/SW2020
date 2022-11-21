package com.example.threadtest;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button bt_add,bt_logout,bt_signout;
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
    boolean keeplogin = false;
    public Object search = new JSONArray();
    public ArrayList<Dictionary> getmArrayList() {
        return mArrayList;
    }
    public StringBuilder days = new StringBuilder();
    public String startTime = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userId = intent.getStringExtra("id");
        bt_add = findViewById(R.id.bt_add);
        bt_signout = findViewById(R.id.bt_signout);
        bt_logout = findViewById(R.id.bt_logout);
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


        retrofit_client.getApiService().getAlarm()
                .enqueue(new Callback<List<alarmsearch>>() {
                    @Override
                    public void onResponse(Call<List<alarmsearch>> call, Response<List<alarmsearch>> response) {
                        List<alarmsearch> result = response.body();


                        for(int i =0; i<result.size(); i++)
                        {
                            int SearchAM,SearchHour,SearchMin;
                            String setAm[] = result.get(i).getStartTime().split(":");
                            SearchHour = Integer.parseInt(setAm[0]);
                            SearchMin = Integer.parseInt(setAm[1]);
                            String SearchWeekend = result.get(i).getDays();
                            boolean[] Weekend = new boolean[8];
                            if(SearchWeekend.equals("MONDAY")) {Weekend[0] = TRUE;}
                            if(SearchWeekend.equals("TUESDAY")) {Weekend[1] = TRUE;}
                            if(SearchWeekend.equals("WEDNESDAY")) {Weekend[2] = TRUE;}
                            if(SearchWeekend.equals("THURSDAY")) {Weekend[3] = TRUE;}
                            if(SearchWeekend.equals("FRIDAY")) {Weekend[4] = TRUE;}
                            if(SearchWeekend.equals("SATURDAY")) {Weekend[5] = TRUE;}
                            if(SearchWeekend.equals("SUNDAY")) {Weekend[6] = TRUE;}
                            if(Integer.parseInt(setAm[0])<13)
                            {
                                SearchAM = 0;
                            }
                            else
                            {
                                SearchAM = 1;
                                SearchHour = SearchHour - 12;
                            }
                            Dictionary dict = new Dictionary(i,SearchAM,SearchHour,SearchMin,Weekend);
                            mArrayList.add(0,dict);
                            mAdapter.notifyItemInserted(0);
                        }

                        Log.d("SearchTest",result.toString());
                        Log.d("SearchTest", result.get(0).getAlarmId());
                        Log.d("SearchTest", String.valueOf(result.size()));
                        Log.d("SearchTest", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<List<alarmsearch>> call, Throwable t) {

                    }
                });
        Toast myToast = Toast.makeText(MainActivity.this,userId+"님 반갑습니다.", Toast.LENGTH_SHORT);
        myToast.show();

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
                final Switch sw_re = (Switch)
                        view.findViewById(R.id.sw_re);

                numberPickermin.setMinValue(0);
                numberPickermin.setMaxValue(59);

                numberPickerhour.setMinValue(1);
                numberPickerhour.setMaxValue(12);

                numberPickerAM.setMinValue(0);
                numberPickerAM.setMaxValue(1);
                numberPickerAM.setDisplayedValues(new String[]{"AM","PM"});
                int[] answer = new int[3];
                boolean[] Weekend = new boolean[8];
                final AlertDialog dialog = builder.create();

                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count++;
                        StringBuilder days = new StringBuilder();
                        if(cb0.isChecked()) {Weekend[0] = TRUE;
                            days.append("MONDAY"); } //월 화 수 목 금 토 일
                        if(cb1.isChecked()) {Weekend[1] = TRUE;
                            days.append("TUESDAY");}
                        if(cb2.isChecked()) {Weekend[2] = TRUE;
                            days.append("WEDNESDAY");}
                        if(cb3.isChecked()) {Weekend[3] = TRUE;
                            days.append("THURSDAY");}
                        if(cb4.isChecked()) {Weekend[4] = TRUE;
                            days.append("FRIDAY");}
                        if(cb5.isChecked()) {Weekend[5] = TRUE;
                            days.append("SATURDAY");}
                        if(cb6.isChecked()) {Weekend[6] = TRUE;
                            days.append("SUNDAY");}

                        int AM = numberPickerAM.getValue();
                        int hour = numberPickerhour.getValue();
                        int min = numberPickermin.getValue();
                        int hourcal = 0;
                        String AM1;
                        if(AM ==0 )
                        {
                            AM1 = "AM";
                            hourcal += hour;
                        }
                        else
                        {
                            AM1 = "PM";
                            hourcal += 12+hour;
                        }

                        Dictionary dict = new Dictionary(count,AM,hour,min,Weekend);
                        mArrayList.add(0,dict);
                        mAdapter.notifyItemInserted(0);

                        if(hourcal<10)
                        {
                            startTime += "0"+Integer.toString(hourcal)+":";
                        }
                        else
                            startTime += Integer.toString(hourcal)+":";

                        if(min<10)
                        {
                            startTime += "0"+Integer.toString(min);
                        }
                        else
                            startTime += Integer.toString(min);
                        Log.d("startTime",startTime);
                        Log.d("sw_re", String.valueOf(sw_re.isChecked()));
                        Log.d("days",days.toString());
                        alarmAdd add = new alarmAdd(
                                days.toString(),
                                sw_re.isChecked(),
                                startTime
                        );

                      retrofit_client.getApiService().postHour(add)
                              .enqueue(new Callback<alarmAdd>() {
                                  @Override
                                  public void onResponse(Call<alarmAdd> call, Response<alarmAdd> response) {
                                        Log.d("INSERT","성공");
                                        Log.d("1", String.valueOf(response.code()));
                                  }

                                  @Override
                                  public void onFailure(Call<alarmAdd> call, Throwable t) {
                                      Log.d("INSERT","실패");
                                  }
                              });
                        Toast ToastAlarmAdd = Toast.makeText(MainActivity.this,days.toString()+"에 "+startTime+"알람이 추가되었습니다.", Toast.LENGTH_SHORT);
                        ToastAlarmAdd.show();
                        startTime = "";
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

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit_client.getApiService().getlogout()
                        .enqueue(new Callback<logout>() {
                            @Override
                            public void onResponse(Call<logout> call, Response<logout> response) {
                                logout result = response.body();
                                keeplogin = response.isSuccessful();
                                Log.d("logoutTset", String.valueOf(keeplogin));
                                mArrayList.clear();
                                mAdapter.notifyDataSetChanged();
                                MyCookieJar jar = new MyCookieJar();
                                jar.setCookies(null);
                                Toast ToastLogout = Toast.makeText(MainActivity.this,userId+"님 로그아웃 되었습니다.", Toast.LENGTH_SHORT);
                                ToastLogout.show();
                                Log.d("resetCookie", String.valueOf(jar.getCookies()));
                            }

                            @Override
                            public void onFailure(Call<logout> call, Throwable t) {

                            }

                        });
            }

        });

        bt_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view3 = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.activitysignout, null, false);
                builder.setView(view3);
                final AlertDialog dialog1 = builder.create();


                final Button bt_agree = (Button)
                        view3.findViewById(R.id.bt_agree);
                final EditText edit_singup_id = (EditText)
                        view3.findViewById(R.id.edit_signout);
                dialog1.show();

                bt_agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = String.valueOf(edit_singup_id.getText());
                        retrofit_client.getApiService().deleteSign(id)
                                .enqueue(new Callback<signout>() {
                                    @Override
                                    public void onResponse(Call<signout> call, Response<signout> response) {
                                        signout result = response.body();
                                        Log.d("sucess", String.valueOf(result.isSuccess()));
                                        Log.d("sending", String.valueOf(response.code()));
                                        Toast.makeText(dialog1.getContext(),id+"삭제 성공", Toast.LENGTH_SHORT).show();
                                        dialog1.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<signout> call, Throwable t) {
                                        Toast.makeText(dialog1.getContext(),"삭제 실패", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });

    }
}