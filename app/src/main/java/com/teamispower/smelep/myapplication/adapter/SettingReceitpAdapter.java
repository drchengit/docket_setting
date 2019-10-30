package com.teamispower.smelep.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.data.DocketHead;
import com.teamispower.smelep.myapplication.data.KeyData;
import com.teamispower.smelep.myapplication.utils.ArmUtils;
import com.teamispower.smelep.myapplication.widget.FlowLayoutManager;
import com.teamispower.smelep.myapplication.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/28 0028.
 * qq:1414355045
 */
public class SettingReceitpAdapter extends RecyclerView.Adapter {
    private final static int HEAD = 0, CONTENT = 1;
    SparseArray<List<KeyData>> sparseArray;
    private DocketHead headData;
    private Context context;
    private LayoutInflater inflater;
    private SettingReceitpListener listener;

    public SettingReceitpAdapter(Context context, DocketHead docketHead) {
        this.sparseArray = new SparseArray<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.sparseArray.put(0, new ArrayList<KeyData>());
        this.sparseArray.put(1, new ArrayList<KeyData>());
        this.sparseArray.put(2, new ArrayList<KeyData>());
        this.sparseArray.put(3, new ArrayList<KeyData>());
        this.headData = docketHead;

    }

    public void setSparseArray(SparseArray<List<KeyData>> sparseArray) {
        this.sparseArray = sparseArray;
        notifyDataSetChanged();
    }

    public void setListener(SettingReceitpListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            View itemView = inflater.inflate(R.layout.resale_item_receitp_sh, parent, false);
            HeadHolder headHolder = new HeadHolder(itemView);
            headHolder.setListener(new HeadHolder.HeadListener() {
                /**
                 *
                 * @param location 0=> 自动打印，1=>打印规格，2=>打印张数 ，3=>店铺名称,4=>logo图片，5=>单据条形码
                 * @param check
                 */
                @Override
                public void someThingClick(int location, boolean check) {
                    if (listener != null) listener.someThingClick(location, check);
                }

                @Override
                public void titleChange(String title) {
                    if (listener != null) listener.titleChange(title);
                }
            });
            return headHolder;
        } else {
            View itemView = inflater.inflate(R.layout.resale_item_receitp_cs, parent, false);
            return new ContentsHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder) {
            initHead((HeadHolder) holder, headData);
        } else if (holder instanceof ContentsHolder) {
            initContents((ContentsHolder) holder, position - 1);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEAD : CONTENT;
    }

    @Override
    public int getItemCount() {
        return sparseArray.size() + 1;
    }

    private void initHead(HeadHolder holder, DocketHead headData) {
        holder.auto_print_sb.setChecked(headData.isAutoPrint());
        holder.print_size_tv.setText(headData.getPrintSizeStr());
        holder.shop_name_sb.setChecked(headData.isNameShow());
        holder.bar_code_sb.setChecked(headData.isBarCodeShow());
        holder.print_number_tv.setText(headData.getPrintNumber()+"张");
        holder.logo_file_name_tv.setText(headData.getPicName());
        holder.title_et.removeTextChangedListener(holder);
        holder.title_et.setText(headData.getTitle());
        holder.title_et.addTextChangedListener(holder);
    }


    private void initContents(ContentsHolder holder, final int key) {
        holder.content_tv.setText(keyToItemTitle(key));

        FlowLayoutManager flowLayoutManager = new FlowLayoutManager() {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        if(holder.recyclerView.getItemDecorationCount()<=0) holder.recyclerView.addItemDecoration(new SpaceItemDecoration(ArmUtils.dip2px(context, 8)));
        holder.recyclerView.setLayoutManager(flowLayoutManager);
        holder.recyclerView.setHasFixedSize(true);
        ReceitpSettingCAdapter adapter = new ReceitpSettingCAdapter(context, sparseArray.get(key),key);
        adapter.setSettingListener(new ReceitpSettingCAdapter.ReceitpSettingListener() {
            @Override
            public void itemChange(int postion) {
                if (listener != null) listener.itemChange(postion,sparseArray.get(postion-1));
            }
        });
        holder.recyclerView.setAdapter(adapter);
    }


    private String keyToItemTitle(int key){
        if(key==0)return "上部";
        if(key==1)return "中部";
        if(key==2)return "下部";
        if(key==3)return "尾部";
        return "上部";
    }

 public    interface SettingReceitpListener {
        /**
         * @param location 0=> 自动打印，1=>打印规格，2=>打印张数 ，3=>店铺名称,4=>logo图片，5=>单据条形码
         */
        void someThingClick(int location, boolean check);

        void titleChange(String title);
     void itemChange(int position,List<KeyData> list);
    }

    static class HeadHolder extends RecyclerView.ViewHolder implements SwitchButton.OnCheckedChangeListener, View.OnClickListener ,TextWatcher{

        final SwitchButton auto_print_sb, bar_code_sb, shop_name_sb;
        final TextView print_size_tv, print_number_tv, logo_file_name_tv;
        final EditText title_et;
        HeadListener listener;

        public HeadHolder(View itemView) {
            super(itemView);
            auto_print_sb = itemView.findViewById(R.id.auto_print_sb);
            auto_print_sb.setOnCheckedChangeListener(this);
            itemView.findViewById(R.id.print_size_fl).setOnClickListener(this);
            print_size_tv = itemView.findViewById(R.id.print_size_tv);
            print_number_tv = itemView.findViewById(R.id.print_number_tv);
            shop_name_sb = itemView.findViewById(R.id.shop_name_sb);
            shop_name_sb.setOnCheckedChangeListener(this);
            itemView.findViewById(R.id.logo_fl).setOnClickListener(this);
            logo_file_name_tv = itemView.findViewById(R.id.logo_file_name_tv);
            title_et = itemView.findViewById(R.id.title_et);
            itemView.findViewById(R.id.print_number_fl).setOnClickListener(this);
            bar_code_sb = itemView.findViewById(R.id.bar_code_sb);
            bar_code_sb.setOnCheckedChangeListener(this);
            title_et.addTextChangedListener(this);
        }

        public void setListener(HeadListener listener) {
            this.listener = listener;
        }

        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            if (view.getId() == R.id.auto_print_sb) {
                if (listener != null) listener.someThingClick(0, isChecked);
            } else if (view.getId() == R.id.bar_code_sb) {
                if (listener != null) listener.someThingClick(5, isChecked);
            } else if (view.getId() == R.id.shop_name_sb) {
                if (listener != null) listener.someThingClick(3, isChecked);
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.print_size_fl) {
                if (listener != null) listener.someThingClick(1, false);
            } else if (v.getId() == R.id.logo_fl) {
                if (listener != null) listener.someThingClick(4, false);
            } else if (v.getId() == R.id.print_number_fl) {
                if (listener != null) listener.someThingClick(2, false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (listener != null)
                listener.titleChange(ArmUtils.isEmpty(s) ? "" : s.toString());

        }

        interface HeadListener {
            /**
             * @param location 0=> 自动打印，1=>打印规格，2=>打印张数 ，3=>店铺名称,4=>logo图片，5=>单据条形码
             */
            void someThingClick(int location, boolean check);

            void titleChange(String title);

        }
    }

    static class ContentsHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView content_tv;

        public ContentsHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler);
            content_tv = itemView.findViewById(R.id.content_tv);
        }
    }
}
