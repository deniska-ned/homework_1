package ru.nedoluzhko.homework_1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {

    List<MyData> listData;

    private static String  NUM_COUNT_KEY = "numCountKey";

    private OnItemSelectedListener listener;
    private MyDataAdapter myDataAdapter;

    public interface OnItemSelectedListener {
        void onItemClick(int numItem);
    }

    public static FragmentList newInstance(int numCount){
        FragmentList fragmentList = new FragmentList();
        Bundle args = new Bundle();
        args.putInt(NUM_COUNT_KEY, numCount);
        fragmentList.setArguments(args);
        return fragmentList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentList.OnItemSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int numCount;
        if (savedInstanceState != null){
            numCount = savedInstanceState.getInt(NUM_COUNT_KEY);
        } else {
            numCount = getArguments().getInt(NUM_COUNT_KEY);
        }

        listData = new ArrayList<>();
        for (int i = 1; i <= numCount; ++i)
            listData.add(new MyData(i));
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        myDataAdapter = new MyDataAdapter(listData, listener);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.columns_count)));
        recyclerView.setAdapter(myDataAdapter);

        Button addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataAdapter.addItem();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUM_COUNT_KEY, listData.size());
    }

    public static class MyData {
        public int color;
        public String text;

        MyData(int num) {
            this.color = num % 2 == 0 ? R.color.even : R.color.odd;
            this.text = Integer.valueOf(num).toString();
        }
    }
}
