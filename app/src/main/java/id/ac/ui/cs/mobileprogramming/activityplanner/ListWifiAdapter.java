package id.ac.ui.cs.mobileprogramming.activityplanner;

import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListWifiAdapter extends RecyclerView.Adapter<ListWifiAdapter.ViewHolder> {

    List<ScanResult> scanResults;

    public ListWifiAdapter() {
    }

    public void updateWifi(List<ScanResult> scanResults) {
        this.scanResults = scanResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListWifiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifi_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListWifiAdapter.ViewHolder holder, int position) {
        ScanResult result = scanResults.get(position);
        holder.textView.setText(result.SSID + " " + result.BSSID);
    }

    @Override
    public int getItemCount() {
        if (scanResults != null) {
            return  scanResults.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v;
        }
    }
}
