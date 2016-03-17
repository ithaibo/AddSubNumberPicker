package com.ithaibo.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Andy on 2016/3/10.
 */
public class AddSubNumberPicker extends LinearLayout {
    private Context context;
    private TextView tvCount;
    private Button addBtn;
    private Button subBtn;
    /**
     * 当前的数量
     */
    private int count = 0;
    /**
     * 限制类型
     */
    private int typeCountLimit = 0;

    /**
     * count>=0
     */
    public static final int TYPE_UNSIGN_NUMBER = 1;
    /**
     * count can not great than the max
     */
    public static final int TYPE_MAX_LIMIT = 2;
    /**
     * count can not less than the min
     */
    public static final int TYPE_MIN_LIMIT = 3;
    /**
     * count can not be zero.
     */
    public static final int TYPE_NONE_ZERO = 4;

    private int maxLimit;
    private int minLimit;

    private String toastUnsign;
    private String toastMaxLimit;
    private String toastMinLimit;
    private String toastNoneZero;
    private OnClickListener clickListener;


    public AddSubNumberPicker(Context context) {
        super(context);
        this.context = context;
    }

    public AddSubNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.add_sub_number_picker_layout, this);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        addBtn = (Button) view.findViewById(R.id.bt_add);
        subBtn = (Button) view.findViewById(R.id.bt_sub);

        clickListener = createClickListner();

        addBtn.setOnClickListener(clickListener);
        subBtn.setOnClickListener(clickListener);

        setNumberText();
    }

    private OnClickListener createClickListner() {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id==R.id.bt_add){
                    doAddAction();
                    if(countChangeListner!=null)
                        countChangeListner.onCountChanged(count);
                } else if(id == R.id.bt_sub){
                    doSubAction();
                    if(countChangeListner!=null)
                        countChangeListner.onCountChanged(count);
                }
            }
        };
        return listener;
    }

    public int getCount() {
        return count;
    }

    private void setNumberText() {
        tvCount.setText(count + "");
    }

    /**
     * 执行加
     */
    private void doAddAction() {
        count++;
        if (typeCountLimit == TYPE_MAX_LIMIT && count > maxLimit) {
            this.count = maxLimit;
            if (isNull(toastMaxLimit)){
                showToast(toastMaxLimit);
            }
        }
        setNumberText();
    }

    /**
     * 执行减
     */
    private void doSubAction() {
        count--;
        switch (typeCountLimit){
            case TYPE_MIN_LIMIT:
                if (count<minLimit){
                    count = minLimit;
                    if (!isNull(toastMinLimit)){
                        showToast(toastMinLimit);
                    }
                }
                break;
            case TYPE_NONE_ZERO:
                if (count==0){
                    count = -1;
                    if (!isNull(toastNoneZero)){
                        showToast(toastNoneZero);
                    }
                }
                break;
            case TYPE_UNSIGN_NUMBER:
                if (count<0){
                    count = 0;
                    if (!isNull(toastUnsign)){
                        showToast(toastUnsign);
                    }
                }
                break;
        }
        setNumberText();
    }

    public int getTypeCountLimit() {
        return typeCountLimit;
    }

    public void setTypeCountLimitAndToasLimittStr(int typeCountLimit, String toastLimitStr) {
        this.typeCountLimit = typeCountLimit;
        if (toastLimitStr!=null){
            switch (typeCountLimit){
                case TYPE_MIN_LIMIT:
                    this.toastMinLimit = toastLimitStr;
                    break;
                case TYPE_MAX_LIMIT:
                    this.toastMaxLimit = toastLimitStr;
                    break;
                case TYPE_UNSIGN_NUMBER:
                    this.toastUnsign = toastLimitStr;
                    break;
                case TYPE_NONE_ZERO:
                    this.toastNoneZero = toastLimitStr;
                    break;
            }
        }
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimitr) {
        this.maxLimit = maxLimit;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    private void showToast(String toastStr) {
        Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
    }

    private boolean isNull(Object obj) {
        return obj==null?true:false;
    }

    private CountChangeListner countChangeListner;

    public void setCountChangeListner(CountChangeListner countChangeListner) {
        this.countChangeListner = countChangeListner;
    }

    public interface CountChangeListner {
        void onCountChanged(int count);
    }
}
