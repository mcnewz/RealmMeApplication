package com.manit.krungsri.realmmeapplication.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.manit.krungsri.realmmeapplication.R;

/**
 * Created by MCNEWZ on 03-Dec-17.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView ivStudent;
    private TextView tvStudnt, tvDes1, tvDes2;
    private ItemClickListener clickListener;

    public StudentViewHolder(View itemView) {
        super(itemView);
        tvStudnt = itemView.findViewById(R.id.tvStudent);
        tvDes1 = itemView.findViewById(R.id.tvDes1);
        tvDes2 = itemView.findViewById(R.id.tvDes2);
        ivStudent = itemView.findViewById(R.id.ivStudent);

        ivStudent.setImageResource(R.mipmap.ic_launcher_round);
        itemView.setOnClickListener(this);
    }


    public void setTvStudnt(String text) {
        tvStudnt.setText(text);
    }

    public void setTvDes1(String text) {
        tvDes1.setText(text);


    }

    public void setTvDes2(String text) {
        tvDes2.setText(text);


    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getAdapterPosition(), false);
    }


}
