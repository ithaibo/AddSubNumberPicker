# AddSubNumberPicker

## 基本介绍
AddSubNumberPicker是一个左减右加的数字选择器，常用于购物车列表等情景。

## 功能
- 数量加减
        
- 设置数量限制规则：
   - 不能为0
   - 不能小于0
   - 不能大于最大数（自己设置）
   - 不能小于最小数(自己设置)
        
- 数量变化CallBack
   当数量发生变化时，提供回调处理其他业务逻辑接口。

## 使用方法
gradle中添加compile project(':add_sub_number_picker_library')
        
在layout文件中添加com.ithaibo.library.AddSubNumberPicker，例如：
``` xml<com.ithaibo.library.AddSubNumberPicker
           android:id="@+id/picker"
           android:layout_width="200dp"
           android:layout_height="wrap_content"
           android:layout_centerInParent="true"
           android:layout_marginLeft="30dp"/>
 ``` 

设置类型、提示内容：
```Java
    picker.setTypeCountLimitAndToasLimittStr(AddSubNumberPicker.TYPE_UNSIGN_NUMBER, "不能小于0");
        设置CountChangeListner：
                picker.setCountChangeListner(new AddSubNumberPicker.CountChangeListner() {
                    @Override
                    public void onCountChanged(int count) {
                        Log.d("MainActivity", "count changed, count = "+count);
                    }
                });
```
