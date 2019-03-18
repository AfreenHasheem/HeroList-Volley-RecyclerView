package com.example.volleyrecyclerviewapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    private List<HeroList> heroList;
    private Context mContext;
    private static int currentPosition = 0;

    public HeroAdapter(List<HeroList> heroList, Context mContext) {
        this.heroList = heroList;
        this.mContext = mContext;
    }
    @Override
    public HeroAdapter.HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes, parent, false);
        return new HeroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeroAdapter.HeroViewHolder holder, final int position) {
        HeroList hero = heroList.get(position);
        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealname());
        holder.textViewTeam.setText(hero.getTeam());
        holder.textViewFirstAppearance.setText(hero.getFirstappearance());
        holder.textViewCreatedBy.setText(hero.getCreatedby());
        holder.textViewPublisher.setText(hero.getPublisher());
        holder.textViewBio.setText(hero.getBio().trim());

        Glide.with(mContext).load(hero.getImageurl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position){
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance, textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;

         HeroViewHolder(View itemView) {
            super(itemView);

             textViewName = (TextView) itemView.findViewById(R.id.textViewName);
             textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
             textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
             textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
             textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
             textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
             textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
             imageView = (ImageView) itemView.findViewById(R.id.imageView);

             linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
