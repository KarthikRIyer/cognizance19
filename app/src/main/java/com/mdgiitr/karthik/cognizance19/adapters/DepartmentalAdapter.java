package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.Departmental;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DepartmentalAdapter extends RecyclerView.Adapter<DepartmentalAdapter.DepartmentalViewHolder> {

    List<Departmental> list;
    Context context;
    int screenWidth = 0;

    public DepartmentalAdapter(Context context, List<Departmental> list, int screenWidth) {
        this.context = context;
        this.list = list;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public DepartmentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_departmental, parent, false);

        return new DepartmentalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentalViewHolder holder, int position) {
        Departmental departmentalModel = list.get(position);
        holder.deptIcon.getLayoutParams().height = (screenWidth / 2) - 100;
        holder.deptIcon.getLayoutParams().width = (screenWidth / 2) - 100;
        holder.deptNameView.setText(departmentalModel.getName());
        holder.deptIcon.requestLayout();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DepartmentalViewHolder extends RecyclerView.ViewHolder {
        public TextView deptNameView;
        public CircleImageView deptIcon;

        public DepartmentalViewHolder(@NonNull View itemView) {
            super(itemView);
            deptNameView = itemView.findViewById(R.id.dept_nameTextView);
            deptIcon = itemView.findViewById(R.id.department_image);
        }
    }

}