package com.ragerpie.ayi.ragerpie.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by WangBo on 2016/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        initVariable();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    protected abstract void initVariable();

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract int getLayoutResId();

    public void showToast(String message) {
        if (message != null){
            toast.setText(message);
            toast.show();
        }

    }
}
