package com.lr.quartetplatform.moudle4;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.quartetplatform.GlideEngine;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle4.presenter.SettingPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.HttpParams;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;

public class SettingActivity extends BaseMvpActivity<SettingPresenter> {
    private ImageView ivBack;
    private TextView tvSetAvatar, tvSetNickName, tvSetSignature, tvSetPass, tvExit;
    private RxPermissions rxPermissions;
    private List<LocalMedia> selectList;
    private HttpParams httpParams;

    @Override
    protected SettingPresenter getPresenter() {
        return new SettingPresenter();
    }

    @Override
    protected void initData() {
        rxPermissions = new RxPermissions(this);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.ivBack);
        tvSetAvatar = findViewById(R.id.tvSetAvatar);
        tvSetNickName = findViewById(R.id.tvSetNickName);
        tvSetSignature = findViewById(R.id.tvSetSignature);
        tvSetPass = findViewById(R.id.tvSetPass);
        tvExit = findViewById(R.id.tvExit);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSetAvatar.setOnClickListener(this);
        tvSetNickName.setOnClickListener(this);
        tvSetSignature.setOnClickListener(this);
        tvSetPass.setOnClickListener(this);
        tvExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSetAvatar:
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                PictureSelector.create(SettingActivity.this)
                                        .openGallery(PictureMimeType.ofImage())
                                        .isWeChatStyle(true)
                                        .selectionMode(PictureConfig.SINGLE)
                                        .compress(true)
                                        .compressQuality(90)
                                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                            } else {
                                // 权限被拒绝
                                Toast.makeText(SettingActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.tvSetNickName:

                break;
            case R.id.tvSetSignature:

                break;
            case R.id.tvSetPass:

                break;
            case R.id.tvExit:

                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {
            selectList = PictureSelector.obtainMultipleResult(data);
            LocalMedia localMedia = selectList.get(0);
            String path = localMedia.getPath();
            httpParams.put("file", new HttpParams.FileWrapper(new File(path), localMedia.getFileName(), MediaType.parse(localMedia.getMimeType())));
            mPresenter.uploadImage(httpParams);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
