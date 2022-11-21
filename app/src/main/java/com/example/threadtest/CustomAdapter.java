package com.example.threadtest;

import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private MainActivity Main = new MainActivity();
    private Context mContext;
    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder

    {
        protected TextView id;
        protected TextView AM;
        protected TextView hour;
        protected TextView min;
        protected CardView Cardview;
        protected TextView tv_ch0,tv_ch1,tv_ch2,tv_ch3,tv_ch4,tv_ch5,tv_ch6;


        public CustomViewHolder(View view) {
            super(view);

            this.id = (TextView) view.findViewById(R.id.tvId);
            this.AM = (TextView) view.findViewById(R.id.tvAM);
            this.hour = (TextView) view.findViewById(R.id.tvHour);
            this.min = (TextView) view.findViewById(R.id.tvMin);
            this.Cardview = (CardView) view.findViewById(R.id.CardView);
            this.tv_ch0 = (TextView) view.findViewById(R.id.tv_week_0);
            this.tv_ch1 = (TextView) view.findViewById(R.id.tv_week_1);
            this.tv_ch2 = (TextView) view.findViewById(R.id.tv_week_2);
            this.tv_ch3 = (TextView) view.findViewById(R.id.tv_week_3);
            this.tv_ch4 = (TextView) view.findViewById(R.id.tv_week_4);
            this.tv_ch5 = (TextView) view.findViewById(R.id.tv_week_5);
            this.tv_ch6 = (TextView) view.findViewById(R.id.tv_week_6);

            Cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    view = LayoutInflater.from(view.getContext())
                            .inflate(R.layout.activity_alarm_create, null, false);
                    builder.setView(view);
                    boolean[] Weekend = new boolean[8];
                    boolean[] Weekend1 = new boolean[8];
                    Weekend1 = mList.get(getAdapterPosition()).getWeekend();
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
                    numberPickermin.setWrapSelectorWheel(true);
                    numberPickermin.setValue(mList.get(getAdapterPosition()).getMin());

                    numberPickerhour.setMinValue(1);
                    numberPickerhour.setMaxValue(12);
                    numberPickerhour.setWrapSelectorWheel(true);
                    numberPickerhour.setValue(mList.get(getAdapterPosition()).getHour());

                    numberPickerAM.setMinValue(0);
                    numberPickerAM.setMaxValue(1);
                    numberPickerAM.setWrapSelectorWheel(true);
                    numberPickerAM.setValue(mList.get(getAdapterPosition()).getAM());
                    numberPickerAM.setDisplayedValues(new String[]{"AM","PM"});


                    if(Weekend1[0]==TRUE) {cb0.setChecked(true);}
                    if(Weekend1[1]==TRUE) {cb1.setChecked(true);}
                    if(Weekend1[2]==TRUE) {cb2.setChecked(true);}
                    if(Weekend1[3]==TRUE) {cb3.setChecked(true);}
                    if(Weekend1[4]==TRUE) {cb4.setChecked(true);}
                    if(Weekend1[5]==TRUE) {cb5.setChecked(true);}
                    if(Weekend1[6]==TRUE) {cb6.setChecked(true);}

                    final AlertDialog dialog = builder.create();
                    bt_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = Main.getCount();
                            int AM = numberPickerAM.getValue();
                            int hour = numberPickerhour.getValue();
                            int min = numberPickermin.getValue();
                            if(cb0.isChecked()) {Weekend[0] = TRUE;} //월 화 수 목 금 토 일
                            if(cb1.isChecked()) {Weekend[1] = TRUE;}
                            if(cb2.isChecked()) {Weekend[2] = TRUE;}
                            if(cb3.isChecked()) {Weekend[3] = TRUE;}
                            if(cb4.isChecked()) {Weekend[4] = TRUE;}
                            if(cb5.isChecked()) {Weekend[5] = TRUE;}
                            if(cb6.isChecked()) {Weekend[6] = TRUE;}
                            String AM1;
                            if(AM ==0 )
                            {
                                AM1 = "AM";
                            }
                            else
                            {
                                AM1 = "PM";
                            }
                            Dictionary dict = new Dictionary(id,AM,hour,min,Weekend);
                            mList.set(getAdapterPosition(),dict);
                            notifyItemChanged(getAdapterPosition());
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    bt_nosave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    bt_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(),mList.size());
                            dialog.dismiss();
                        }
                    });
                }
            });
        }

    }




    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.AM.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.hour.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.min.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.tv_ch0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        viewholder.tv_ch6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.AM.setGravity(Gravity.CENTER);
        viewholder.hour.setGravity(Gravity.CENTER);
        viewholder.min.setGravity(Gravity.CENTER);
        viewholder.tv_ch0.setGravity(Gravity.CENTER);
        viewholder.tv_ch1.setGravity(Gravity.CENTER);
        viewholder.tv_ch2.setGravity(Gravity.CENTER);
        viewholder.tv_ch3.setGravity(Gravity.CENTER);
        viewholder.tv_ch4.setGravity(Gravity.CENTER);
        viewholder.tv_ch5.setGravity(Gravity.CENTER);
        viewholder.tv_ch6.setGravity(Gravity.CENTER);

        viewholder.tv_ch0.setVisibility(View.INVISIBLE);
        viewholder.tv_ch1.setVisibility(View.INVISIBLE);
        viewholder.tv_ch2.setVisibility(View.INVISIBLE);
        viewholder.tv_ch3.setVisibility(View.INVISIBLE);
        viewholder.tv_ch4.setVisibility(View.INVISIBLE);
        viewholder.tv_ch5.setVisibility(View.INVISIBLE);
        viewholder.tv_ch6.setVisibility(View.INVISIBLE);
        boolean Weekend[] = mList.get(position).getWeekend();

        if(Weekend[0]==TRUE) {viewholder.tv_ch0.setVisibility(View.VISIBLE);}
        if(Weekend[1]==TRUE) {viewholder.tv_ch1.setVisibility(View.VISIBLE);}
        if(Weekend[2]==TRUE) {viewholder.tv_ch2.setVisibility(View.VISIBLE);}
        if(Weekend[3]==TRUE) {viewholder.tv_ch3.setVisibility(View.VISIBLE);}
        if(Weekend[4]==TRUE) {viewholder.tv_ch4.setVisibility(View.VISIBLE);}
        if(Weekend[5]==TRUE) {viewholder.tv_ch5.setVisibility(View.VISIBLE);}
        if(Weekend[6]==TRUE) {viewholder.tv_ch6.setVisibility(View.VISIBLE);}


        int AM4 = mList.get(position).getAM();
        if(AM4 == 0)
        {
            viewholder.AM.setText("오전");
        }
        else
        {
            viewholder.AM.setText("오후");
        }
        viewholder.hour.setText(String.valueOf(mList.get(position).getHour())+"시");
        viewholder.min.setText(String.valueOf(mList.get(position).getMin())+"분");
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}