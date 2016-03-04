package com.tiny.tinyimageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.Random;

/**
 * Created JackLuo
 * 实现主要功能：
 * 创建时间： on 2016/3/4.
 * 修改者： 修改日期： 修改内容：
 */
public class LoadImageHelper {

    private static final String TAG = "LoadImageHelper";

    /**
     * 加载头像---------存在快速滑动时，如果下载速度很快，会导致复用性问题使头像错乱
     *
     * @param context APP上下文
     * @param target  要显示图片的ImageView
     * @param userId  用户id
     * @param headUrl 头像地址
     */
    public synchronized static void loadHead(final Context context, final ImageView target, final long userId, final String headUrl) {
        target.setTag(R.id.img_head, null);
        if (isEmpty(headUrl)) {
            target.setImageResource(R.drawable.default_photo);
            return;
        }
//        String headPath = getContactHeadPath(context, userId);
//        final File file = new File(headPath);
        //final float dimension = context.getResources().getDimension(R.dimen.head_photo_small);
        //final int headSize = dp2px(context, dimension);
//        if (file.exists()) {
//            Picasso.with(context).load(file).error(R.drawable.default_photo).placeholder(R.drawable.default_photo)
//                    .resize(headSize, headSize).centerInside()
//                    .config(Bitmap.Config.RGB_565).into(target);
//        } else {// 头像在本地不存在，去服务器下载

        final long beginTime = System.currentTimeMillis();
        RequestCreator info = Picasso.with(context)
                .load(headUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .tag(context)
                .resizeDimen(R.dimen.head_photo_small, R.dimen.head_photo_small).centerCrop();
        info.into(target, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "------图片加载用时：" + (System.currentTimeMillis() - beginTime));
            }

            @Override
            public void onError() {
                Log.d(TAG, "-----图片加载失败");
            }
        });

//            target.setImageResource(R.drawable.default_photo);
//            if (!NetUtils.isNetworkConnected(context)) {
//                return;
//            }
//            // 下载头像
//            MainApplication app = (MainApplication) context.getApplicationContext();
//            ServerInfo serverInfo = app.getServerInfo();
//            if (serverInfo == null) {
//                return;
//            }
//            StringBuilder url = new StringBuilder("http://");
//            url.append(serverInfo.fileAccessIp).append(":").append(serverInfo.fileAccessPort).append("/").append(headUrl);
//            target.setTag(R.id.img_head, url.toString());
//            HttpContactManager.downloadHeadAsync(url.toString(), headPath, new DefaultCallback() {
//                @Override
//                public void onSuccess(Object object) {
//                    String reqUrl = (String) object;
//                    String tagUrl = (String) target.getTag(R.id.tag_key_head_url);
//                    Log.d(TAG, "------loadHead.target = " + target + " ,userId = " + userId + " ,reqUrl = " + reqUrl + " ,tagUrl = " + tagUrl);
//                    if (reqUrl.equals(tagUrl)) {
//                        final long beginTime = System.currentTimeMillis();
//                        RequestCreator config = Picasso.with(context).load(file).error(R.drawable.default_photo).placeholder(R.drawable.default_photo)
//                                .resize(headSize, headSize).centerInside().config(Bitmap.Config.RGB_565);
//                        config.into(target, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                Log.d(TAG, "------图片加载用时：" + (System.currentTimeMillis() - beginTime));
//                            }
//
//                            @Override
//                            public void onError() {
//
//                            }
//                        });
//                    }
//                }
//            });
    }

//}

    private static boolean isEmpty(String headUrl) {
        if (headUrl == null || "".equals(headUrl.trim()) || "null".equalsIgnoreCase(headUrl)) {
            return true;
        } else
            return false;
    }

    /**
     * 根据Id获取联系人头像的地址
     *
     * @param context
     * @param userId
     * @return
     */
    private static String getContactHeadPath(Context context, long userId) {

        return null;
    }

    private static int dp2px(Context context, float dimension) {
        Resources resources = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimension, resources.getDisplayMetrics());
        return Math.round(px);
    }

}
