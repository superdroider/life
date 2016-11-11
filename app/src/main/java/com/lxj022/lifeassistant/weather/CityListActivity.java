package com.lxj022.lifeassistant.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.lxj022.lifeassistant.R;
import com.lxj022.lifeassistant.data.bean.CityBean;
import com.lxj022.lifeassistant.data.local.db.DbOperate;

import java.util.List;

/**
 * Created by superdroid on 2016/10/16.
 */
public class CityListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int PROVINCE_DATA_VIEW = 0;
    public static final int CITY_DATA_VIEW = 1;
    public static final int SEARCH_LIST_VIEW = 2;

    private ImageButton ib_back;
    private EditText et_city_name;
    private ImageView iv_delete;
    private GridView gv_city_list;
    private ListView lv_search_result;

    private DbOperate dbOperate;//数据库操作对象
    private CityListAdapter cityListAdapter;
    private List<CityBean> cityList;
    private List<CityBean> provinceList;
    private List<CityBean> searchResultList;

    private int currentViewType = PROVINCE_DATA_VIEW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        et_city_name = (EditText) findViewById(R.id.et_city_name);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        gv_city_list = (GridView) findViewById(R.id.gv_city_list);
        lv_search_result = (ListView) findViewById(R.id.lv_search_result);
        ib_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
        et_city_name.addTextChangedListener(new InputTextChangeListener());
        gv_city_list.setOnItemClickListener(new ItemClickListener());
        lv_search_result.setOnItemClickListener(new ItemClickListener());
        dbOperate = new DbOperate(this);
        provinceList = dbOperate.getAllProvince();
        cityListAdapter = new CityListAdapter(provinceList, this, currentViewType);
        gv_city_list.setAdapter(cityListAdapter);
        gv_city_list.setVisibility(View.VISIBLE);
        lv_search_result.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;

            case R.id.iv_delete:
                et_city_name.setText("");
                break;
        }
    }


    /**
     * 搜索结果适配到页面
     *
     * @param searchResultList
     */
    private void setSearchDataToView(List<CityBean> searchResultList) {
        if (searchResultList == null) {
            return;
        }
        currentViewType = SEARCH_LIST_VIEW;
        this.searchResultList = searchResultList;
        cityListAdapter = new CityListAdapter(searchResultList, this, currentViewType);
        lv_search_result.setAdapter(cityListAdapter);
        lv_search_result.setVisibility(View.VISIBLE);
        gv_city_list.setVisibility(View.GONE);
    }

    class InputTextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.toString().trim().length() > 0) {
                iv_delete.setVisibility(View.VISIBLE);
                setSearchDataToView(dbOperate.searchCityName(charSequence.toString().trim()));
            } else {
                iv_delete.setVisibility(View.GONE);
                lv_search_result.setVisibility(View.GONE);
                gv_city_list.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (currentViewType == PROVINCE_DATA_VIEW) {
                cityList = dbOperate.getCityName(provinceList.get(i).getProvinceName());
                currentViewType = CITY_DATA_VIEW;
                cityListAdapter.updataData(cityList, currentViewType);
            } else if (currentViewType == CITY_DATA_VIEW) {
                Intent intent = new Intent();
                intent.putExtra("city", cityList.get(i));
                setResult(RESULT_OK, intent);
                finish();
            } else if (currentViewType == SEARCH_LIST_VIEW) {
                Intent intent = new Intent();
                intent.putExtra("city", searchResultList.get(i));
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}
