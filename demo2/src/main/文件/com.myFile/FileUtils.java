package com.myFile;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 创建者：wanglei
 * <p>时间：16/7/4  09:38
 * <p>类描述：todo 这里所有使用到的JavaBean必须序列化否则会异常
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class FileUtils {

    /**
     * 文件序列化对象
     *
     * @param context  上下文
     * @param clazz    被序列化成对象的字节码
     * @param fileName 需要序列化的文件的名字
     * @return 返回传入的字节码的对象，失败返回null。
     */
    public static <T> T fileToBean(Context context, Class<T> clazz, String fileName) {
        try {
            File file1 = new File(getFilePath(context).getAbsoluteFile() + "/" + fileName);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
            ois.readUTF();
            T bean = (T) clazz.newInstance();
            bean = (T) ois.readObject();
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象序列化文件
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param o        需要序列化成文件的对象
     * @return true序列化成功，false序列化失败
     */
    public static boolean beanToFile(Context context, String fileName, Object o) {
        try {
            File filePath = getFilePath(context);
            File file = new File(filePath.getAbsoluteFile() + "/" + fileName);
            objectToFile(o, file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取   data/data/包名/files/Constants.FILEPATHNAME   的路径
     *
     * @param context 上下文
     * @return 返回需要的路径
     */
    public static File getFilePath(Context context) {
        String path = context.getFilesDir().getAbsolutePath();
        File folder = new File(path + "/" + Constants.FILEPATHNAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    /**
     * 将对象转换成文件
     *
     * @param object 要序列换成文件的对象
     * @param file   缓存文件
     */
    public static void objectToFile(Object object, File file) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeUTF("UTF-8");
        oos.writeObject(object);
        oos.close();
    }

}
