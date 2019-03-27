package com.e.hkif_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.net.URL;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {
    private ListView sportListView;
    private ListView upCommingEventListView;
    private Activity activity;


    NavigationView navigationView1;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        navigationView1 = getActivity().findViewById(R.id.nav_view);


        activity = getActivity();
        sportListView = getActivity().findViewById(R.id.lvMain);

        setupSportListView();
    }

    private void setupSportListView(){

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);


        SportItems_Adapter simpleAdapter = new SportItems_Adapter(activity, title, description);
        sportListView.setAdapter(simpleAdapter);

        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        navigationView1.getMenu().clear();
                        navigationView1.inflateMenu(R.menu.drawer_navigation_team_leader);

                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }
                }
            }
        });
    }

    public class SportItems_Adapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SportItems_Adapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.single_home_item1, null);
            }

            title = convertView.findViewById(R.id.tvMain);
            imageView = convertView.findViewById(R.id.ivMain);


            title.setText(titleArray[position]);


            if(titleArray[position].equalsIgnoreCase("FOOTBALL")){
                imageView.setImageResource(R.drawable.soccer);
            }else if(titleArray[position].equalsIgnoreCase("VOLLEYBALL")){
                imageView.setImageResource(R.drawable.volleyball);
            }else if(titleArray[position].equalsIgnoreCase("BADMINTON")){
                imageView.setImageResource(R.drawable.badminton);
            }else if(titleArray[position].equalsIgnoreCase("CLIMBING")){
                imageView.setImageResource(R.drawable.climbing);
            } else if(titleArray[position].equalsIgnoreCase("SWIMMING")){
                imageView.setImageResource(R.drawable.swimming);
            } else if(titleArray[position].equalsIgnoreCase("BOXING")){
                imageView.setImageResource(R.drawable.boxing);
            }else if(titleArray[position].equalsIgnoreCase("JU_JITSU")){
                imageView.setImageResource(R.drawable.ju_jitsu);
            }

            return convertView;

        }
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
