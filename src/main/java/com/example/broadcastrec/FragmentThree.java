package com.example.broadcastrec;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.broadcastrec.databinding.FragmentThreeBinding;

public class FragmentThree extends Fragment {
    String TAG = "Frag";
    FragmentThreeBinding binding;
    int no = 0;

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false);
        View view = binding.getRoot();

        binding.btnThree.setOnClickListener(v3 -> {
            if (!binding.textThree.getText().toString().isEmpty()) {
                no = Integer.parseInt(binding.textThree.getText().toString());
            } else {
                binding.TextView3.setText(R.string.is_empty);

            }
            StringBuilder sb = new StringBuilder();
            sb.append("");
            if (no > 0) {
                for (int i = 1; i <= no; i++) {
                    for (int j = no; j >= i; j--) {
                        sb.append(j);
                    }
                    sb.append("\n");
                }

                binding.TextView3.setText(sb);
            } else
                sb.append("");

        });

        return view;
    }
}