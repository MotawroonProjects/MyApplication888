package com.apps.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.apps.myapplication.databinding.ActivityMain2Binding;
import com.royole.drawinglib.Constant;
import com.royole.drawinglib.RyDrawingManager;
import com.royole.drawinglib.interfaces.IDrawingBusinessListener;
import com.royole.drawinglib.interfaces.IDrawingDataListener;
import com.royole.drawinglib.interfaces.IDrawingServiceConnectionListener;
import com.royole.drawinglib.interfaces.IPushEventListener;
import com.royole.drawinglib.interfaces.IScanListener;
import com.royole.drawinglib.interfaces.ISecurityConnListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity  implements IScanListener, ISecurityConnListener, IPushEventListener, IDrawingDataListener, IDrawingBusinessListener, IDrawingServiceConnectionListener {

    private SpinnerTaxAdapter spinnerTaxAdapter;
    private RyDrawingManager aRyDrawingManager;
    private List<BluetoothDevice> mDevDatalist;
    private final String bluthoos_perm = Manifest.permission.BLUETOOTH;
    private final String bluthoosadmin_perm = Manifest.permission.BLUETOOTH_ADMIN;

    private final int bluthoos_req = 200;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        initView();

    }

    private void initView() {
        mDevDatalist = new ArrayList<>();
        spinnerTaxAdapter = new SpinnerTaxAdapter(mDevDatalist, this);
        binding.textviewFirst.setAdapter(spinnerTaxAdapter);
        binding.textviewFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BluetoothDevice device = mDevDatalist.get(i);
                aRyDrawingManager.connectDevice(device);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
      //  checkBluthoosPermission();
        initManager();

    }
    private void checkBluthoosPermission() {

        if (ContextCompat.checkSelfPermission(this, bluthoos_perm) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, bluthoosadmin_perm) != PackageManager.PERMISSION_GRANTED) {

            Log.e(";d;;d;", "ssssss");

            ActivityCompat.requestPermissions(this, new String[]{bluthoos_perm, bluthoosadmin_perm}, bluthoos_req);


        } else {
            Log.e(";d;;d;", "lslsllsl");

            aRyDrawingManager.startScanRyDrawingDevice(2000);


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == bluthoos_req && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            aRyDrawingManager.startScanRyDrawingDevice(2000);
        }
    }

    private void initManager() {
        aRyDrawingManager = RyDrawingManager.getInstance();
        aRyDrawingManager.init(this);
        aRyDrawingManager.setLeScanListener(this);
        aRyDrawingManager.setPushEventListener(this);
        aRyDrawingManager.setDrawingDataListener(this);
        aRyDrawingManager.setDrawingBusinessListener(this);
        aRyDrawingManager.setRyDrawingServiceConnectionListener(this);
        aRyDrawingManager.setSecurityConnListener(this);
        startscan();
    }

    private void startscan() {
        if (aRyDrawingManager.isSupportBle()) {
            if (aRyDrawingManager.isBluetoothEnable()) {
                checkBluthoosPermission();
                
            } else {
                checkBluthoosPermission();
            }
        } else {
            Log.e(";d;;d;", "ssddkkdkdkk");
            checkBluthoosPermission();
        }
    }

    @Override
    public void onLeScanStart() {

    }

    @Override
    public void onLeScanEnd() {

    }

    @Override
    public void onDeviceFound(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {

        if (mDevDatalist.contains(bluetoothDevice)) {
            Log.e("s;s;lslslsl",bluetoothDevice.getAddress().toString());
            return;
        }
        Log.e("s;s;lslslsl",bluetoothDevice.getName().toString());

        mDevDatalist.add(bluetoothDevice);
        spinnerTaxAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBluetoothConnectionStateChange(int i, int i1) {

    }

    @Override
    public void onRequestSecurityStateResponse(int i, int i1) {

    }

    @Override
    public void onRequestBindDeviceResponse(int i, int i1) {

    }

    @Override
    public void onRequestBindUidResponse(int i, int i1) {

    }

    @Override
    public void onRequestBindPwdResponse(int i, int i1) {

    }

    @Override
    public void onRequestVerifyUidResponse(int i, int i1) {

    }

    @Override
    public void onRequestVerifyPwdResponse(int i, int i1) {

    }

    @Override
    public void onRequestUnbindDeviceResponse(int i, int i1) {

    }

    @Override
    public void onRequestSetNicknameResponse(int i, int i1) {

    }

    @Override
    public void onRequestTimeoutDisconnectResponse(int i, int i1) {

    }

    @Override
    public void onRequestModifyPwdResponse(int i, int i1) {

    }

    @Override
    public void onAcknowledgeModifyPwdResponse(int i, int i1) {

    }

    @Override
    public void onStorageFullEvent() {

    }

    @Override
    public void onStorageLowEvent(int i, int i1, int i2) {

    }

    @Override
    public void onSavePageEvent(int i, int i1) {

    }

    @Override
    public void onLowPowerEvent(int i) {

    }

    @Override
    public void onClearPageEvent(int i) {

    }

    @Override
    public void onCreateNewPageEvent(int i, int i1) {

    }

    @Override
    public void onButtonAClick() {

    }

    @Override
    public void onButtonBClick() {

    }

    @Override
    public void onFormatFlashDone() {

    }

    @Override
    public void onDrawingDataChange(int i, int i1, int i2, long l) {

    }

    @Override
    public void onDrawingWorkModeChange(int i, int i1) {

    }

    @Override
    public void onGetDeviceWorkingModeResponse(int i, int i1) {

    }

    @Override
    public void onGetStorageInfoResponse(int i, int i1, int i2, int i3) {

    }

    @Override
    public void onGetPageDataResponse(int i, byte[] bytes, int i1, int i2) {

    }

    @Override
    public void onGetPageSizeResponse(int i, int i1, int i2) {

    }

    @Override
    public void onRemovePageResponse(int i, int i1) {

    }

    @Override
    public void onRemoveAllPageResponse(int i) {

    }

    @Override
    public void onGetBatteryInfoResponse(int i, int i1, int i2) {

    }

    @Override
    public void onGetDeviceVersionResponse(int i, String s, String s1, int i1, int i2) {

    }

    @Override
    public void onCancelSyncFileResponse(int i, int i1) {

    }

    @Override
    public void onCreateNewPageResponse(int i, int i1) {

    }

    @Override
    public void onClearCurrentPageDataResponse(int i, int i1) {

    }

    @Override
    public void onSwitchDFUModeResponse(int i) {

    }

    @Override
    public void onGetDeviceNameResponse(int i, String s) {

    }

    @Override
    public void onSetDeviceNameResponse(int i, String s) {

    }

    @Override
    public void onGetDataTransTypeResponse(int i, int i1) {

    }

    @Override
    public void onChangeToSleepModeResponse(int i) {

    }

    @Override
    public void onFormatFlashResponse(int i) {

    }

    @Override
    public void onNoDeviceFoundByMacError(String s) {

    }

    @Override
    public void onDrawingServiceNotFoundError() {

    }

    @Override
    public void onConnectDeviceByNameTimeout(String s) {

    }

    @Override
    public void onDrawingServiceStateChange(int i, int i1) {
        if (i1 == Constant.ServiceConnectionState.STATE_CONNECTED) {
            Toast.makeText(this, "sssss", Toast.LENGTH_LONG).show();
            if (aRyDrawingManager.isLeScanning()) {
                aRyDrawingManager.stopLeScan();
            }
        }

    }

    @Override
    public void onDrawingServiceConnectError(int i) {

    }
}