package MyView;

import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public interface IView<S extends ViewModel> {
    void setData(S data,int position);
    View getView();
}
