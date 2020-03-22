package ru.nedoluzhko.homework_1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    List<FragmentList.MyData> listData;
    FragmentList.OnItemSelectedListener listener;

    public MyDataAdapter(
            List<FragmentList.MyData> data,
            FragmentList.OnItemSelectedListener listener) {
        this.listData = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FragmentList.MyData listItem = listData.get(position);

        holder.textView.setText(listItem.text);

        Context textViewContext = holder.textView.getContext();
        holder.textView.setTextColor(ContextCompat.getColor(textViewContext, listItem.color));
    }

    @Override
    public int getItemCount() {
        if (listData != null)
            return listData.size();
        return 0;
    }

    public void addItem() {
        listData.add(new FragmentList.MyData(listData.size() + 1));
        notifyItemInserted(listData.size() - 1);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listTextView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos + 1);
                }
            });
        }
    }
}
