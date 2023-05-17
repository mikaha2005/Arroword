/*package com.example.project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class GrAdapter extends ArrayAdapter<String>{

    public GrAdapter(@NonNull Context context, ArrayList<String> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int row, int col, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        String courseModel = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);

        courseTV.setText(courseModel);
        return listitemView;
    }
}*/
