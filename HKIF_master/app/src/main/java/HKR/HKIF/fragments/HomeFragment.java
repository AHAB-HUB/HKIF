package HKR.HKIF.fragments;

import android.app.Activity;
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

import HKR.HKIF.R;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private ListView sportListView;
    private Activity activity;
    private NavigationView navigationView1;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        navigationView1 = getActivity().findViewById(R.id.nav_view);

        activity = getActivity();
        sportListView = getActivity().findViewById(R.id.lvMain);

        setupSportListView();
    }

    private void setupSportListView() {

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);


        SportItems_Adapter simpleAdapter = new SportItems_Adapter(title, description);
        sportListView.setAdapter(simpleAdapter);

        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FootballFragment()).commit();
                        break;

                    case 1:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new VolleyballFragment()).commit();
                        break;

                    case 2:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new BadmintonFragment()).commit();
                        break;
                    case 3:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ClimbingFragment()).commit();
                        break;
                    case 5:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new SwimmingFragment()).commit();
                        break;
                    case 6:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new BoxingFragment()).commit();

                        break;
                    case 7:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new JuJitsuFragment()).commit();

                        break;
                    case 8:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FloorballFragment()).commit();

                        break;
                    case 9:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new BasketballFragment()).commit();
                        break;



                }
            }
        });
    }

    public class SportItems_Adapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SportItems_Adapter(String[] title, String[] description) {
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(getContext());
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
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.single_home_item1, null);
            }

            title = convertView.findViewById(R.id.tvMain);
            imageView = convertView.findViewById(R.id.ivMain);


            title.setText(titleArray[position]);


            if (titleArray[position].equalsIgnoreCase("FOOTBALL")) {
                imageView.setImageResource(R.drawable.soccer);
            } else if (titleArray[position].equalsIgnoreCase("VOLLEYBALL")) {
                imageView.setImageResource(R.drawable.volleyball);
            } else if (titleArray[position].equalsIgnoreCase("BADMINTON")) {
                imageView.setImageResource(R.drawable.badminton);
            } else if (titleArray[position].equalsIgnoreCase("CLIMBING")) {
                imageView.setImageResource(R.drawable.climbing);
            } else if (titleArray[position].equalsIgnoreCase("SWIMMING")) {
                imageView.setImageResource(R.drawable.swimming);
            } else if (titleArray[position].equalsIgnoreCase("BOXING")) {
                imageView.setImageResource(R.drawable.boxing);
            } else if (titleArray[position].equalsIgnoreCase("JU_JITSU")) {
                imageView.setImageResource(R.drawable.ju_jitsu);
            } else if (titleArray[position].equalsIgnoreCase("FLOORBALL")) {
                imageView.setImageResource(R.drawable.floorball);
            } else if (titleArray[position].equalsIgnoreCase("BASKETBALL")) {
                imageView.setImageResource(R.drawable.basketball);
            }

            return convertView;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
