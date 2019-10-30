package com.teamispower.smelep.myapplication.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.teamispower.smelep.myapplication.BuildConfig;
import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.data.KeyData;

import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/28 0028.
 * qq:1414355045
 * 选择显示项的adapter
 */
public class ReceitpSettingCAdapter extends RecyclerView.Adapter<ReceitpSettingCAdapter.Holder> {
    private Context context;
    private List<KeyData> list;
    private LayoutInflater inflater;
    private int key;//表示当前 adapter 所在部分
    private ReceitpSettingListener settingListener;

    public ReceitpSettingCAdapter(Context context, List<KeyData> list, int key) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.key = key;


    }

    public void setSettingListener(ReceitpSettingListener settingListener) {
        this.settingListener = settingListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.resale_item_receitp_cs_item, parent, false);
        Holder holder = new Holder(itemView);
        holder.setListener(new Holder.HolderClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (key == 1) {
                    Toast.makeText(view.getContext().getApplicationContext(), "商品不能取消", Toast.LENGTH_SHORT).show();
                    return;
                }
                KeyData keyData = list.get(position);
                keyData.setShow(!keyData.isShow());
                if (settingListener != null) {//数据改变了
                    settingListener.itemChange(key + 1);
                    ReceitpSettingCAdapter.this.notifyItemChanged(position);
                }
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        KeyData keyData = list.get(position);
        if(BuildConfig.DEBUG)  Log.e("刷新",position  +"");
        if (!keyData.isShow()) {
            ((TextView) holder.itemView).setText(keyData.getKey());
            holder.itemView.setBackgroundResource(R.drawable.resale_corner_4_eef0f8);
            ((TextView) holder.itemView).setTextColor(ContextCompat.getColor(context, R.color.resale_color_7A7E95));
        } else {
            ((TextView) holder.itemView).setText(keyData.getKey());
            holder.itemView.setBackgroundResource(R.drawable.resale_corner_4_tran_15_ec414d);
            ((TextView) holder.itemView).setTextColor(ContextCompat.getColor(context, R.color.resale_color_EC414D));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface ReceitpSettingListener {
        void itemChange(int postion);
    }

    static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        HolderClickListener listener;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setListener(HolderClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onClick(v, this.getAdapterPosition());

        }

        interface HolderClickListener {
            void onClick(View view, int position);
        }
    }
}
