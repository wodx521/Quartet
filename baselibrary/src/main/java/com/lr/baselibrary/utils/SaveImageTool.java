package com.lr.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.lr.baselibrary.application.GlobalApplication;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveImageTool {
    @SuppressLint("StaticFieldLeak")
    private static Context context = GlobalApplication.getContext();

    /**
     * 这个方法只能保存view中显示的绘制图片，图片不是原图
     *
     * @param view     要保存的view
     * @param filePath 存储文件路径
     */
    public static void saveBitmapCache(View view, String filePath) {
        //开启绘制缓存
        view.setDrawingCacheEnabled(true);
        Bitmap imageBitmap = view.getDrawingCache();
        FileOutputStream outStream = null;
        File file = new File(filePath);
        //如果是目录不允许保存
        if (file.isDirectory()) {
            return;
        }
        try {
            outStream = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            imageBitmap.recycle();
            //关闭绘制缓存
            view.setDrawingCacheEnabled(false);
        }
    }

    /**
     * 只能保存ImageView中的图片,且只能获取静态图
     *
     * @param imageView 要保存图片的ImageView
     * @param filePath  文件路径
     */
    public static void saveImageViewBitmap(ImageView imageView, String filePath) {
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return;
        }
        FileOutputStream outStream = null;
        File file = new File(filePath);
        // 如果是目录不允许保存
        if (file.isDirectory()) {
            return;
        }
        Bitmap bitmap = null;
        try {
            outStream = new FileOutputStream(file);
            Drawable current = drawable.getCurrent();
            bitmap = ((BitmapDrawable) current).getBitmap();
            if (!bitmap.isRecycled()) {
                boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                if (compress) {
                    UiTools.showToast("已成功保存" + filePath);
                } else {
                    UiTools.showToast("保存失败");
                }
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        }
    }

    /**
     * 可保存所有view显示的内容,讲view内容绘制成bitmap并保存
     *
     * @param view     要保存图片的View
     * @param filePath 文件路径
     */
    public static void saveViewBitmap(View view, String filePath) {
        // 创建对应大小的bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        //存储
        FileOutputStream outStream = null;
        File file = new File(filePath);
        //如果是目录不允许保存
        if (file.isDirectory()) {
            return;
        }
        try {
            outStream = new FileOutputStream(file);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            if (compress) {
                UiTools.showToast("已成功保存" + filePath);
            } else {
                UiTools.showToast("保存失败");
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bitmap.recycle();
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        }
    }

    /**
     * 通过MediaStore保存，兼容AndroidQ，保存成功自动添加到相册数据库，无需再发送广告告诉系统插入相册
     *
     * @param context      context
     * @param sourceFile   源文件
     * @param saveFileName 保存的文件名
     * @param saveDirName  picture子目录
     * @return 成功或者失败
     */
    public static boolean saveImageWithAndroidQ(Context context,
                                                File sourceFile,
                                                String saveFileName,
                                                String saveDirName) {
//        String extension = BitmapUtil.getExtension(sourceFile.getAbsolutePath());

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image");
        values.put(MediaStore.Images.Media.DISPLAY_NAME, saveFileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.TITLE, "Image.png");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + saveDirName);

        Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = context.getContentResolver();

        Uri insertUri = resolver.insert(external, values);
        BufferedInputStream inputStream = null;
        OutputStream os = null;
        boolean result = false;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            if (insertUri != null) {
                os = resolver.openOutputStream(insertUri);
            }
            if (os != null) {
                byte[] buffer = new byte[1024 * 4];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.flush();
            }
            result = true;
        } catch (IOException e) {
            result = false;
        } finally {
            try {
                if (os!=null) {
                    os.close();
                }
                if (inputStream!=null) {
                    inputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void saveImage(Bitmap bitmap, String filePath) {
        File storageDir = new File(filePath);
        FileOutputStream outStream = null;
        if (storageDir.isDirectory()) {
            return;
        }
        try {
            outStream = new FileOutputStream(storageDir);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            if (compress) {
                UiTools.showToast("已成功保存" + filePath);
            } else {
                UiTools.showToast("保存失败");
            }
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bitmap.recycle();
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            Uri uri = Uri.fromFile(storageDir);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        }
    }
}
