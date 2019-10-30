package com.teamispower.smelep.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.data.KeyData;
import com.teamispower.smelep.myapplication.data.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/25 0025.
 * qq:1414355045
 * 上部，商品列表内容
 */
public class ReceiptMidAdapter extends RecyclerView.Adapter<ReceiptMidAdapter.Holder> {
    private Context context;
    private List datas;
    private LayoutInflater inflater;

    public ReceiptMidAdapter(Context mContext, List topDatas) {
        this.context = mContext;
        this.datas = topDatas;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.resale_item_receip_mid_item, parent, false);
        return new Holder(itemview);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Product product = (Product) datas.get(position);
        holder.one_tv.setText(product.getGoodsName()+"（"+product.getSpec()+"）");
        holder.two_tv.setText(product.getUnitPrice().stripTrailingZeros().toPlainString());
        holder.three_tv.setText(product.getNum().stripTrailingZeros().toPlainString());
        holder.four_tv.setText(product.getAllPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        final TextView one_tv, two_tv, three_tv, four_tv;

        public Holder(View itemView) {
            super(itemView);
            one_tv = itemView.findViewById(R.id.one_tv);
            two_tv = itemView.findViewById(R.id.two_tv);
            three_tv = itemView.findViewById(R.id.three_tv);
            four_tv = itemView.findViewById(R.id.four_tv);
        }
    }
}
