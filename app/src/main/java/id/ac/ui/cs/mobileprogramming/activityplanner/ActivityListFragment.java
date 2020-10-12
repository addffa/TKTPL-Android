package id.ac.ui.cs.mobileprogramming.activityplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.activityplanner.databinding.FragmentActivityListBinding;
import id.ac.ui.cs.mobileprogramming.activityplanner.model.Activity;

public class ActivityListFragment extends Fragment {
    private FragmentActivityListBinding binding;
    private ActivityDetailFragment activityDetailFragment = new ActivityDetailFragment();

    public ActivityListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activity_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Activity> list = new ArrayList<>();
        list.add(new Activity("Monday", "Debugging", "Learn about different types of errors in Java and practice finding them"));
        list.add(new Activity("Monday", "Design patterns", "Learn more about Observer and Adapter patterns"));
        list.add(new Activity("Tuesday", "Algorithms", "Study quick sort algorithm"));

        final SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                viewModel.setSelected(adapter.getItemAt(position));
                ActivityListFragment.this.getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, activityDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}