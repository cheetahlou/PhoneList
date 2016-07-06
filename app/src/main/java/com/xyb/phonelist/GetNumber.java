package com.xyb.phonelist;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class GetNumber {
    public static List<PhoneBean> phoneBeanLists = new ArrayList<PhoneBean>();

    public static String getNumber(Context context) {
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
        String phoneNumber;
        String phoneName;
        while (cursor.moveToNext()) {
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); //获取手机号码
            phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));//获取号码姓名
            PhoneBean phoneBean = new PhoneBean(phoneName, phoneNumber);
            phoneBeanLists.add(phoneBean);
        }
        return null;
    }
}