package com.teamispower.smelep.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.teamispower.smelep.myapplication.BuildConfig;
import com.teamispower.smelep.myapplication.R;
import com.teamispower.smelep.myapplication.adapter.AllReceitpAdapter;
import com.teamispower.smelep.myapplication.adapter.SettingReceitpAdapter;
import com.teamispower.smelep.myapplication.data.DocketHead;
import com.teamispower.smelep.myapplication.data.KeyData;
import com.teamispower.smelep.myapplication.data.Product;
import com.teamispower.smelep.myapplication.widget.ReceiptRecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 小票页面
 */
public class ReceipActivity extends AppCompatActivity {
    /*小票展示的recycler*/
    ReceiptRecyclerView receiptRecyclerView;
    /**
     * 小票展示的adapter
     **/
    AllReceitpAdapter receipAdapter;
    /*列表*/
    RecyclerView recycler_setting;
    /*设置列表*/
    SettingReceitpAdapter settingAdapter;

    DocketHead headData;
    SparseArray<List<KeyData>> contentArray;
    boolean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resale_activity_receip);

        findId();
        initData();
        init();
        receipAdapter.setSparseArray(contentArray);
        settingAdapter.setSparseArray(contentArray);
    }

    private void findId() {
        receiptRecyclerView = findViewById(R.id.receip_recycler);
        recycler_setting = findViewById(R.id.recycler_setting);

    }

    private void initData() {
        headData = new DocketHead();
        contentArray = new SparseArray();

        ArrayList<KeyData> head = new ArrayList<>();
        head.add(new KeyData("单号", "51661666166666", true));
        head.add(new KeyData("流水号", "2000123", true));
        head.add(new KeyData("时间", "2018.03.03 14:32", true));
        head.add(new KeyData("收营员", "御坂美琴", true));
        contentArray.put(0, head);

        List<Product> products = new ArrayList<>();
        products.add(new Product("耐克运动鞋", "36码/蓝色", new BigDecimal("2"), new BigDecimal("2")));
        products.add(new Product("耐克运动鞋", "36码/蓝色", new BigDecimal("2"), new BigDecimal("2")));
        products.add(new Product("耐克运动鞋", "36码/蓝色", new BigDecimal("2"), new BigDecimal("2")));
        KeyData product = new KeyData("商品","" , true,products);

        /*注意这里的数据有些特殊*/
        ArrayList<KeyData> list = new ArrayList<>();
        list.add(product);
        contentArray.put(1, list);


        ArrayList<KeyData> bottom = new ArrayList<>();
        bottom.add(new KeyData("应收", "11.20", true));
        bottom.add(new KeyData("数量", "4", true));
        bottom.add(new KeyData("优惠", "0.10", true));
        bottom.add(new KeyData("抹零", "0.10", true));
        bottom.add(new KeyData("实收", "15.00", true));
        bottom.add(new KeyData("找零", "4.00", true));
        bottom.add(new KeyData("支付详情", "微信支付(30.00)、现金(50.20)", true,true));
        contentArray.put(2, bottom);

        ArrayList<KeyData> tail = new ArrayList<>();
        tail.add(new KeyData("会员卡号", "21662666666", true));
        tail.add(new KeyData("会员姓名", "迪丽热巴", true));
        tail.add(new KeyData("积分(本次/剩余)", "15/315", true));
        tail.add(new KeyData("余额", "2500.00", true));
        tail.add(new KeyData("店铺地址", "达州市通川区人民广场罗浮第壹区A座2单元3012号", true));
        tail.add(new KeyData("电话", "0818 5651562", true));
        tail.add(new KeyData("打印时间", "2018.30.30 14:32", true));

        contentArray.put(3, tail);
    }

    private void init() {

        StaggeredGridLayoutManager sl = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        receiptRecyclerView.setLayoutManager(sl);
        receipAdapter = new AllReceitpAdapter(this, headData);
        receiptRecyclerView.setAdapter(receipAdapter);
        receiptRecyclerView.changeWithMode(false);


        LinearLayoutManager lr = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_setting.setLayoutManager(lr);
        settingAdapter = new SettingReceitpAdapter(this, headData);
        recycler_setting.setAdapter(settingAdapter);


        settingAdapter.setListener(new SettingReceitpAdapter.SettingReceitpListener() {
            /**
             *
             * @param location 0=> 自动打印，1=>打印规格，2=>打印张数 ，3=>店铺名称,4=>logo图片，5=>单据条形码
             * @param check
             */
            @Override
            public void someThingClick(int location, boolean check) {
                switch (location) {
                    case 0:
                        headData.setAutoPrint(check);
                        break;
                    case 1:
                        b = !b;
                        receiptRecyclerView.changeWithMode(b);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "打印张数", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        headData.setNameShow(check);
                        receipAdapter.notifyItemRangeChanged(0,1);
                        break;
                    case 4:
                        headData.setPicName("哈哈.jpe");
                        receipAdapter.notifyItemRangeChanged(0,1);
                        break;
                    case 5:
                        headData.setBarCodeShow(check);
                        receipAdapter.notifyItemRangeChanged(0,1);
                        break;
                    default:
                }
            }

            @Override
            public void titleChange(String title) {
                headData.setTitle(title);
                receipAdapter.notifyItemRangeChanged(0,1);
            }

            @Override
            public void itemChange(int position, List<KeyData> list) {
                List<KeyData> temp = new ArrayList<>();
                for(KeyData keyData:list){
                    if(BuildConfig.DEBUG) Log.e("数据",position+" :"+ keyData.toString());
                if(keyData.isShow()) temp.add(keyData);
                }
                receipAdapter.changeItemDatas(position,temp);
            }
        });

    }

}
