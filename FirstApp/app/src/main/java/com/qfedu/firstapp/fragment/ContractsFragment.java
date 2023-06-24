package com.qfedu.firstapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.activity.DialogActivity;

/**
 * 联系人（通讯录）碎片Fragment
 */
public class ContractsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contracts, container, false);
        Button button = root.findViewById(R.id.btn_opendialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return root;
    }

    private void openDialog() {
        Intent intent = new Intent(getContext(), DialogActivity.class);
        getContext().startActivity(intent);
    }

}