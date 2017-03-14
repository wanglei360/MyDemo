package com;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 创建者：wanglei
 * <p>时间：16/8/8  16:58
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class FoldingLineLTextView extends TextView {
    public FoldingLineLTextView(Context context) {
        super(context);
    }

    public FoldingLineLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FoldingLineLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
//                && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY
                && getWidth() > 0
//                && getHeight() > 0
                ) {
            String newText = autoSplitText(this);
            if (!TextUtils.isEmpty(newText)) {
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private String autoSplitText(final TextView tv) {
        ArrayList<String> list = new ArrayList<>();
        final String rawText = tv.getText().toString(); //原始文本
        if (!TextUtils.isEmpty(rawText)) {
            String indentSpace = "     ";
            final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
            final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度
            String[] split = rawText.split("#");
            for (int x = 0; x < split.length; x++) {
                list.add(x == 0 ? split[x] : "\n" + indentSpace + split[x]);
            }
            float lineWidth = 0;
            for (int x = 0; x < list.size(); x++) {
                String str = list.get(x);
                StringBuilder charList = new StringBuilder();
                lineWidth = tvPaint.measureText(str);
                if (lineWidth > tvWidth) {
                    char[] chars1 = str.toCharArray();
                    float newLineWidth = 0;
                    int index = 0;
                    int goTo = 0;
                    float goToNewLineWidth = 0;
                    for (int y = 0; y < chars1.length; y++) {
                        char ch = chars1[y];
                        newLineWidth += tvPaint.measureText(String.valueOf(ch));
                        if (ch == ' ' || ch == ',' || ch == '.') {
                            index = y;
                            goToNewLineWidth = newLineWidth;
                        }
                        if (newLineWidth > tvWidth) {
                            if (goToNewLineWidth < tvWidth / 3) {
                                charList.append("\n" + indentSpace);
                                newLineWidth = tvPaint.measureText(String.valueOf(ch) + indentSpace);
                            } else {
                                charList.insert(index + goTo, "\n" + indentSpace + indentSpace);
                                newLineWidth = tvPaint.measureText(charList.toString().substring(index));
                                goTo += 1 + indentSpace.toCharArray().length + indentSpace.toCharArray().length;
                            }
                        }
                        charList.append(ch);
                    }
                    list.set(x, charList.toString());
                }
            }
        }
        String s1 = "";
        for (String ss : list) {
            s1 += ss;
        }
        return s1;
    }

//    private String autoSplitText(final TextView tv) {
//        final String rawText = tv.getText().toString(); //原始文本
//        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
//        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度
//
//        //将原始文本按行拆分
//        String[] rawTextLines = rawText.replaceAll("\r", "").split("\n");
//        StringBuilder sbNewText = new StringBuilder();
//        for (String rawTextLine : rawTextLines) {
//            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
//                //如果整行宽度在控件可用宽度之内，就不处理了
//                sbNewText.append(rawTextLine);
//            } else {
//                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
//                float lineWidth = 0;
//                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
//                    char ch = rawTextLine.charAt(cnt);
//                    lineWidth += tvPaint.measureText(String.valueOf(ch));
//                    if (lineWidth <= tvWidth) {
//                        sbNewText.append(ch);
//                    } else {
//                        sbNewText.append("\n");
//                        lineWidth = 0;
//                        --cnt;
//                    }
//                }
//            }
//            sbNewText.append("\n");
//        }
//
//        //把结尾多余的\n去掉
//        if (!rawText.endsWith("\n")) {
//            sbNewText.deleteCharAt(sbNewText.length() - 1);
//        }
//
//        return sbNewText.toString();
//    }
}
