package com.tahlia.annotation.retention_runtime.type;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tahlia.annotation.R;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class TypeActivity<T> extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TypeActivity";

    private ResponseData<Integer, List<Data>> responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        findViewById(R.id.parameterizedType_btn).setOnClickListener(this);
        findViewById(R.id.typeVariable_btn).setOnClickListener(this);
        findViewById(R.id.genericArrayType_btn).setOnClickListener(this);
        findViewById(R.id.wildcardType_btn).setOnClickListener(this);

        initData();
    }

    private void initData() {
        responseData = new ResponseData<>();
        responseData.setCode(0);
        responseData.setMsg("default data");
        responseData.setData1(18);
        Data data = new Data("tahlia");
        List<Data> list = new ArrayList<>();
        list.add(data);
        responseData.setData2(list);
    }

    @Override
    public void onClick(View v) {
        if (responseData == null) {
            initData();
        }
        switch (v.getId()) {
            case R.id.parameterizedType_btn:
                parseParameterizedType();
                break;
            case R.id.typeVariable_btn:
                parseTypeVariable();
                break;
            case R.id.genericArrayType_btn:
                parseGenericArrayType();
                break;
            case R.id.wildcardType_btn:
                parseWildCardType();
                break;
        }
    }

    private void parseParameterizedType() {

        try {
            // responseData: ResponData<T, D>, 其中T是Integer类型，D是List<Data>类型(List<E>也是泛型应用)
            Field response = TypeActivity.class.getDeclaredField("responseData");
            // 形似xxx<T>的泛型组合形式的，使用ParameterizedType承载。
            ParameterizedType parameterizedType = (ParameterizedType) response.getGenericType();
            // rawType获取的是xxx
            Log.d(TAG, "parameterizedType.getRawType(): "+parameterizedType.getRawType());
            // actualTypeArgs是真实类型的Type[]， 因为可能不止一个泛型，比如xxxx<K,V>的形式.
            // 这个方法只能剥离最外层的<>，获取到里面K，V的内容
            Type[] args = parameterizedType.getActualTypeArguments();
            for (Type arg : args) {
                Log.d(TAG, "parameterizedType.getActualTypeArguments(): "+arg);
            }
            // xxx<T>所在的外部类，如果不是内部类，返回null
            Log.d(TAG, "parameterizedType.getOwnerType(): "+parameterizedType.getOwnerType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void parseTypeVariable() {
        // responseData: ResponData<T, D>, 其中T是Integer类型，D是List<Data>类型(List<E>也是泛型应用)
        try {
            Field data1 = ResponseData.class.getDeclaredField("data1");
            // 获取的形式是T data的形式，因此使用TypeVariable承载
            TypeVariable typeVariable1 = (TypeVariable) data1.getGenericType();
            // 泛型声明的那个占位符的名字，声明的时候用的是T data， 返回T。如果是D data， 返回D
            Log.d(TAG, "typeVariable1.getName(): "+typeVariable1.getName());
            // 获取承载T的类型，即 xxx<T>, 返回xxx
            Log.d(TAG, "typeVariable1.getGenericDeclaration(): "+typeVariable1.getGenericDeclaration());
            // 获取上界信息。如果没有默认是Object
            // 返回的是Type， 因为可能不止一个，泛型可以单继承和多实现，用&连接，但必须继承在前实现在后，即&后的必须是接口
            Type[] bounds1 = typeVariable1.getBounds();
            for (Type bound : bounds1) {
                Log.d(TAG, "typeVariable1.getBounds(): "+bound);
            }

            // 上面是data1的解析，下面是data2的解析
            Field data2 = ResponseData.class.getDeclaredField("data2");
            TypeVariable typeVariable2 = (TypeVariable) data2.getGenericType();
            Log.d(TAG, "typeVariable2.getName(): "+typeVariable2.getName());
            Log.d(TAG, "typeVariable2.getGenericDeclaration(): "+typeVariable2.getGenericDeclaration());
            Type[] bounds2 = typeVariable2.getBounds();
            for (Type bound : bounds2) {
                Log.d(TAG, "typeVariable2.getBounds(): "+bound);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // 为了方便查看，把定义放在了这里
    private List<String>[][] listStrings;
    private List<String>[] strings;
    private T[] datas;
    // 必须是带泛型的数组，非泛型数组通过getGenericType获取到的类型是Class
//    private Data[] datas;
    private void parseGenericArrayType() {
        try {
            Field field1 = TypeActivity.class.getDeclaredField("listStrings");
            // 因为是数组，所以用GenericArrayType承载
            GenericArrayType arrayType1 = (GenericArrayType) field1.getGenericType();
            // 返回去掉最外围数组后的Type类型。
            // 二维数组->一维数组，本质上还是数组，因此类型是GenericArrayType
            // List<String>[] -> List<String>, 类型是ParameterizedType
            // T[] -> T， 类型是TypeVariable
            Type genericComponentType1 = arrayType1.getGenericComponentType();

            Field field2 = TypeActivity.class.getDeclaredField("strings");
            GenericArrayType arrayType2 = (GenericArrayType) field2.getGenericType();
            Type genericComponentType2 = arrayType2.getGenericComponentType();

            Field field3 = TypeActivity.class.getDeclaredField("datas");
            GenericArrayType arrayType3 = (GenericArrayType) field3.getGenericType();
            Type genericComponentType3 = arrayType3.getGenericComponentType();

            Log.d(TAG, "parseGenericArrayType: ");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // 为了方便查看，把定义放在了这里
    private List<? extends Number> list1;
    private List<? super Integer> list2;
    private void parseWildCardType() {
        try {
            Field listField1 = TypeActivity.class.getDeclaredField("list1");
            ParameterizedType parameterizedType1 = (ParameterizedType) listField1.getGenericType();
            Type[] actualTypeArguments1 = parameterizedType1.getActualTypeArguments();
            for (Type type : actualTypeArguments1) {
                WildcardType wildcardType1 = (WildcardType) type;
                Type[] lowerBounds1 = wildcardType1.getLowerBounds();
                Type[] upperBounds1 = wildcardType1.getUpperBounds();
                for (Type lowBound : lowerBounds1) {
                    Log.d(TAG, "wildcardType1.getLowerBounds(): "+lowBound);
                }
                for (Type upperBound : upperBounds1) {
                    Log.d(TAG, "wildcardType1.getUpperBounds(): "+upperBound);
                }
            }

            Field listField2 = TypeActivity.class.getDeclaredField("list2");
            ParameterizedType parameterizedType2 = (ParameterizedType) listField2.getGenericType();
            Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
            for (Type type : actualTypeArguments2) {
                WildcardType wildcardType2 = (WildcardType) type;
                Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                Type[] upperBounds2 = wildcardType2.getUpperBounds();
                for (Type lowBound : lowerBounds2) {
                    Log.d(TAG, "wildcardType2.getLowerBounds(): "+lowBound);
                }
                for (Type upperBound : upperBounds2) {
                    Log.d(TAG, "wildcardType2.getUpperBounds(): "+upperBound);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
