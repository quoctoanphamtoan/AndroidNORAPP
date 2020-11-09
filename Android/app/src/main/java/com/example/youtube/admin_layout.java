package com.example.youtube;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtube.Adapter.UserAdapter;
import com.example.youtube.Resualt.UserResual;
import com.example.youtube.Retrofit.RetrolClient;
import com.example.youtube.Service.RetrofitInterface;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class admin_layout extends AppCompatActivity {
    ListView gdv;
    UserAdapter adt;

   private EditText txtmail;
    ArrayList<UserResual> listuser;
    TextView tv;
    private RetrofitInterface retrofitInterface;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_layout);
        mAuth = FirebaseAuth.getInstance();
        anhxa();
        setclick();


        init();


    }

    private void init() {
        listuser = new ArrayList<>();
        RetrolClient retrolClient = new RetrolClient();
        retrofitInterface = retrolClient.getservice();
        RetrofitInterface retrofitInterface = retrolClient.getservice();
        Call<List<UserResual>> call = retrofitInterface.executeusers();
        call.enqueue(new Callback<List<UserResual>>() {
            @Override

            public void onResponse(Call<List<UserResual>> call, Response<List<UserResual>> response) {
                List<UserResual> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    listuser.add(list.get(i));
                }
                ArrayAdapter<UserResual> arrayAdapter = new ArrayAdapter<UserResual>(admin_layout.this, android.R.layout.simple_list_item_1, listuser);
                gdv.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<List<UserResual>> call, Throwable t) {

            }
        });


    }

    private void anhxa() {
        gdv = (ListView) findViewById(R.id.gdv);
//
    }

    private void setup() {

    }

    private void setclick() {
        gdv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int i ;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(admin_layout.this);
                builder.setTitle("Confirm");
                builder.setMessage("Thong tin user" + listuser.get(position).toString());
                i=position;
                builder.setPositiveButton("Disable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(admin_layout.this);
                        builder.setTitle("");
                        builder.setMessage("Ban có chắc disable tài khoản này");
                        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RetrolClient retrolClient = new RetrolClient();
                                retrofitInterface = retrolClient.getservice();
                                RetrofitInterface retrofitInterface = retrolClient.getservice();
                                Call<Void> call = retrofitInterface.disable(listuser.get(position).getEmail().toString());
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });
                            }
                        });
                        builder.show();
                    }
                });
                builder.show();

            }
        });

    }


}