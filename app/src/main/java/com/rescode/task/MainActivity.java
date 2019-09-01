package com.rescode.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.rescode.task.api.ApiUtils;
import com.rescode.task.api.SendUserOrder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.plus_btn)
    Button mPlusButton;
    @BindView(R.id.minus_btn)
    Button mMinusButton;
    @BindView(R.id.order_counter_txt_id)
    TextView mOrderCounterTextView;
    @BindView(R.id.total_bill)
    TextView mTotoalBill;
    @BindView(R.id.final_total_bill)
    TextView mFinalTotoalBill;
    @BindView(R.id.sugar_radio_group)
    RadioGroup mSugarRadioGroup;
    @BindView(R.id.milk_radio_group)
    RadioGroup mMilkRadioGroup;

    @BindView(R.id.submit_order)
    Button mSubmitOrderButton;

    private int mCounter;
    private double mFinalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle("قهوة أسبريسوا");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCounter = 1;
        updateBill(mCounter);
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCounter > 1) {
                    mCounter--;
                    mOrderCounterTextView.setText("" + mCounter);
                    updateBill(mCounter);
                }
            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter++;
                mOrderCounterTextView.setText("" + mCounter);
                updateBill(mCounter);
            }
        });
        mSubmitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startToSendDataToWhatEver();

            }
        });


    }

    private void startToSendDataToWhatEver() {

        HashMap<String, String> sugarHashMap = new HashMap<>();
        sugarHashMap.put("سكر زيادة", "1");
        sugarHashMap.put("سكر خفيف", "2");
        sugarHashMap.put("بدون سكر", "3");
        HashMap<String, String> milkHashMap = new HashMap<>();
        milkHashMap.put("كتير", "1");
        milkHashMap.put("متوسط", "2");
        milkHashMap.put("قليل", "3");

        RadioButton sugarRadioButton = findViewById(mSugarRadioGroup.getCheckedRadioButtonId());
        String userPreferredSugarType = (String) sugarRadioButton.getText();

        RadioButton milkRadioButton = findViewById(mMilkRadioGroup.getCheckedRadioButtonId());
        String userPreferredMilkType = (String) milkRadioButton.getText();

        OrderModel orderModel = new OrderModel();

        orderModel.setUser_id("1");
        orderModel.setProduct_id("1");
        orderModel.setBrunche_id("1");
        orderModel.setCount("" + mCounter);
        orderModel.setTotlePrice("" + mFinalBill);

        orderModel.setAddition("1," + sugarHashMap.get(userPreferredSugarType));
        orderModel.setSubadd("2," + milkHashMap.get(userPreferredMilkType));

        sendOrderToTheServer(orderModel);

    }

    private void sendOrderToTheServer(OrderModel orderModel) {

        Log.i("ZOKA", "send : " + orderModel.toString());

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("يرجى الإنتظار ");
        pd.show();
        Retrofit retrofit = ApiUtils.getRetrofitInstance();
        SendUserOrder sendUserOrder = retrofit.create(SendUserOrder.class);

        Call<OrderModel> call = sendUserOrder.sendOrder(orderModel.getUser_id(),
                orderModel.getProduct_id(), orderModel.getBrunche_id(), orderModel.getCount(),
                orderModel.getAddition(), orderModel.getSubadd(), orderModel.getTotlePrice());
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                pd.dismiss();
                Toast.makeText(MainActivity.this, "تم ارسال طلبك بنجاح ! ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("ZOKA", "onResponse: " + t.getMessage());
                pd.dismiss();

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        // onBackPressed();
        Toast.makeText(this, "return to the previous Activity", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void updateBill(int count) {
        double totalBill = 15 * count;
        mTotoalBill.setText("" + totalBill + " ريال ");
        double tax = ((totalBill / 100.0) * 10.0);
        mFinalBill = totalBill + tax;
        mFinalTotoalBill.setText("" + mFinalBill + " ريال ");
    }

}