package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class inviteDialog extends Dialog {

    private ImageView exit_btn;
    private View.OnClickListener exit_listner,invite_listener;
    private TextView dialog_text;
    private String code;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_invite_dialog);

        share = findViewById(R.id.share);
        exit_btn = findViewById(R.id.dialog_exit);
        dialog_text = findViewById(R.id.dialog_code);

        exit_btn.setOnClickListener(exit_listner);
        dialog_text.setText(code);

        share.setOnClickListener(invite_listener);
    }

    public inviteDialog(@NonNull Context context, View.OnClickListener exit_listner,View.OnClickListener invite_listener){
        super(context);

        // 저장되어 있는 초대코드를 가져옴
        SharedPreferences SharedPreferences = context.getSharedPreferences("SAVE",Context.MODE_PRIVATE); //
        code =SharedPreferences.getString("invitecode",""); // String에 저장

        this.invite_listener = invite_listener;
        this.exit_listner = exit_listner;
    }
}
