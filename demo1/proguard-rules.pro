# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-ignorewarnings
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontwarn org.springframework.**
-dontwarn com.nostra13.universalimageloader.**
-dontwarn com.hp.hpl.sparta.**
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own). banduzb
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class me.bandu.talk.android.phone.bean.*{*;}

-keep class com.tencent.mm.sdk.** {
   *;
}

-keep public class com.bandu.*
-keep public class * extends com.bandu.bean.BaseBean{
   void set*(***);
   *** get*();
 }
 -keep public class com.chivox.*{
	native <methods>;
 }
 -keepclassmembers class com.chivox.* {
	public static int *;

}

-keepclassmembers class ** {
    public void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep class com.chivox.** { *; }
-keep public class * extends me.bandu.talk.android.phone.activity.BaseAppCompatActivity
-keep public class * extends me.bandu.talk.android.phone.fragment.BaseFragment
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.preference.Preference
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class me.bandu.talk.android.phone.R$*{
	 public static final int *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
    public static final int *;
    public static abstract interface *(***);
}

-keep class com.google.**{*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


# 保证所有的jar不混淆
#-libraryjars libs/android-async-http-1.4.5.jar
#-libraryjars libs/date4j.jar
#-libraryjars libs/androidannotations-api-3.3.1.jar
#-libraryjars libs/eventbus.jar
#-libraryjars libs/httpUtils.jar
#-libraryjars libs/gson-2.2.4.jar
#-libraryjars libs/umeng-analytics-v5.4.2.jar
#-libraryjars libs/universal-image-loader-1.9.3.jar
#-libraryjars libs/umeng-update-v2.6.0.1.jar

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson

#友盟统计   包下的所有类不要混淆，包括类里面的方法
-keep class com.umeng.** { *; }
#注解          包下的所有内容不要混淆，ViewUitls有用到
-keep class * extends java.lang.annotation.Annotation.** { *; }

#-libraryjars libs/android-support-v4.jar
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep class android.support.v4.app.** { *; }
-keep class android.support.v4.media.** { *; }
-keep class android.support.v4.media.session.** { *; }
-keep class android.support.v4.content.** { *; }
-keep class android.support.v4.graphics.** { *; }
-keep class android.support.v4.** { *; }

-dontwarn android.support.v4.**
-dontwarn android.support.v4.app.**
-dontwarn android.support.v4.media.**
-dontwarn android.support.v4.media.session.**
-dontwarn android.support.v4.content.**
-dontwarn android.support.v4.graphics.**
 #  ######## butterknife混淆  ##########
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

 #  ######## 友盟推送混淆  ##########
-keep class okio.** {*;}
-keep class com.squareup.wire.** {*;}
-keep class com.umeng.message.protobuffer.* {
	 public <fields>;
     public <methods>;
}
-keep class com.umeng.message.* {
	 public <fields>;
     public <methods>;
}
-keep class org.android.agoo.impl.* {
	 public <fields>;
     public <methods>;
}
-keep class org.android.agoo.service.* {*;}
-keep class org.android.spdy.**{*;}
-keep public class me.bandu.talk.android.phone.R$*{
    public static final int *;
}

 #  ######## universalimageloader混淆  ##########
 -dontwarn com.nostra13.universalimageloader.**

 -keep class com.nostra13.universalimageloader.** { *; }

 #  ######## greenDao混淆  ##########
 -keep class de.greenrobot.dao.** {*;}
 -keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
     public static java.lang.String TABLENAME;
 }
 -keep class **$Properties


-keep class * extends android.os.AsyncTask{
    public Interger <doInBackground>(...);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
   void set*(***);
   *** get*();
}
-keepclassmembers class * extends me.bandu.talk.android.phone.bean.BaseBean
-keepclassmembers class  me.bandu.talk.android.phone.bean.*

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#没有的话object中含有其他对象的字段的时候会抛出ClassCastException
-keepattributes Signature


#比如我们要向activity传递对象使用了Serializable接口的时候，这时候这个类及类里面的所有内容都不能混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable { *; }


# Activity下的所有onClick方法不会混淆
-keepclassmembers class * extends android.app.Activity {
   	public void *(android.view.View);
}

#Fragment下的所有onClick方法不会混淆
-keepclassmembers class * extends android.app.Fragment {
 	public void *(android.view.View);
}

#Fragment下的所有onClick方法不会混淆
-keepclassmembers class * extends android.support.v4.app.Fragment {
 	public void *(android.view.View);
}

