package com.apps.myapplication;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import androidx.databinding.DataBindingUtil;

import com.apps.myapplication.databinding.SpinnerTaxBinding;

import java.util.List;
import java.util.Locale;


public class SpinnerTaxAdapter extends BaseAdapter {
    private List<BluetoothDevice> dataList;
    private Context context;
    private String lang;
    public SpinnerTaxAdapter(List<BluetoothDevice> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") SpinnerTaxBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.spinner_tax,viewGroup,false);
        binding.setLang(lang);
        binding.setModel(dataList.get(i).getName().toString());
        return binding.getRoot();
    }


}
