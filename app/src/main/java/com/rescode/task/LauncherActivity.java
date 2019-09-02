package com.rescode.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {

    @BindView(R.id.product_Spinner)
    Spinner mSelectionSpinner;

    @BindView(R.id.product_Button)
    Button mShowProductButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        mShowProductButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] stringArray = getResources().getStringArray(R.array.product_id);
              String id= stringArray[mSelectionSpinner.getSelectedItemPosition()];

                Intent intent = new Intent(LauncherActivity.this,MainActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        String[] v = getResources().getStringArray(R.array.product_id);
        Toast.makeText(this, "" + v[mSelectionSpinner.getSelectedItemPosition()], Toast.LENGTH_SHORT).show();
    }
}
