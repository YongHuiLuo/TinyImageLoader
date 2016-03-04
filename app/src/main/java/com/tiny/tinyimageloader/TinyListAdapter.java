package com.tiny.tinyimageloader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created JackLuo
 * 实现主要功能：
 * 创建时间： on 2016/3/4.
 * 修改者： 修改日期： 修改内容：
 */
public class TinyListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<UserModel> mData;

    public TinyListAdapter(Context context, ArrayList<UserModel> mData) {
        inflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TinyHolder tinyHolder;
        UserModel userModel;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            tinyHolder = new TinyHolder();
            convertView.setTag(tinyHolder);
        } else {
            tinyHolder = (TinyHolder) convertView.getTag();
        }

        tinyHolder.img_header = (ImageView) convertView.findViewById(R.id.img_head);
        tinyHolder.txt_position = (TextView) convertView.findViewById(R.id.txt_position);
        userModel = (UserModel) getItem(position);
        LoadImageHelper.loadHead(convertView.getContext(), tinyHolder.img_header, userModel.userId, userModel.headUrl);
        tinyHolder.txt_position.setText(String.valueOf(userModel.userId));
        Log.d("tiny", "imgHeader --" + tinyHolder.img_header.hashCode() + ";txtPosition--" + tinyHolder.txt_position.hashCode() + ";userModel.userId--" + userModel.userId);
        return convertView;
    }

    public static class TinyHolder {
        ImageView img_header;
        TextView txt_position;
    }
}
