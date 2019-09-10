package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class inviteDialog extends Dialog {

    private Button exit_btn;
    private View.OnClickListener exit_listner;

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
        exit_btn.setOnClickListener(exit_listner);

    }

    public inviteDialog(@NonNull Context context, View.OnClickListener exit_listner){
        super(context);
        this.exit_listner = exit_listner;
    }
}
