package com.lr.quartetplatform.moudle1.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.GlideEngine;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.CustomInfoBean;
import com.lr.quartetplatform.bean.params.CustomParams;
import com.lr.quartetplatform.moudle1.adapter.UploadAdapter;
import com.lr.quartetplatform.moudle1.diglog.ChooseTypeDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.HttpParams;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

public class CustomActivity extends BaseMvpActivity<CustomPresenter> {
    private TextView tvTitle, tvProjectType, tvProjectLanguage, tvSubmit, tvProjectCycle;
    private ImageView ivBack;
    private EditText etNeedDes, etContact;
    private RecyclerView rvUpload;
    private ChooseTypeDialog chooseTypeDialog;
    private List<String> language;
    private List<String> timeLimit;
    private List<String> typeList;
    private List<String> imageList = new ArrayList<>();
    private RxPermissions rxPermissions;
    private List<LocalMedia> selectList;
    private UploadAdapter uploadAdapter;
    private HttpParams httpParams = new HttpParams();
    private CustomParams customParams = new CustomParams();

    @Override
    protected CustomPresenter getPresenter() {
        return new CustomPresenter();
    }

    @Override
    protected void initData() {
        rxPermissions = new RxPermissions(this);
        imageList.clear();
        tvTitle.setText(R.string.custom);
        chooseTypeDialog = new ChooseTypeDialog(CustomActivity.this);
        chooseTypeDialog.setOrderClickListener(new ChooseTypeDialog.OrderClickListener() {
            @Override
            public void onSelectorContent(int position, String type) {
                switch (type) {
                    case "1":
                        if (typeList != null && typeList.size() > 0) {
                            String chooseType = typeList.get(position);
                            tvProjectType.setText(chooseType);
                        }
                        break;
                    case "2":
                        if (language != null && language.size() > 0) {
                            String chooseLanguage = language.get(position);
                            tvProjectLanguage.setText(chooseLanguage);
                        }
                        break;
                    case "3":
                        if (timeLimit != null && timeLimit.size() > 0) {
                            String chooseTime = timeLimit.get(position);
                            tvProjectCycle.setText(chooseTime);
                        }
                        break;
                    default:
                }
            }
        });
        uploadAdapter = new UploadAdapter(CustomActivity.this);
        rvUpload.setAdapter(uploadAdapter);
        imageList.add("");
        uploadAdapter.setImageList(imageList);
        uploadAdapter.setClickListener(new UploadAdapter.ClickListener() {
            @Override
            public void addListener(int position) {
                String s = imageList.get(position);
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                PictureSelector.create(CustomActivity.this)
                                        .openGallery(PictureMimeType.ofImage())
                                        .isWeChatStyle(true)
                                        .selectionMode(PictureConfig.SINGLE)
                                        .compress(true)
                                        .compressQuality(90)
                                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                            } else {
                                // 权限被拒绝
                                Toast.makeText(CustomActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void deleteListener(int position) {

            }
        });
        mPresenter.getCustomInfo();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_custom;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        tvProjectType = findViewById(R.id.tvProjectType);
        tvProjectLanguage = findViewById(R.id.tvProjectLanguage);
        tvProjectCycle = findViewById(R.id.tvProjectCycle);
        etNeedDes = findViewById(R.id.etNeedDes);
        rvUpload = findViewById(R.id.rvUpload);
        etContact = findViewById(R.id.etContact);
        tvSubmit = findViewById(R.id.tvSubmit);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvProjectLanguage.setOnClickListener(this);
        tvProjectType.setOnClickListener(this);
        tvProjectCycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSubmit:
                // 提交
                customParams.setDemandcontent(UiTools.getText(etNeedDes));
                customParams.setHomeimage("");
                customParams.setLanguage(UiTools.getText(tvProjectLanguage));
                customParams.setMobile(UiTools.getText(etContact));
                customParams.setTimelimit(UiTools.getText(tvProjectCycle));
                customParams.setType(UiTools.getText(tvProjectType));
                mPresenter.submitCustom(GsonUtils.toJson(customParams));
                break;
            case R.id.tvProjectLanguage:
                // 切换开发语言
                if (language != null && language.size() > 0) {
                    chooseTypeDialog.getDialog(language, "2");
                }
                break;
            case R.id.tvProjectType:
                // 切换项目类型
                if (typeList != null && typeList.size() > 0) {
                    chooseTypeDialog.getDialog(typeList, "1");
                }
                break;
            case R.id.tvProjectCycle:
                // 切换开发周期
                if (timeLimit != null && timeLimit.size() > 0) {
                    chooseTypeDialog.getDialog(timeLimit, "3");
                }
                break;
            default:
        }
    }

    public void setCustomInfo(CustomInfoBean customInfoBean) {
        language = customInfoBean.getLanguage();
        timeLimit = customInfoBean.getTimeLimit();
        typeList = customInfoBean.getType();
        if (language != null && language.size() > 0) {
            tvProjectLanguage.setText(language.get(0));
        }
        if (typeList != null && typeList.size() > 0) {
            tvProjectType.setText(typeList.get(0));
        }
        if (timeLimit != null && timeLimit.size() > 0) {
            tvProjectCycle.setText(timeLimit.get(0));
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

    public void setUploadResult(String imageUrl) {
        if (imageList.size() == 1) {
            imageList.add(0, imageUrl);
        } else {
            imageList.add(imageList.size() - 2, imageUrl);
        }
        uploadAdapter.setImageList(imageList);
    }
}
