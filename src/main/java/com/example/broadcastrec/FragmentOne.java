package com.example.broadcastrec;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.broadcastrec.databinding.ActivityMainBinding;
import com.example.broadcastrec.databinding.FragmentOneBinding;


public class FragmentOne extends Fragment {
    String  TAG="Android:";
    ActivityMainBinding binding;
    FragmentOneBinding fragmentOneBinding;
    EditText editText;
    Music music=new Music();
    boolean isBound=false;

    public FragmentOne() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    fragmentOneBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_one,container,false);
        // Inflate the layout for this fragment
       View view=fragmentOneBinding.getRoot();
//        Contacts contacts=new Contacts("Kartik");
//        fragmentOneBinding.se
       editText=fragmentOneBinding.edt;

       FragmentTwo fragmentTwo=new FragmentTwo();

       fragmentOneBinding.Send.setOnClickListener(v1->{
           String value=editText.getText().toString();
           SharedPreferences sharedPreferences= requireActivity().getSharedPreferences("Key", Context.MODE_PRIVATE);
           SharedPreferences.Editor editor=sharedPreferences.edit();
           editor.putString("Value",value);
           editor.apply();
           requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id,fragmentTwo).addToBackStack("").commit();

       });


       fragmentOneBinding.Save.setOnClickListener(v2->{
           Log.d(TAG, "onCreateView: ");
           requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id,fragmentTwo).addToBackStack("").commit();

       });

       fragmentOneBinding.Start.setOnClickListener(v1->{
           Log.d(TAG, "onCreateView: ");
           Intent intent=new Intent(getActivity(),Music.class);

          // getActivity().startService(intent);
           requireActivity().bindService(intent,Myserviceconn,Context.BIND_AUTO_CREATE);
       });
       fragmentOneBinding.Stop.setOnClickListener(v2->{
           requireActivity().unbindService(Myserviceconn);
       });
        return view;

    }
    private ServiceConnection Myserviceconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Music.MyBinder myBinder=(Music.MyBinder) service;
            music= myBinder.getService();
            Log.d(TAG, "onServiceConnected: "+ music);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;

        }
    };
}