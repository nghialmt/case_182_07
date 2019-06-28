package com.example.faceshop;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faceshop.Retrofit.IAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IAPI api;

    Button btnReset;
    EditText edtEmail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectFirebase();
        initAPI();
        mapping();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Làm ơn nhập email !", Toast.LENGTH_SHORT).show();
                }else{
                    if(email.trim().matches(emailPattern)) {
                        new FancyGifDialog.Builder(MainActivity.this)
                                .setTitle("Reset password")
                                .setMessage("Trường hợp nếu bạn muốn thay đổi mật khẩu thì bạn sẽ xác nhận tại địa chỉ email của bạn và làm theo hướng dẫn. Bạn có thể xác nhận thành công khi có thông từ notification !")
                                .setNegativeBtnText("Cancel")
                                .setPositiveBtnBackground("#FF4081")
                                .setPositiveBtnText("Ok")
                                .setNegativeBtnBackground("#FFA9A7A8")
                                .setGifResource(R.drawable.gif7)
                                .isCancellable(true)
                                .OnPositiveClicked(new FancyGifDialogListener() {
                                    @Override
                                    public void OnClick() {
                                       resetPassword(email);
                                    }
                                })
                                .OnNegativeClicked(new FancyGifDialogListener() {
                                    @Override
                                    public void OnClick() {
                                        Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .build();


                    }else{
                        Toast.makeText(MainActivity.this, "Địa chỉ email không hợp lệ !", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }


    private void connectFirebase() {
        FirebaseApp.initializeApp(this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Log.e("Kiem tra",task.toString());
                    return;
                }
                String token = task.getResult().getToken();

                Log.d("Kiem tra",token);
            }
        });
    }

    private void mapping() {
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnReset = (Button) findViewById(R.id.btnReset);
    }

    void resetPassword(String email){
        final AlertDialog dialog = new SpotsDialog(MainActivity.this);
        dialog.show();
        compositeDisposable.add(api.resetPassword(ApiUtils.key,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userModel ->{
                            if(userModel.isSuccess()){
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "Bạn hãy kiểm tra gmail của bạn để xác nhận việc đổi mật khẩu !", Toast.LENGTH_SHORT).show();
                            }else{
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "Error :"+userModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable ->
                                { dialog.dismiss();
                                Toast.makeText(this, "Throwable :"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }));
    }

    private void initAPI() {
        try {
            api = ApiUtils.getSOService();
        }catch (Exception ex){
            Toast.makeText(this, "Error :"+ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
