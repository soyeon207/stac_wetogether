package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class add_dialog extends Dialog {

    private ImageView exit_btn;
    private View.OnClickListener exit_listner;

    public add_dialog(@NonNull Context context, View.OnClickListener exit_listner) {
        super(context);

        this.exit_listner = exit_listner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_add_dialog);

        exit_btn = findViewById(R.id.add_close);

        exit_btn.setOnClickListener(exit_listner);
    }
}
