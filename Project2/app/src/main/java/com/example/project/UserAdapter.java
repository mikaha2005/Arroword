
package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class UsersAdapter extends ArrayAdapter<User>{
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.textViewName);
        TextView time = convertView.findViewById(R.id.textViewTime);
        // Populate the data into the template view using the data object
        name.setText(user.getName());
        time.setText(user.getGameTime());
        // Return the completed view to render on screen
        return convertView;
    }

}


    /*private static final String TAG = "UserAdapter";
    private ArrayList<User> users;


    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    */
/*@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.name.setText(users.get(position).getName());
        holder.age.setText(users.get(position).getAge());
//*        String name = users.get(position).getName();
        int age=users.get(position).getAge();//*
*//*


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    }




   */
/* @Override
    public boolean isEnabled(int i) {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView age;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewName);
            age=itemView.findViewById(R.id.textViewAge);
            //relativeLayout=itemView.findViewById(R.id.recyclerview);
        }
        public TextView getName()
        {
            return this.name;
        }*/


    //}
//}

