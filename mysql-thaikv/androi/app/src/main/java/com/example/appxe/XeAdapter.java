package com.example.appxe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class XeAdapter extends BaseAdapter {
    Context mycontext;
    int mylayout;
    List<Xe> mangxe;

    public XeAdapter(Context mycontext, int mylayout, List<Xe> mangxe) {
        this.mycontext = mycontext;
        this.mylayout = mylayout;
        this.mangxe = mangxe;
    }

    public Context getMycontext() {
        return mycontext;
    }

    public void setMycontext(Context mycontext) {
        this.mycontext = mycontext;
    }

    public int getMylayout() {
        return mylayout;
    }

    public void setMylayout(int mylayout) {
        this.mylayout = mylayout;
    }

    public List<Xe> getMangxe() {
        return mangxe;
    }

    public void setMangxe(List<Xe> mangxe) {
        this.mangxe = mangxe;
    }

    @Override
    public int getCount() {
        return mangxe.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder
    {
        TextView txtvTenxe,txtvHangsx,txtvNamsx;
        ImageView imgHinh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)
                    mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mylayout,null);
            viewHolder.txtvTenxe=(TextView)convertView.findViewById(R.id.textViewTenxe);
            viewHolder.txtvHangsx=(TextView)convertView.findViewById(R.id.textViewHangsx);
            viewHolder.txtvNamsx=(TextView)convertView.findViewById(R.id.textViewNamsx);
            viewHolder.imgHinh=(ImageView) convertView.findViewById(R.id.imageViewHinh);
            convertView.setTag(viewHolder);
        }else { viewHolder=(ViewHolder) convertView.getTag(); }
        Xe xe=mangxe.get(position);
        viewHolder.txtvTenxe.setText(xe.getTenxe());
        viewHolder.txtvHangsx.setText(xe.getHangsx());
        viewHolder.txtvNamsx.setText(String.valueOf(xe.getNamsx()));

        Context context = viewHolder.imgHinh.getContext();
        int id = context.getResources().getIdentifier(xe.getHinh(), "drawable", context.getPackageName());
        viewHolder.imgHinh.setImageResource(id);

        return convertView;
    }

}
