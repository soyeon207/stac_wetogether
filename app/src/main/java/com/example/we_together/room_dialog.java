package com.example.we_together;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class room_dialog extends Dialog {

    private Button new_btn,origin_btn;
    private View.OnClickListener new_listener,origin_listener;

    public room_dialog(@NonNull Context context,View.OnClickListener new_listener,View.OnClickListener origin_listener) {
        super(context);

        this.new_listener = new_listener;
        this.origin_listener = origin_listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.activity_room_dialog);

        new_btn = findViewById(R.id.new_room);
        origin_btn=findViewById(R.id.origin_room);

        new_btn.setOnClickListener(new_listener);
        origin_btn.setOnClickListener(origin_listener);

    }
}
