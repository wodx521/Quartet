package com.lr.quartetplatform.moudle4.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle1.diglog.ChooseTypeDialog;
import com.lr.quartetplatform.moudle4.presenter.MyJoinPresenter;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

public class MyJoinActivity extends BaseMvpActivity<MyJoinPresenter> {
    private TextView tvTitle, tvType, tvSubmit;
    private ImageView ivBack;
    private EditText etCompanyName, etContact, etContactPhone;
    private List<String> mTypeList;
    private ChooseTypeDialog chooseTypeDialog;
    private HttpParams httpParams = new HttpParams();

    @Override
    protected MyJoinPresenter getPresenter() {
        return new MyJoinPresenter();
    }

    @Override
    protected void initData() {
        chooseTypeDialog = new ChooseTypeDialog(MyJoinActivity.this);
        chooseTypeDialog.setOrderClickListener(new ChooseTypeDialog.OrderClickListener() {
            @Override
            public void onSelectorContent(int position, String type) {
                switch (type) {
                    case "1":
                        if (mTypeList != null && mTypeList.size() > 0) {
                            String chooseType = mTypeList.get(position);
                            tvType.setText(chooseType);
                        }
                        break;
                    default:
                }
            }
        });
        mPresenter.getJoinType();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_my_join;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        etCompanyName = findViewById(R.id.etCompanyName);
        etContact = findViewById(R.id.etContact);
        etContactPhone = findViewById(R.id.etContactPhone);
        tvType = findViewById(R.id.tvType);
        tvSubmit = findViewById(R.id.tvSubmit);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvType.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvType:
                if (mTypeList != null && mTypeList.size() > 0) {
                    chooseTypeDialog.getDialog(mTypeList, "1");
                }
                break;
            case R.id.tvSubmit:
                String contact = UiTools.getText(etContact);
                String contactPhone = UiTools.getText(etContactPhone);
                String companyName = UiTools.getText(etCompanyName);
                String projectType = UiTools.getText(tvType);

                if (UiTools.noEmpty(contact, contactPhone, companyName, projectType)) {
                    httpParams.put("company", companyName);
                    httpParams.put("username", contact);
                    httpParams.put("mobile", contactPhone);
                    httpParams.put("companytype", projectType);
                    mPresenter.getJoinApply(httpParams);
                } else {
                    if (!UiTools.noEmpty(contact)) {
                        UiTools.showToast(R.string.inputContactName);
                    } else if (!UiTools.noEmpty(contactPhone)) {
                        UiTools.showToast(R.string.inputContactPhone);
                    } else if (!UiTools.noEmpty(companyName)) {
                        UiTools.showToast(R.string.inputCompanyName);
                    } else if (!UiTools.noEmpty(projectType)) {
                        UiTools.showToast(R.string.chooseProjectType);
                    }
                }
                break;
            default:
        }
    }

    public void setTypeList(List<String> typeList) {
        this.mTypeList = typeList;
        if (mTypeList != null && mTypeList.size() > 0) {
            String typeInfo = mTypeList.get(0);
            tvType.setText(typeInfo);
        }
    }

    public void setJoin() {
        finish();
    }
}
