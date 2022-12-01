package com.example.broadcastrec;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.broadcastrec.databinding.FragmentTwoBinding;


public class FragmentTwo extends Fragment {
    FragmentTwoBinding fragmentTwoBinding;
    FragmentThree fragmentThree=new FragmentThree();


    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTwoBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_two,container,false);
        TextView textView=fragmentTwoBinding.textView;
        View view=fragmentTwoBinding.getRoot();
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences= requireActivity().getSharedPreferences("Key", Context.MODE_PRIVATE);
        String value=sharedPreferences.getString("Value","");
        textView.setText(value);

        fragmentTwoBinding.Goto.setOnClickListener(v3->{
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_id,fragmentThree).addToBackStack(" ").commit();
        });
        return view;
    }
}