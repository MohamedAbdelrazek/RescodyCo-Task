package com.rescode.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.rescode.task.api.ApiUtils;
import com.rescode.task.api.Order;
import com.rescode.task.utils.Utils;
import com.squareup.picasso.Picasso;

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

    @BindView(R.id.imageView)
    ImageView mMainImageView;

    @BindView(R.id.minus_btn)
    Button mMinusButton;
    @BindView(R.id.order_counter_txt_id)
    TextView mOrderCounterTextView;
    @BindView(R.id.total_bill)
    TextView mTotoalBill;
    @BindView(R.id.final_total_bill)
    TextView mFinalTotoalBill;

    @BindView(R.id.tax_value_txt_view)
    TextView mTaxValueTextView;

    @BindView(R.id.order_name)
    TextView mOrderNameTextView;

    @BindView(R.id.product_price)
    TextView mOrderPriceTextView;

    @BindView(R.id.sugar_text_view_id)
    TextView mSugarTextView;

    @BindView(R.id.milk_text_view_id)
    TextView mMilkTextView;

    @BindView(R.id.img_view1)
    ImageView mImageView1;
    @BindView(R.id.img_view2)
    ImageView mImageView2;
    @BindView(R.id.img_view3)
    ImageView mImageView3;
    @BindView(R.id.img_view4)
    ImageView mImageView4;

    @BindView(R.id.txt_view1)
    TextView mTxtView1;
    @BindView(R.id.txt_view2)
    TextView mTxtView2;
    @BindView(R.id.txt_view3)
    TextView mTxtView3;
    @BindView(R.id.txt_view4)
    TextView mTxtView4;

    @BindView(R.id.img_view11)
    ImageView mImageView11;
    @BindView(R.id.img_view22)
    ImageView mImageView22;
    @BindView(R.id.img_view33)
    ImageView mImageView33;


    @BindView(R.id.txt_view11)
    TextView mTxtView11;
    @BindView(R.id.txt_view22)
    TextView mTxtView22;
    @BindView(R.id.txt_view33)
    TextView mTxtView33;


    @BindView(R.id.submit_order)
    Button mSubmitOrderButton;

    @BindView(R.id.main_grid_layout_id)
    GridLayout mSugarGridLayout;
    @BindView(R.id.main_grid2_layout_id)
    GridLayout mMilkGridLayout;

    private int mCounter;
    private double mFinalBill;
    private double mOrderPrice;
    private double mTax;
    private NewOrderModel mNewOrderModel;
    private int mFinalSugarSelection;
    private int mFinalMilkSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCounter = 1;

        Intent intent = getIntent();
        String product_id = intent.getExtras().getString("ID");
        getData(product_id);

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCounter > 1) {
                    mCounter--;
                    mOrderCounterTextView.setText("" + mCounter);
                    updateBill(mOrderPrice);
                }
            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter++;
                mOrderCounterTextView.setText("" + mCounter);
                updateBill(mOrderPrice);
            }
        });
        mSubmitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startToSendDataToWhatEver();

            }
        });

        for (int i = 0; i < mSugarGridLayout.getChildCount(); i++) {

            LinearLayout child = (LinearLayout) mSugarGridLayout.getChildAt(i);
            int finalI = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(LauncherActivity.this, ""+ finalI, Toast.LENGTH_SHORT).show();
                    child.setBackgroundColor(Color.parseColor("#B88067"));
                    Toast.makeText(MainActivity.this, "" + finalI, Toast.LENGTH_SHORT).show();
                    mFinalSugarSelection = finalI;
                    for (int j = 0; j < mSugarGridLayout.getChildCount(); j++) {
                        if (finalI == j) continue;
                        LinearLayout child = (LinearLayout) mSugarGridLayout.getChildAt(j);
                        child.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    }
                }

            });
        }
        for (int i = 0; i < mMilkGridLayout.getChildCount(); i++) {

            LinearLayout child = (LinearLayout) mMilkGridLayout.getChildAt(i);
            int finalI = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(LauncherActivity.this, ""+ finalI, Toast.LENGTH_SHORT).show();
                    child.setBackgroundColor(Color.parseColor("#B88067"));
                    Toast.makeText(MainActivity.this, "" + finalI, Toast.LENGTH_SHORT).show();
                    mFinalMilkSelection = finalI;
                    for (int j = 0; j < mMilkGridLayout.getChildCount(); j++) {
                        if (finalI == j) continue;
                        LinearLayout child = (LinearLayout) mMilkGridLayout.getChildAt(j);
                        child.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    }
                }

            });
        }
    }

    private void startToSendDataToWhatEver() {
        OrderModel orderModel = new OrderModel();

        orderModel.setUser_id("1");
        orderModel.setProduct_id("1");
        orderModel.setBrunche_id("1");
        orderModel.setCount("" + mCounter);
        orderModel.setTotlePrice("" + mFinalBill);

        int addititonsID1 = mNewOrderModel.getProduct().getAdditions().get(0).getId();

        int addititonsID2 = mNewOrderModel.getProduct().getAdditions().get(1).getId();

        int subAddititonID1 = mNewOrderModel.getProduct().getAdditions().get(0).getSubadd().get(mFinalSugarSelection).getId();

        int subAddititonID2 = mNewOrderModel.getProduct().getAdditions().get(1).getSubadd().get(mFinalMilkSelection).getId();

        orderModel.setAddition("" + addititonsID1 + "," + addititonsID2);
        orderModel.setSubadd("" + subAddititonID1 + "," + subAddititonID2);

        Log.i("ZOKA", "startToSendDataToWhatEver: " + orderModel.toString());

           sendOrderToTheServer(orderModel);

    }

    private void sendOrderToTheServer(OrderModel orderModel) {

        Log.i("ZOKA", "send : " + orderModel.toString());

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("يرجى الإنتظار ");
        pd.show();
        Retrofit retrofit = ApiUtils.getRetrofitInstance();
        Order sendUserOrder = retrofit.create(Order.class);

        Call<OrderModel> call = sendUserOrder.sendOrder(orderModel.getUser_id(),
                orderModel.getProduct_id(), orderModel.getBrunche_id(), orderModel.getCount(),
                orderModel.getAddition(), orderModel.getSubadd(), orderModel.getTotlePrice());
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Log.i("ZOKA", "onResponse: "+response.body().getStatus());
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

    private void updateBill(double price) {
        double totalBill = price * mCounter;
        mTotoalBill.setText("" + totalBill + " ريال ");
        double tax = ((totalBill / 100.0) * mTax);
        mFinalBill = totalBill + tax;
        mFinalTotoalBill.setText("" + mFinalBill + " ريال ");
    }


    private void getData(String id) {

        Retrofit retrofit = ApiUtils.getRetrofitInstance();
        Order sendUserOrder = retrofit.create(Order.class);
        Call<NewOrderModel> call = sendUserOrder.getOrderByID(id);

        call.enqueue(new Callback<NewOrderModel>() {
            @Override
            public void onResponse(Call<NewOrderModel> call, Response<NewOrderModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                mNewOrderModel = response.body();
                mOrderNameTextView.setText(mNewOrderModel.getProduct().getTitle());
                mToolbar.setTitle(mNewOrderModel.getProduct().getTitle());
                mOrderPriceTextView.setText(mNewOrderModel.getProduct().getPrice() + "  ريال");
                mTaxValueTextView.setText(mNewOrderModel.getProduct().getTax() + "   %");

                mSugarTextView.setText(mNewOrderModel.getProduct().getAdditions().get(0).getTitle());
                mMilkTextView.setText(mNewOrderModel.getProduct().getAdditions().get(1).getTitle());

                Picasso.get().load(Utils.constructMainImagePath(mNewOrderModel.getProduct().getImg())).into(mMainImageView);

                mOrderPrice = Double.parseDouble(mNewOrderModel.getProduct().getPrice());
                mTax = Double.parseDouble(mNewOrderModel.getProduct().getTax());
                updateBill(mOrderPrice);

                fillSugarData(mNewOrderModel);
                fillMilkData(mNewOrderModel);
            }

            @Override
            public void onFailure(Call<NewOrderModel> call, Throwable t) {
                Log.i("ZOKA", "onResponse: " + t.getMessage());
            }
        });
    }

    private void fillMilkData(NewOrderModel newOrderModel) {

        if (newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(0).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(0).getImg())), mImageView11);
        } else {
            mImageView11.setImageResource(R.drawable.ic_no_sugar_48dp);
        }

        if (newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(1).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(1).getImg())), mImageView22);
        } else {
            mImageView22.setImageResource(R.drawable.ic_mid_sugar_48dp);

        }


        if (newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(2).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(2).getImg())), mImageView33);
        } else {
            mImageView33.setImageResource(R.drawable.ic_more_sugar_48dp);
        }


        mTxtView11.setText(newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(0).getTitle());
        mTxtView22.setText(newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(1).getTitle());
        mTxtView33.setText(newOrderModel.getProduct().getAdditions().get(1).getSubadd().get(2).getTitle());

    }

    private void fillSugarData(NewOrderModel newOrderModel) {
        if (newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(0).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(0).getImg())), mImageView1);
        } else {
            mImageView1.setImageResource(R.drawable.ic_no_sugar_48dp);
        }

        if (newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(1).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(1).getImg())), mImageView2);
        } else {
            mImageView2.setImageResource(R.drawable.ic_mid_sugar_48dp);

        }


        if (newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(2).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(2).getImg())), mImageView3);
        } else {
            mImageView3.setImageResource(R.drawable.ic_more_sugar_48dp);
        }

        if (newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(3).getImg() != null) {
            GlideToVectorYou.justLoadImage(MainActivity.this, Uri.parse(Utils.constructAdditionImagePath(
                    newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(3).getImg())), mImageView4);
        } else {
            mImageView4.setImageResource(R.drawable.ic_more_sugar_48dp);
        }

        mTxtView1.setText(newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(0).getTitle());
        mTxtView2.setText(newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(1).getTitle());
        mTxtView3.setText(newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(2).getTitle());
        mTxtView4.setText(newOrderModel.getProduct().getAdditions().get(0).getSubadd().get(3).getTitle());
    }
}