package com.example.request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    Button Request;
    private RecyclerView requestList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Request = findViewById(R.id.request);
        requestList = findViewById(R.id.requestList);
        requestList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        info = FirebaseDatabase.getInstance().getReference().child("info");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(info.orderByChild("Status").equalTo("Not Approved"), Model.class)
                .build();
        FirebaseRecyclerAdapter<Model, requestViewHolder> adapter =
                new FirebaseRecyclerAdapter<Model, requestViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull requestViewHolder requestViewHolder, final int i, @NonNull final Model model) {
                        requestViewHolder.name.setText(model.getName());
                        requestViewHolder.city.setText(model.getCity());
                        requestViewHolder.zip.setText(model.getZip());
                        requestViewHolder.wastetype.setText(model.getWastetype());
                        requestViewHolder.landmark.setText(model.getLandmark());
                        requestViewHolder.state.setText(model.getState());
                        requestViewHolder.phone.setText(model.getPhone());
                        requestViewHolder.status.setText(model.getStatus());
                        requestViewHolder.address.setText(model.getAddress());
                        requestViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Approve this request");
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which == 0) {
                                            final String id = getRef(which).getKey();
                                            info.child(id).child("Status").setValue("Approved").addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(MainActivity.this, "The request is approved", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        } else {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();


                            }
                        });
                    }

                    @NonNull
                    @Override
                    public requestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestlayout, parent, false);
                        requestViewHolder holder = new requestViewHolder(view);
                        return holder;
                    }
                };
        requestList.setAdapter(adapter);
        adapter.startListening();
    }
}
