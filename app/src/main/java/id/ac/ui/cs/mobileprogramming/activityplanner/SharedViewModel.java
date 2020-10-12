package id.ac.ui.cs.mobileprogramming.activityplanner;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.activityplanner.model.Activity;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Activity> selected = new MutableLiveData<>();

    public void setSelected(Activity item) {
        selected.setValue(item);
    }

    public MutableLiveData<Activity> getSelected() {
        return selected;
    }
}
