package com.lr.baselibrary.manager;

import android.app.Dialog;

import java.util.ArrayList;
import java.util.List;

public enum DialogManager {
    DIALOG_MANAGER;

    private static List<Dialog> dialogList = new ArrayList<>();

    public void addDialog(Dialog dialog) {
        dialogList.add(dialog);
    }

    public void dismissDialog(Dialog dialog) {
        if (dialogList.size() > 0 && dialog != null && dialog.isShowing()) {
            if (dialogList.contains(dialog)) {
                dialogList.remove(dialog);
                dialog.dismiss();
            }
        }
    }

    public void dismissAllDialog() {
        for (Dialog dialog : dialogList) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        dialogList.clear();
    }
}
