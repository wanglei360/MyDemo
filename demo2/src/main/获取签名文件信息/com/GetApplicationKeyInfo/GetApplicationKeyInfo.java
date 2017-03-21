package com.GetApplicationKeyInfo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myplayer.R;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * 创建者：admin
 * <p>时间：2017/3/6 13:32
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class GetApplicationKeyInfo extends Activity {

    private TextView tv, tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_application_key_info);
        tv = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    public void but(View view) {
        ArrayList<String> singInfo = AppSigning.getSingInfo(this.getBaseContext(), AppSigning.MD5);

        String ss = "";

        for (String si : singInfo) {
            ss += si+"  _  ";
        }
//        X509Certificate cert = getSignatures1(this);
//
//        String pubKey = cert.getPublicKey().toString();
//        String signNumber = cert.getSerialNumber().toString();
//        System.out.println("pubKey:" + pubKey);
//        System.out.println("signNumber:" + signNumber);



        tv.setText(ss);
        tv1.setText(getSingInfo(this));
    }

    public static String getSingInfo(Context context) {
        String ss = "";
        try {
            Signature[] signs = getSignatures(context);
            if (signs != null && signs.length > 0) {
                for (Signature sig : signs) {
                    ss += sig+"  _  ";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }

    /**
     * 返回对应包的签名信息
     *
     * @param context
     * @return
     */
    public static Signature[] getSignatures(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 返回对应包的签名信息
     *
     * @param context
     * @return
     */
    public static X509Certificate getSignatures1(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            Signature sign = signatures[0];
            byte[] bytes = sign.toByteArray();
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(bytes));

            String pubKey = cert.getPublicKey().toString();
            String signNumber = cert.getSerialNumber().toString();
            System.out.println("pubKey:" + pubKey);
            System.out.println("signNumber:" + signNumber);

            return cert;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }


}
