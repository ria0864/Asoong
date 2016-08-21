package org.androidtown.tauction1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class AskContentActivity extends AppCompatActivity {

    ImageButton goFragment2;

    ListView list;
    CommentAdapter adapter;
    ArrayList<CommentData> arrData;

    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_content);

        goFragment2 = (ImageButton) findViewById(R.id.goFragment2);
        goFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new CommentAdapter(this, arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView) findViewById(R.id.list_comment);

        list.setAdapter(adapter);

        //setScroll(list);

        setListViewHeightBasedOnChildren(list);
        mScrollView = (ScrollView) findViewById(R.id.AskCommentScroll);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mScrollView.requestChildFocus(null, linearLayout);
        mScrollView.scrollTo(0, 0);
        mScrollView.invalidate();

    }




    /* setData()함수는 문의내용에 해당하는 댓글들 data 받아오기 */
    private void setData() {//레이아웃 크기에 맞게 글자수 제한할 것
        arrData = new ArrayList<CommentData>();
        arrData.add(new CommentData("설레임펜션", "2016.5.6","300,000원", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new CommentData("서진펜션", "2016.5.6","500,000원", " 안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^.  안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^."));
        arrData.add(new CommentData("설레임펜션", "2016.5.6","300,000원", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new CommentData("서진펜션", "2016.5.6","500,000원", " 안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^.  안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^."));
        arrData.add(new CommentData("서진펜션", "2016.5.6","500,000원", " 안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^.  안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^."));
        arrData.add(new CommentData("서진펜션", "2016.5.6","500,000원", " 안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^.  안녕하세요. 설레임펜션입니다. 2박하신다면 20만원에 해드리겠습니다. 연락주세요^^."));
        arrData.add(new CommentData("설레임펜션", "2016.5.6","300,000원", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight - (listView.getDividerHeight() * (listAdapter.getCount() - 1) * 6);
        Log.d("MyLog", "" + totalHeight);
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}