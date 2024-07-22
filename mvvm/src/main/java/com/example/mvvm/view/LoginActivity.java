package com.example.mvvm.view;

import static com.example.common.utils.UtilsKt.showToast;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.mvvm.bean.User;
import com.example.mvvm.databinding.ActivityLoginBinding;
import com.example.mvvm.state.ResultState;
import com.example.mvvm.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Observer<ResultState<User>> {
    private ActivityLoginBinding viewBinding;
    private LoginViewModel viewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        viewModel = new LoginViewModel();
        initViews();
        observe();
    }

    private void initViews() {
        viewBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = viewBinding.etUsername.getText().toString();
                String password = viewBinding.etPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    showToast("请输入用户名");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                viewModel.login(username, password);
            }
        });
    }

    // 注册观察者
    private void observe() {
        viewModel.getLoginLiveData().observe(this, this);
    }

    @Override
    public void onChanged(ResultState<User> resultState) {
        switch (resultState.getState()) {
            case ResultState.LOADING:
                showLoading();
                break;
            case ResultState.ERROR:
                viewBinding.tvDesc.setText(resultState.getErrMsg());
                hideLoading();
                break;
            case ResultState.SUCCESS:
                hideLoading();
                User user = resultState.getData();
                viewBinding.tvDesc.setText(user.toString());
                break;
        }
    }

    // 取消注册
    private void removeObserve() {
        viewModel.getLoginLiveData().removeObserver(this);
    }

    private void showLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.show();
    }

    private void hideLoading() {
        progressDialog.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObserve();
    }
}
