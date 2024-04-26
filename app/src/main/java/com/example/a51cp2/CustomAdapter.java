package com.example.a51cp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;

    List<String> playlists;

    CustomAdapter(Context context, List<String> playlists){
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.link.setText(String.valueOf(playlists.get(position)));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView link;

        ConstraintLayout playlistLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            link = itemView.findViewById(R.id.link);
            playlistLayout = itemView.findViewById(R.id.playlistLayout);
        }
    }
}
