package edu.njau.SmartAgriculture.module.gcfa;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import net.jiawa.debughelper.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cz.msebera.android.httpclient.Header;
import edu.njau.SmartAgriculture.R;
import edu.njau.SmartAgriculture.api.GCFAApi;
import edu.njau.SmartAgriculture.base.adapter.BaseRecyclerAdapter;
import edu.njau.SmartAgriculture.base.adapter.BaseSpinnerAdapter;
import edu.njau.SmartAgriculture.bean.gcfa.allinfo.AllInfo;
import edu.njau.SmartAgriculture.bean.gcfa.zpfa.Zpfa;
import edu.njau.SmartAgriculture.module.amap.AMapHelper;
import edu.njau.SmartAgriculture.module.SARecyclerFragment;
import edu.njau.SmartAgriculture.module.gcfa.mvp.GCFAContract;
import edu.njau.SmartAgriculture.module.gcfa.mvp.GCFAPresenter;
import edu.njau.SmartAgriculture.module.main.adapter.MainAdapter;
import edu.njau.SmartAgriculture.module.main.bean.MainItem;
import edu.njau.SmartAgriculture.module.search.SearchActivity;

/**
 * Created by zhaoxin5 on 2017/4/11.
 */

public class GCFAFragment extends SARecyclerFragment<GCFAContract.GCFAPresenter, MainItem>
        implements View.OnClickListener,
        GCFAContract.GCFAView /* MVP中的View */ {

    private Spinner mSpinnerCropType;
    private Spinner mSpinnerPlantType;
    private BaseSpinnerAdapter mCropTypeAdapter;
    private BaseSpinnerAdapter mPlantTypeAdapter;

    private String mCropId = "0";
    private String mZpfaId = "00000000001";
    private String mAreaId = "0000000001";
    private String mLocation ="nanjin";
    private String mGZFAName = "" ;

    @Bind(R.id.tv_gcfa_district)
    TextView mDistrict;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        mSpinnerCropType = this.findView(R.id.sp_crop_type);
        mSpinnerPlantType = this.findView(R.id.sp_plant_type);

        if (null == mCropTypeAdapter) {
            mCropTypeAdapter = new BaseSpinnerAdapter(getContext(), cropTypes);
        }
        mSpinnerCropType.setAdapter(mCropTypeAdapter);
        mSpinnerCropType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCropId = ((Info)mCropTypeAdapter.getItem(position)).id;
                XLog.d(true, 5, "cropId: " + mCropId);
                mCropTypeAdapter.setSelectIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCropId = "0";
                XLog.d(true, 5, "cropId: " + mCropId);
            }
        });
        if (null == mPlantTypeAdapter) {
            mPlantTypeAdapter = new BaseSpinnerAdapter(getContext(), plantTypes);
        }
        mSpinnerPlantType.setAdapter(mPlantTypeAdapter);
        mSpinnerPlantType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (null == mPlantTypeAdapter) {
                    mPlantTypeAdapter = new BaseSpinnerAdapter(getContext(), plantTypes);
                }
                if(mAreaId==null || "".equals(mAreaId)){
                    return false;
                }
                if(mCropId==null || "".equals(mCropId)){
                    return false;
                }

                GCFAApi.getZpfaList(mAreaId,mCropId,new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {


                        plantTypes.clear();
                        Log.e("resp:",""+responseString);
                        Zpfa zpfaList = new Gson().fromJson(responseString, Zpfa.class);

                        for(int i=0;i<zpfaList.getResponse().getZpfaList().size();i++){

                            String ZPFAName = zpfaList.getResponse()
                                    .getZpfaList().get(i).getZpfaName();

                            if(ZPFAName!=null && !"".equals(ZPFAName)){
                                plantTypes.add(new Info(ZPFAName
                                        , zpfaList.getResponse()
                                        .getZpfaList().get(i).getZpfaID()));
                            }

                        }
                        mPlantTypeAdapter.notifyDataSetChanged();
                    }
                });


                return false;
            }
        });

        mSpinnerPlantType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mZpfaId = ((Info)mPlantTypeAdapter.getItem(position)).id;
                XLog.d(true, 5, "zpfa: " + mZpfaId);
                mGZFAName = ((Info) mPlantTypeAdapter.getItem(position)).name;
                mPlantTypeAdapter.setSelectIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mZpfaId = "00000000001";
                XLog.d(true, 5, "zpfa: " + mZpfaId);
            }
        });

        AMapHelper.newInstance(getActivity().getApplicationContext()).addLocationListener(new AMapHelper.LocationListener() {
            @Override
            public void onGetLocation(AMapLocation aMapLocation) {
//                mDistrict.setText(aMapLocation.getDistrict());
                mLocation = aMapLocation.getProvince()+aMapLocation.getCity();
                mDistrict.setText(aMapLocation.getProvince()+"  "
                                  +aMapLocation.getCity());
            }
        });
    }

    @Override
    protected BaseRecyclerAdapter<MainItem> getAdapter() {
        return new MainAdapter(getContext());
    }

    @Override
    protected void onItemClick(MainItem mainItem, int position) {
        XLog.d(true, 5);
        launchInfoActivity(mainItem.getTitle());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_icon) {
            // 主页右上角的Search
            Intent intent = new Intent(this.getContext(), SearchActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    protected void beforeInitData() {
        super.beforeInitData();
        GCFAPresenter presenter = new GCFAPresenter(this);
        setPresenter(presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gcfa;
    }

    List<BaseSpinnerAdapter.interf> cropTypes = new ArrayList<BaseSpinnerAdapter.interf>();
    @Override
    public void onGetCropType() {
        // 准备生成作物类型数据
        if (null == mCropTypeAdapter) {
            mCropTypeAdapter = new BaseSpinnerAdapter(getContext(), cropTypes);
        }
        cropTypes.add(new Info("水稻", "0"));
        cropTypes.add(new Info("小麦", "1"));
        // 因为SpinnerAdapter内部保存的是和cropTypes
        // 一样的对象,所以要修改SpinnerAdapter中的数据
        // 只要修改cropTypes即可
        mCropTypeAdapter.notifyDataSetChanged();
    }

    List<BaseSpinnerAdapter.interf> plantTypes = new ArrayList<BaseSpinnerAdapter.interf>();
    @Override
    public void onGetPlantType() {
        // 准备生成栽种类型数据
        if (null == mPlantTypeAdapter) {
            mPlantTypeAdapter = new BaseSpinnerAdapter(getContext(), plantTypes);
        }


//        GCFAApi.getZpfaList(mAreaId,mCropId,new TextHttpResponseHandler(){
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//
//                Log.e("resp:",""+responseString);
//                Zpfa zpfaList = new Gson().fromJson(responseString, Zpfa.class);
//
//                for(int i=0;i<zpfaList.getResponse().getZpfaList().size();i++){
//                    plantTypes.add(new Info(zpfaList.getResponse()
//                            .getZpfaList().get(i).getZpfaName()
//                            , zpfaList.getResponse()
//                            .getZpfaList().get(i).getZpfaID()));
//                }
//            }
//        });

        plantTypes.add(new Info("请选择栽培方案", ""));
//        plantTypes.add(new Info("方案2", "00000000002"));
//        plantTypes.add(new Info("方案3", "00000000003"));
//        plantTypes.add(new Info("方案4", "00000000004"));
        // 因为SpinnerAdapter内部保存的是和plantTypes
        // 一样的对象,所以要修改SpinnerAdapter中的数据
        // 只要修改plantTypes即可
        mPlantTypeAdapter.notifyDataSetChanged();
    }

    class Info implements BaseSpinnerAdapter.interf {
        final String name;
        final String id;
        public Info(String n, String id) {
            this.name = n;
            this.id = id;
        }

        @Override
        public String getShowText() {
            return name;
        }
    }

    private void launchInfoActivity(String type) {

        if(mGZFAName==null || "".equals(mGZFAName)){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("TYPE", type);
        intent.putExtra("ZpfaID", mZpfaId);
        intent.putExtra("AreaID", mAreaId);
        intent.putExtra("CropID", mCropId);
        intent.putExtra("BLocation", mLocation);
        intent.putExtra("mGZFAName", mGZFAName);



        if (type.equals("模式图")) {
            intent.setClass(getActivity(), PatternActivity.class);
            startActivity(intent);
            return;
        }

        if (type.equals("生育时期")) {
            intent.setClass(getActivity(), PeriodActivity.class);
            startActivity(intent);
            return;
        }

//        if (type.equals("病虫草防治")) {
//            intent.setClass(getActivity(), PestControlActivity.class);
//            startActivity(intent);
//            return;
//        }

        if (type.equals("农事操作")) {
            intent.setClass(getActivity(), FarmingOprationActivity.class);
            startActivity(intent);
            return;
        }

//        intent.setClass(getActivity(), VarietyActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("ZpfaID", mZpfaId);
        intent.putExtra("AreaID", mAreaId);
        intent.putExtra("CropID", mCropId);
        startActivity(intent);
    }


}
