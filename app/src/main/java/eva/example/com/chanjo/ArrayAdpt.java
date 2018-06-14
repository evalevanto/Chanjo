package eva.example.com.chanjo;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by eva on 10/13/16.
 */
public class ArrayAdpt extends ArrayAdapter<String> {
    public ArrayList<String> lst;

    public ArrayAdpt(Context context, int resource, ArrayList<String> lst) {
        super(context, resource,lst);
        this.lst = lst;

    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public String getItem(int position) {
        return lst.get(position);
    }
}
