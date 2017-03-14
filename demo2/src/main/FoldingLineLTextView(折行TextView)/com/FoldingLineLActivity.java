package com;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/8  16:58
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class FoldingLineLActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folding_line_layout);
        FoldingLineLTextView mText = (FoldingLineLTextView) findViewById(R.id.tv);
//        TextView mText = (TextView) findViewById(R.id.tv);
//        String sp = "3. A. Yes, she was. Yes, she was. Yes, she was. Yes, she was. #B. She is a tall and beautiful woman. She is a tall and beautiful woman. She is a tall and beautiful woman. #C. Yes, she is.";
        String sp = "1. 你向林涛介绍一位新来的外国小朋友Jim，应怎样介绍你向林涛介绍一位新来的外国小朋友Jim，应怎样介绍你向林涛介绍一位新来的外国小朋友Jim，应怎样介绍? #A. Jim, this is Lin Tao.this is Lin Tao.this is Lin Tao.this is Lin Tao.this is Lin Tao. #B. Lin Tao, he is Jim. #C. Lin Tao, this is Jim.";
        String[] s = sp.split("#");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                sb.append(s[i] + "\r\n");
            } else {
                sb.append("     "+s[i] + "\r\n");
            }
        }
        String text = sb.toString();

        mText.setText(sp);
    }
}