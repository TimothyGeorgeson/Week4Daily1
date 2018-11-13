package com.example.consultants.week4daily1.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consultants.week4daily1.R;
import com.example.consultants.week4daily1.model.Person;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    List<Person> personList;

    public RecyclerViewAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == LOADING)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress, viewGroup, false);
        }
        else
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Person person = personList.get(i);

        if(getItemViewType(i) == ITEM) {
            viewHolder.ivImage.setImageBitmap(person.getImage());
            viewHolder.tvName.setText((person.getName()));
            viewHolder.tvAge.setText("Age: " + person.getAge());
            viewHolder.tvGender.setText("Gender: " + person.getGender());
            viewHolder.tvCountry.setText("Country: " + person.getCountry());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == personList.size() - 1) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvName;
        private final TextView tvAge;
        private final TextView tvGender;
        private final TextView tvCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }
}
