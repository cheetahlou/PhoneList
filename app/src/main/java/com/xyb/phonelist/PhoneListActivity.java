package com.xyb.phonelist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xyb.phonelist.PhoneAdapter.MyListener;
import com.xyb.phonelist.PhoneAdapter.SMSListener;


public class PhoneListActivity extends AppCompatActivity {


    private ListView phonelist;
    private PhoneAdapter phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        GetNumber.getNumber(this);

        phonelist=(ListView)findViewById(R.id.phonelist);

        //设置adapter
        phoneAdapter = new PhoneAdapter(GetNumber.phoneBeanLists,this,mListener,smsListener);
        phonelist.setAdapter(phoneAdapter);

        phonelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String num=phoneAdapter.getPhoneBeanLists().get(position).getPhoneNumber().toString();

                Intent callitt=new Intent();
                callitt.setAction(Intent.ACTION_CALL);
                callitt.setData(Uri.parse("tel:"+num));
                startActivity(callitt);
                //Toast.makeText(PhoneListActivity.this,num,Toast.LENGTH_SHORT).show();
             /*  Intent callitt=new Intent();
                callitt.setAction(Intent.ACTION_CALL);
                callitt.setData(Uri.parse("tel:15700083995"));
                startActivity(callitt);*/
               /*  String srr="http://www.meizu.com";
                Uri uri=Uri.parse(srr);

                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);*/
            }
        });


    }
    /*
    *实现类，响应按钮点击事件
    */

    private MyListener mListener = new MyListener() {
                 @Override
                public void myOnClick(int position, View v) {
                         Toast.makeText(PhoneListActivity.this, "listview的内部的按钮被点击了！，位置是-->" + position , Toast.LENGTH_SHORT).show();
                     String num=phoneAdapter.getPhoneBeanLists().get(position).getPhoneNumber().toString();


                     Intent callitt=new Intent();
                     callitt.setAction(Intent.ACTION_CALL);
                     callitt.setData(Uri.parse("tel:"+num));
                     startActivity(callitt);


                    }
           };
    private SMSListener smsListener=new SMSListener() {
        @Override
        public void myOnClick(int position, View v) {
            String num=phoneAdapter.getPhoneBeanLists().get(position).getPhoneNumber().toString();
            Uri uri = Uri.parse("smsto:"+num);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "");
            startActivity(it);
        }
    };



}
