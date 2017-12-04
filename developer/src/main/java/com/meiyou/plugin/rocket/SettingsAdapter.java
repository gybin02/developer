package com.meiyou.plugin.rocket;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.meiyou.jet.developer.R;
import com.meiyou.plugin.rocket.common.PrefHelper;
import com.meiyou.plugin.rocket.common.RocketUtil;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 构造View Adapter
 * It is used to create setting items by using the given config class
 */
class SettingsAdapter extends BaseAdapter {

    private static final String TAG = "Rocket";

    private final List<MethodInfo> list;
    private final LayoutInflater inflater;
    private final Context context;

    private SettingsAdapter(Context context, List<MethodInfo> list) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    static BaseAdapter newInstance(Context context, List<MethodInfo> list) {
        return new SettingsAdapter(context, list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MethodInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        MethodInfo methodInfo = getItem(position);
        switch (getItemViewType(position)) {
            case MethodInfo.VIEW_BUTTON:
                return createButton(parent, methodInfo);
            case MethodInfo.VIEW_CHECKBOX:
                return createCheckBox(parent, methodInfo);
            case MethodInfo.VIEW_SPINNER:
                return createSpinner(parent, methodInfo);
            case MethodInfo.VIEW_TEXTAREA:
                return createTextArea(parent, methodInfo);
            case MethodInfo.VIEW_EDITTEXT:
                return createEditText(parent, methodInfo);
            default:
                throw new IllegalArgumentException("view type should be one of the following: BUTTON, CHECKBOX, SPINNER");
        }
    }

    private View createEditText(ViewGroup parent, MethodInfo methodInfo) {
        final Method method = methodInfo.getMethod();
        final Object instance = methodInfo.getInstance();

        View view = inflater.inflate(R.layout.item_settings_input, parent, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        String text = methodInfo.getTitle();
        if (!TextUtils.isEmpty(text)) {
            title.setText(text);
        }

        final EditText editText = (EditText) view.findViewById(R.id.text_input);
        Button btn = (Button) view.findViewById(R.id.btn);
        Object data = methodInfo.getData();
        if (data != null) {
            String btnText = (String) data;
            if (!TextUtils.isEmpty(btnText)) {
                editText.setHint(btnText);
            }
        }

        final String methodName = method.getName();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = editText.getText().toString();
                if (TextUtils.isEmpty(uri)) {
                    Toast.makeText(context, "editText is Null", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    method.invoke(instance, uri);
                    PrefHelper.setString(context, methodName, uri);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        //上次记录
        try {
            Log.e(TAG, "pref: Key: " + methodName);
            Map<String, ?> all = PrefHelper.getPrefs(context).getAll();
            Iterator<? extends Map.Entry<String, ?>> iterator = all.entrySet().iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                Map.Entry<String, ?> entry = iterator.next();
                stringBuilder.append(entry.getKey())
                             .append(":")
                             .append(entry.getValue())
                             .append("；  ");
            }
            String alllStr = stringBuilder.toString();
            Log.e(TAG, "createEditText: " + alllStr);
            
            String string = PrefHelper.getString(context, methodName);


            if (!TextUtils.isEmpty(string)) {
                editText.setText(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private View createTextArea(ViewGroup parent, MethodInfo methodInfo) {
        final Method method = methodInfo.getMethod();
        final Object instance = methodInfo.getInstance();

        View view = inflater.inflate(R.layout.item_settings_text, parent, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(methodInfo.getTitle());
        TextView text_area = (TextView) view.findViewById(R.id.text_area);
        String content = "";
        try {
            content = (String) method.invoke(instance);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        text_area.setText(content);
        final String finalContent = content;
        text_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketUtil.copy(context, finalContent);
            }
        });

        return view;
    }

    private View createSpinner(ViewGroup parent, MethodInfo methodInfo) {

        final Method method = methodInfo.getMethod();
        final Object instance = methodInfo.getInstance();
        final Context context = parent.getContext();

        View view = inflater.inflate(R.layout.item_settings_spinner, parent, false);
        ((TextView) view.findViewById(R.id.title)).setText(methodInfo.getTitle());

        String[] dataList = (String[]) methodInfo.getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                parent.getContext(), R.layout.simple_spinner_item, dataList
        );

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);

                try {
                    method.invoke(instance, value);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

                PrefHelper.setInt(context, method.getName(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setSelection(PrefHelper.getInt(context, method.getName()));
        return view;
    }

    private View createButton(ViewGroup parent, MethodInfo methodInfo) {

        final Method method = methodInfo.getMethod();
        final Object instance = methodInfo.getInstance();

        View view = inflater.inflate(R.layout.item_settings_button, parent, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setText((String) methodInfo.getData());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    method.invoke(instance);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        return view;
    }

    private View createCheckBox(ViewGroup parent, MethodInfo methodInfo) {
        final boolean isTemp = (boolean) methodInfo.getData();
        final Method method = methodInfo.getMethod();
        final Object instance = methodInfo.getInstance();
        final Context context = parent.getContext();

        View view = inflater.inflate(R.layout.item_settings_checkbox, parent, false);
        ((TextView) view.findViewById(R.id.title)).setText(methodInfo.getTitle());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setChecked(PrefHelper.getBoolean(context, method.getName()));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    method.invoke(instance, isChecked);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                if (!isTemp) {
                    PrefHelper.setBoolean(context, method.getName(), isChecked);
                }

            }
        });

        return view;
    }

}
