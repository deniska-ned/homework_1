package ru.nedoluzhko.homework_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class FragmentNum extends Fragment {
    private FragmentList.MyData data;

    public static FragmentNum newInstance(int numItem) {
        FragmentNum fragmentNum = new FragmentNum();

        Bundle args = new Bundle();
        args.putInt("numItem", numItem);
        fragmentNum.setArguments(args);
        return fragmentNum;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new FragmentList.MyData(getArguments().getInt("numItem"));
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_num, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView =  view.findViewById(R.id.fragmentNumTextView);
        textView.setText(data.text);
        textView.setTextColor(ContextCompat.getColor(getContext(), data.color));
    }

    public int getNum(){
        return Integer.valueOf(data.text);
    }
}