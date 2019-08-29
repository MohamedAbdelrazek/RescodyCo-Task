package com.rescode.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        // obtain sugar sup addition value ..

        RadioButton sugarRadioButton = findViewById(mSugarRadioGroup.getCheckedRadioButtonId());
        String userPreferredSugarType = (String) sugarRadioButton.getText();

        // obtain milk sup addition value..
        RadioButton milkRadioButton = findViewById(mMilkRadioGroup.getCheckedRadioButtonId());
        String userPreferredMilkType = (String) milkRadioButton.getText();

        String desc = "العدد " +" : "+ mCounter;
        desc = desc + "\n السكر " +" : "+ userPreferredSugarType;
        desc = desc + "\n اللبن " +" : "+ userPreferredMilkType;
        desc = desc + "\n إجمالي الفاتورة" +" : "+ mFinalBill + " ريال ";
        String cafeEmail = "mohamed@rescody.com";

        // we can send data as we need to a server or whatever ....
        sendOrderUsingEmail(cafeEmail, desc);

    }

    private void sendOrderUsingEmail(String cafeEmail, String desc) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", cafeEmail, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "قهوة أسبريسو");

        emailIntent.putExtra(Intent.EXTRA_TEXT, desc);
        startActivity(Intent.createChooser(emailIntent, "ارسال طلب جديد ..."));
    }

    @Override
    public boolean onSupportNavigateUp() {
        // onBackPressed();
        Toast.makeText(this, "return to the previous Activity", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void updateBill(int count) {
        int totalBill = 15 * count;
        mTotoalBill.setText("" + totalBill + " ريال ");
        double tax = ((totalBill / 100.0) * 10.0);
        mFinalBill = totalBill + tax;
        mFinalTotoalBill.setText("" + mFinalBill + " ريال ");
    }

}


