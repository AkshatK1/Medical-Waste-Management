package com.example.medwastemanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class pickup_details extends AppCompatActivity {

    EditText name, phone, address, landmark, zip, city, state;
    String sName, sPhone, sAddress, sLandmark, sZip, sCity, sState;
    int infec, patho, sharps, chem, pharma, cyto;
    String allWaste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_details);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        zip = findViewById(R.id.zip_code);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);



        Intent intent = getIntent();
        infec = intent.getIntExtra("infec", 0);
        patho = intent.getIntExtra("patho", 0);
        sharps = intent.getIntExtra("sharps", 0);
        chem = intent.getIntExtra("chem", 0);
        pharma = intent.getIntExtra("pharma", 0);
        cyto = intent.getIntExtra("cyto", 0);


        allWaste = " ";
        if(infec != 0)
            allWaste = allWaste + "Infectious Waste, ";
        if(patho != 0)
            allWaste = allWaste + "Pathological Waste, ";
        if(sharps != 0)
            allWaste = allWaste + "Sharps Waste, ";
        if(chem != 0)
            allWaste = allWaste + "Chemical Waste, ";
        if(pharma != 0)
            allWaste = allWaste + "Pharmaceutical Waste, ";
        if(cyto !=0)
            allWaste = allWaste + "Cytotoxic Waste, ";

    }

    public void requestPickup(View view) {
        sName = name.getText().toString();
        sPhone = phone.getText().toString();
        sAddress = address.getText().toString();
        sLandmark = landmark.getText().toString();
        sZip = zip.getText().toString();
        sCity = city.getText().toString();
        sState = state.getText().toString();
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("info");
        HashMap<String, Object> userdataMap = new HashMap<>();
        userdataMap.put("phone", sPhone);
        userdataMap.put("address",sAddress);
        userdataMap.put("name", sName);
        userdataMap.put("landmark", sLandmark);
        userdataMap.put("city", sCity);
        userdataMap.put("zip", sZip);
        userdataMap.put("state", sState);
        userdataMap.put("wastetype",allWaste);
        userdataMap.put("Status","Not Approved");

        RootRef.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Intent thankyou = new Intent(pickup_details.this, thankyou.class);
                    startActivity(thankyou);
                    finish();
                }
            }
        });

    }
}
