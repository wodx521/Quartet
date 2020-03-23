package com.lr.quartetplatform.moudle1.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle1.presenter.ReservationPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationActivity extends BaseMvpActivity<ReservationPresenter> {
    private TextView tvTitle, tvData, tvTime, tvReservation;
    private ImageView ivBack;
    private EditText etPhone;
    private OptionsPickerView pvNoLinkOptions;
    private List<String> timeList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();

    @Override
    protected ReservationPresenter getPresenter() {
        return new ReservationPresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.reservation);


        getChooseData();


        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvData.setText(dataList.get(options1));
                tvTime.setText(timeList.get(options2));
            }
        })
                .setLayoutRes(R.layout.pickerview_customer_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView tvCancel = v.findViewById(R.id.tvCancel);
                        TextView tvConfirm = v.findViewById(R.id.tvConfirm);
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvNoLinkOptions.dismiss();
                            }
                        });
                        tvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvNoLinkOptions.returnData();
                                pvNoLinkOptions.dismiss();
                            }
                        });
                    }
                })
                .setItemVisibleCount(5)
                .build();
        pvNoLinkOptions.setNPicker(dataList, timeList, null);
        pvNoLinkOptions.setSelectOptions(0, 0, 1);
    }

    private void getChooseData() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar tempData = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_YEAR, selectedDate.get(Calendar.DAY_OF_YEAR) + 7);

        for (int i = 0; i <= 7; i++) {
            tempData.set(Calendar.DAY_OF_YEAR, selectedDate.get(Calendar.DAY_OF_YEAR) + i);
            int month = tempData.get(Calendar.MONTH);
            int day = tempData.get(Calendar.DAY_OF_MONTH);
            int week = tempData.get(Calendar.DAY_OF_WEEK);
            String weekContent = "";
            switch (week) {
                case 1:
                    weekContent = "周日";
                    break;
                case 2:
                    weekContent = "周一";
                    break;
                case 3:
                    weekContent = "周二";
                    break;
                case 4:
                    weekContent = "周三";
                    break;
                case 5:
                    weekContent = "周四";
                    break;
                case 6:
                    weekContent = "周五";
                    break;
                case 7:
                    weekContent = "周六";
                    break;
                default:
            }

            dataList.add((month + 1) + "月" + day + "日 " + weekContent);
        }

        timeList.add("上午12:00前");
        timeList.add("下午12:00-18:00");
        timeList.add("晚上18:00后");
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_reservation;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        tvData = findViewById(R.id.tvData);
        tvTime = findViewById(R.id.tvTime);
        etPhone = findViewById(R.id.etPhone);
        tvReservation = findViewById(R.id.tvReservation);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvData.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvReservation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvData:
                pvNoLinkOptions.show();
                break;
            case R.id.tvTime:

                break;
            case R.id.tvReservation:

                break;
            default:
        }
    }
}
