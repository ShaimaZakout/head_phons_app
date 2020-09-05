package com.example.headphonsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeadAdapter extends RecyclerView.Adapter<HeadAdapter.BookViewHolder> {
    private ArrayList<HeadModel> headModels;
    private Context context;
    RecViewOnclickIteam recViewOnclickIteam;

    interface RecViewOnclickIteam {
        void onClick(int bostion);
    }

    public void setRecViewOnclickIteam(RecViewOnclickIteam recViewOnclickIteam) {
        this.recViewOnclickIteam = recViewOnclickIteam;
    }

    public HeadAdapter(ArrayList<HeadModel> headModels, Context context) {
        this.headModels = headModels;
        this.context = context;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImge;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recViewOnclickIteam != null) {
                        int postion = getAdapterPosition();
                        if (postion != RecyclerView.NO_POSITION) {
                            recViewOnclickIteam.onClick(postion);
                        }
                    }

                }
            });
        }

        public void setImageView(int settingImage) {
            bookImge = itemView.findViewById(R.id.custom_img);
            bookImge.setImageResource(settingImage);


        }


    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.head_phon_iteam, null, false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        int settingImage = headModels.get(position).getImg();
        holder.setImageView(settingImage);

    }

    @Override
    public int getItemCount() {
        return headModels.size();
    }


}
