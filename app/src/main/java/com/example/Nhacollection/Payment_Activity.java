package com.example.Nhacollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Nhacollection.Domain.Items;

import java.util.ArrayList;
import java.util.List;

public class Payment_Activity extends AppCompatActivity {
    private TextView mSubtotal;
    private Button payBtn;
    private  TextView mSubTotalAmount;
    List<Items> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        double amount = 0.0;

        amount=getIntent().getDoubleExtra("amount",0.0);
        itemsList = (ArrayList<Items>) getIntent().getSerializableExtra("itemsList");

        mSubtotal=findViewById(R.id.sub_total);

        payBtn=findViewById(R.id.pay_btn);
        mSubTotalAmount=findViewById(R.id.total_amt);
        if(itemsList!=null &&  itemsList.size()>0){
            amount= 0.0;

            for(Items item: itemsList){
                amount+=item.getPrice();


            }
            mSubtotal.setText(" "+amount+"PKR");
        }else{
            mSubtotal.setText(" "+amount+"PKR");
            mSubTotalAmount.setText(""+amount+"PKR");


        }

            payBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Payment_Activity.this, jazz_integration.class);
                    startActivity(intent);
                    finish();
                }
            });


        }


}





