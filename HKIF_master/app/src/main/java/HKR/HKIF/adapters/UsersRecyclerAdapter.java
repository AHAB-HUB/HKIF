package HKR.HKIF.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import HKR.HKIF.R;
import HKR.HKIF.data.NotificationData;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<NotificationData> listUsers;



    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.title.setText(listUsers.get(position).getTitle());
        holder.message.setText(listUsers.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    public UsersRecyclerAdapter(List<NotificationData> listUsers) {
        this.listUsers = listUsers;
    }

    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView title;
        private AppCompatTextView message;

        public UserViewHolder(View view) {
            super(view);
            title    =  view.findViewById(R.id.notification_title);
            message  =  view.findViewById(R.id.notification_message);
        }
    }


}
