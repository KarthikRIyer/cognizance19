package com.mdgiitr.karthik.cognizance19.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mdgiitr.karthik.cognizance19.R;
import com.mdgiitr.karthik.cognizance19.models.Event;

import java.util.List;

import static com.mdgiitr.karthik.cognizance19.MainActivity.navController;

public class DepartmentalEventAdapter extends RecyclerView.Adapter<DepartmentalEventAdapter.DepartmentalEventViewHolder> {
    List<Event> eventList;
    Context context;

    public DepartmentalEventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public DepartmentalEventAdapter.DepartmentalEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_departmental_event, parent, false);

        return new DepartmentalEventAdapter.DepartmentalEventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentalEventAdapter.DepartmentalEventViewHolder holder, int position) {
        Event model = eventList.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.home_menu_gray_card)
                .error(R.drawable.home_menu_gray_card);
        Glide.with(context)
                .load(model.getImageURL())
                .apply(options)
                .into(holder.eventPic);
        holder.eventName.setText(model.getName());
        holder.eventTagline.setText(model.getTagline());
        Bundle bundle = new Bundle();
        bundle.putInt("id", model.getID());
        holder.eventCard.setOnClickListener(v -> navController.navigate(R.id.action_centerStageAndDepartmentalFragment_to_speceficEventFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class DepartmentalEventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventPic;
        public TextView eventName, eventTagline;
        public CardView eventCard;

        public DepartmentalEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPic = itemView.findViewById(R.id.departmental_event_image);
            eventName = itemView.findViewById(R.id.departmental_event_name);
            eventTagline = itemView.findViewById(R.id.departmental_event_tagline);
            eventCard = itemView.findViewById(R.id.event_card);
        }
    }
}
