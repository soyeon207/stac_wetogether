package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class inviteDialog extends Dialog {

    private Button exit_btn;
    private View.OnClickListener exit_listner;
    private TextView dialog_text;
    private String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_invite_dialog);

        exit_btn = findViewById(R.id.dialog_exit);
        dialog_text = findViewById(R.id.dialog_code);

        exit_btn.setOnClickListener(exit_listner);
        dialog_text.setText(code);
    }

    public inviteDialog(@NonNull Context context, View.OnClickListener exit_listner){
        super(context);

        // 저장되어 있는 초대코드를 가져옴
        SharedPreferences SharedPreferences = context.getSharedPreferences("SAVE",Context.MODE_PRIVATE); //
        code =SharedPreferences.getString("invitecode",""); // String에 저장

        this.exit_listner = exit_listner;
    }
}
