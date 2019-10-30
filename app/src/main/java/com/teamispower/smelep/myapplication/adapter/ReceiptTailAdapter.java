package com.teamispower.smelep.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.data.KeyData;

import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 *  上部，商品列表内容
 */
public class ReceiptTailAdapter extends RecyclerView.Adapter<ReceiptTailAdapter.Holder> {
        private Context context;
        private List<KeyData> datas;
        private LayoutInflater inflater;

    public ReceiptTailAdapter(Context mContext, List<KeyData> tailDatas) {
    this.context = mContext;
    this.datas = tailDatas;
    inflater = LayoutInflater.from(context);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.resale_item_receip_tail_item,parent,false);
        return new Holder(itemview);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(datas.get(position).getKey()+"："+datas.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class  Holder extends RecyclerView.ViewHolder{
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
