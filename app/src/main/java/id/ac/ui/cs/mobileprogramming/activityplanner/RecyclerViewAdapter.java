package id.ac.ui.cs.mobileprogramming.activityplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.activityplanner.databinding.ActivityListItemBinding;
import id.ac.ui.cs.mobileprogramming.activityplanner.model.Activity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Activity> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public RecyclerViewAdapter(List<Activity> list) {
        this.list = list;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Activity getItemAt(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ActivityListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.day.setText(list.get(position).getDay());
        holder.binding.activity.setText(list.get(position).getActivity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ActivityListItemBinding binding;

        public ViewHolder(@NonNull ActivityListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
