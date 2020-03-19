package com.lr.baselibrary.utils;

public class TransferTimeUtils {
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return (days > 9 ? days : "0" + days) + ":" + (hours > 9 ? hours : "0" + hours) + ":" + (minutes > 9 ? minutes : "0" + minutes)  + ":" + (second > 9 ? second : "0" + second);
        } else {
            return (hours > 9 ? hours : "0" + hours) + ":" + (minutes > 9 ? minutes : "0" + minutes) + ":" + (second > 9 ? second : "0" + second);
        }
    }
}
