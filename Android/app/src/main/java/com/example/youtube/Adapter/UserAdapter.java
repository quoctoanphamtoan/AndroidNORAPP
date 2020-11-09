package com.example.youtube.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.youtube.R;
import com.example.youtube.Resualt.UserResual;
import com.example.youtube.Retrofit.RetrolClient;
import com.example.youtube.Service.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends ArrayAdapter<UserResual> {
    private Context ct;
    private ArrayList<UserResual> users= new ArrayList<>();
    RetrolClient retrolClient;


    public UserAdapter(@NonNull Context context, int resource, @NonNull final List<UserResual> objects) {
        super(context, resource, objects);
        this.ct = context;
        RetrofitInterface retrofitInterface = retrolClient.getservice();
        Call<List<UserResual>> call = retrofitInterface.executeusers();
        call.enqueue(new Callback<List<UserResual>>() {

            @Override

            public void onResponse(Call<List<UserResual>> call, Response<List<UserResual>> response) {
                List<UserResual> list = response.body();
                for (int i=0;i<list.size();i++){
                    users.add(list.get(i));
//                    Log.d(TAG, "onResponse" + productsList.get(i).toString());
                }

            }

            @Override
            public void onFailure(Call<List<UserResual>> call, Throwable t) {

            }
        });

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.user_item, null);


        }if (users.size()>0){
            UserResual userResual =this.users.get(position);
            TextView nameUser  = convertView.findViewById(R.id.nameUser);
//            TextView tenChap  = convertView.findViewById(R.id.txtTenChap);
//            ImageView imageViewAnhtruyen =convertView.findViewById(R.id.ImgAnhTruyen);

            nameUser.setText(userResual.getName());
//            tenChap.setText(truyen.getTenChap());
//            Glide.with(this.ct).load(truyen.getLinkAnh()).into(imageViewAnhtruyen);


        }
        return convertView;
    }
}
