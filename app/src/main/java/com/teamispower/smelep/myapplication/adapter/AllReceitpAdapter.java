package com.teamispower.smelep.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.data.DocketHead;
import com.teamispower.smelep.myapplication.data.KeyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DrChen
 * @Date 2019/10/24 0024.
 * qq:1414355045
 * 小票的展示
 */
public class AllReceitpAdapter extends RecyclerView.Adapter {
    private final static int HEAD = 1, TOP = 2, MID = 3, BOTTOM = 4, TAIL = 5;
    private final int itemCount = 5;
    private LayoutInflater inflater;
    //分成段 :头部 ,上部，中，下，
    private DocketHead headData;
    private SparseArray<List<KeyData>> sparseArray;
    private Context mContext;



    public AllReceitpAdapter(Context context, DocketHead headData) {
        mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.headData = headData;
        sparseArray = new SparseArray<>();
       sparseArray.put( 0,new ArrayList<KeyData>());
        sparseArray.put(1,new ArrayList<KeyData>());
        sparseArray.put(2,new ArrayList<KeyData>());
        sparseArray.put(3,new ArrayList<KeyData>());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            return new HeadHolder(createContentView(R.layout.resale_item_receip_head, parent));
        } else if (viewType == TOP) {
            return new TopHolder(createContentView(R.layout.resale_item_receip_top, parent));
        } else if (viewType == MID) {
            return new MidHolder(createContentView(R.layout.resale_item_receip_mid, parent));
        } else if (viewType == BOTTOM) {
            return new BottomHolder(createContentView(R.layout.resale_item_receip_bottom, parent));
        } else if (viewType == TAIL) {
            return new TailHolder(createContentView(R.layout.resale_item_receip_tail, parent));
        }
        return null;
    }

    public void changeItemDatas(int position,List<KeyData> topDatas) {
        sparseArray.put(position-1,topDatas);
        notifyItemRangeChanged(position,1);
    }


    private View createContentView(int layotuId, ViewGroup parent) {
        View view = inflater.inflate(layotuId, parent, false);
        StaggeredGridLayoutManager.LayoutParams slm = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        slm.setFullSpan(true);
        view.setLayoutParams(slm);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeadHolder) {
            initHead(((HeadHolder) holder), headData);//加载头部数据源
        } else if (holder instanceof TopHolder) {
            initTop((TopHolder) holder, sparseArray.get(0));//加载上部数据源
        } else if (holder instanceof MidHolder&& sparseArray.get(1).get(0)!=null) {
            initMid(((MidHolder) holder), sparseArray.get(1));//加载中部数据源
        } else if (holder instanceof BottomHolder) {
            initBottom(((BottomHolder) holder), sparseArray.get(2));//加载下部数据源
        } else if (holder instanceof TailHolder) {
            initTail(((TailHolder) holder), sparseArray.get(3));//加载尾部数据源
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEAD;
        if (position == 1) return TOP;
        if (position == 2) return MID;
        if (position == 3) return BOTTOM;
        if (position == 4) return TAIL;
        return 0;
    }

    @Override
    public int getItemCount() {

        return itemCount;
    }

    private void initHead(HeadHolder holder, DocketHead headData) {
         if(headData.isBarCodeShow()){
             holder.bar_code_iv.setVisibility(View.VISIBLE);
         }else {
             holder.bar_code_iv.setVisibility(View.GONE);
         }
         if(headData.isNameShow()){
             holder.shop_name_tv.setVisibility(View.VISIBLE);
         }else {
             holder.shop_name_tv.setVisibility(View.GONE);
         }
         holder.title_tv.setText(headData.getTitle());
        // TODO: 2019/10/29 0029  logo pic
//         holder.logo_iv.se
    }

    /*上部： 营业员这些*/
    private void initTop(TopHolder holder, List<KeyData> topDatas) {


        
        LinearLayoutManager lr = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        holder.recyclerView.setLayoutManager(lr);
        ReceiptTopAdapter centerAdapter = new ReceiptTopAdapter(mContext, topDatas);
        holder.recyclerView.setAdapter(centerAdapter);
    }

    /*中部： 商品列表*/
    private void initMid(MidHolder holder, List<KeyData> midDatas) {
        LinearLayoutManager lr = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        holder.recyclerView.setLayoutManager(lr);
        ReceiptMidAdapter centerAdapter = new ReceiptMidAdapter(mContext, midDatas.get(0).getProducts());
        holder.recyclerView.setAdapter(centerAdapter);
        holder.line.setVisibility(midDatas.size()<=0?View.GONE:View.VISIBLE);

    }

    /*下部 ： 支付明细*/
    private void initBottom(BottomHolder holder, final List<KeyData> bottomDatas) {
        GridLayoutManager gr = new GridLayoutManager(mContext,2) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        gr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return  bottomDatas.get(position).isSpan() ? 2:1;
            }
        });

        ReceiptBottomAdapter centerAdapter = new ReceiptBottomAdapter(mContext, bottomDatas);
        holder.recyclerView.setAdapter(centerAdapter);
        holder.recyclerView.setLayoutManager(gr);
        holder.line.setVisibility(bottomDatas.size()<=0?View.GONE:View.VISIBLE);


    }

    /*尾部： 详情 */
    private void initTail(TailHolder holder, List<KeyData> tailDatas) {
        LinearLayoutManager lr = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {//禁止滑动
                return false;
            }
        };
        holder.recyclerView.setLayoutManager(lr);
        ReceiptTailAdapter centerAdapter = new ReceiptTailAdapter(mContext, tailDatas);
        holder.recyclerView.setAdapter(centerAdapter);
        holder.line.setVisibility(tailDatas.size()<=0?View.GONE:View.VISIBLE);

    }

    public void setSparseArray(SparseArray<List<KeyData>> contentArray) {
        sparseArray.put(0,contentArray.get(0));
        sparseArray.put(1,contentArray.get(1));
        sparseArray.put(2,contentArray.get(2));
        sparseArray.put(3,contentArray.get(3));
        notifyDataSetChanged();
    }

    static class HeadHolder extends RecyclerView.ViewHolder {
        final TextView shop_name_tv,title_tv;
        final ImageView logo_iv,bar_code_iv;
        public HeadHolder(View itemView) {
            super(itemView);
            shop_name_tv =itemView.findViewById(R.id.shop_name_tv);
                    logo_iv = itemView.findViewById(R.id.logo_iv);
            title_tv = itemView.findViewById(R.id.title_tv);
                    bar_code_iv  =itemView.findViewById(R.id.bar_code_iv);
        }
    }

    static class TopHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;


        public TopHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler);

        }
    }

    static class MidHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public View line;

        public MidHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler);
            line = itemView.findViewById(R.id.line);
        }
    }

    static class BottomHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public View line;

        public BottomHolder(View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.line);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }

    static class TailHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public View line;
        public TailHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler);
            line = itemView.findViewById(R.id.line);
        }
    }
}
