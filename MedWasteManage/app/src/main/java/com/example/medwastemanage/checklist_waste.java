package com.example.medwastemanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class checklist_waste extends AppCompatActivity {

    CheckBox check_infec, check_patho, check_sharps, check_chem, check_pharma, check_cyto;
    int infec, patho, sharps, chem, pharma, cyto;
    String status = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_waste);

        check_infec = findViewById(R.id.check_infectious);
        check_patho = findViewById(R.id.check_pathological);
        check_sharps = findViewById(R.id.check_sharps);
        check_chem = findViewById(R.id.check_chemical);
        check_pharma = findViewById(R.id.check_pharma);
        check_cyto = findViewById(R.id.check_cyto);

        infec = 0;
        patho = 0;
        sharps = 0;
        chem = 0;
        pharma = 0;
        cyto = 0;

        check_infec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    infec = 1;
                else
                    infec = 0;
            }
        });
        check_patho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    patho = 1;
                else
                    patho = 0;
            }
        });
        check_sharps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    sharps = 1;
                else
                    sharps = 0;
            }
        });
        check_chem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    chem = 1;
                else
                    chem = 0;
            }
        });
        check_pharma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    pharma = 1;
                else
                    pharma = 0;
            }
        });
        check_cyto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    cyto = 1;
                else
                    cyto = 0;
            }
        });
    }

    public void submitWaste(View view) {


        if (!status.equals("abc") || status.equals("Normal"))
        {
            if (infec == 0 && patho == 0 && sharps == 0 && chem == 0 && pharma == 0 && cyto == 0) {
                Toast.makeText(checklist_waste.this, "Check Atleast 1 Option", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(checklist_waste.this, pickup_details.class);
                intent.putExtra("infec", infec);
                intent.putExtra("patho", patho);
                intent.putExtra("sharps", sharps);
                intent.putExtra("chem", chem);
                intent.putExtra("pharma", pharma);
                intent.putExtra("cyto", cyto);
                startActivity(intent);

            }
        }
        else {
            Toast.makeText(checklist_waste.this,"Your previous order is not approved",Toast.LENGTH_LONG).show();
        }
    }









    @Override
    protected void onStart() {
        super.onStart();
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference().child("info").child(Prevalent.currentOnlineUser.getPhone());
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String state=dataSnapshot.child("Status").getValue().toString();
                    if(state.equals("Not Approved"))
                    {
                        status="abc";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
