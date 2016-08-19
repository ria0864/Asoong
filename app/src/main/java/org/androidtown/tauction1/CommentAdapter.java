package org.androidtown.tauction1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Eun on 2016-08-16.
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CommentData> arrData;
    private LayoutInflater inflater;

    public CommentAdapter(Context c, ArrayList<CommentData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getComment_accomName();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.comment_list, parent, false);
        }

        TextView comment_accomName = (TextView)convertView.findViewById(R.id.comment_accomName);
        comment_accomName.setText(arrData.get(position).getComment_accomName());

        TextView comment_regDay = (TextView)convertView.findViewById(R.id.comment_regDay);
        comment_regDay.setText(arrData.get(position).getComment_regDay());

        TextView comment_price = (TextView)convertView.findViewById(R.id.comment_price);
        comment_price.setText(arrData.get(position).getComment_price());

        TextView comment_content = (TextView)convertView.findViewById(R.id.comment_content);
        comment_content.setText(arrData.get(position).getComment_content());

        Button comment_btn_accomInfo = (Button)convertView.findViewById(R.id.comment_btn_accomInfo);//업체정보 클릭
        comment_btn_accomInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccommodationInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
        Button comment_btn_tel = (Button)convertView.findViewById(R.id.comment_btn_tel);// 전화상담 클릭
        comment_btn_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCall("01030450339");
            }
        });

        return convertView;
    }
    private void startCall(String num) {

        Intent it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));

        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // *앱 권한 주기 참고 http://gun0912.tistory.com/55
            Toast.makeText(context, "휴대전화 권한을 체크해주세요", Toast.LENGTH_SHORT).show();
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(it);
    }

}
