package HKR.HKIF.fragments;

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

import HKR.HKIF.R;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private ListView sportListView;
    private ListView upCommingEventListView;
    private Activity activity;
    private URL url;
    Bitmap img;
    Drawable image;
    InputStream is;

    NavigationView navigationView1;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        navigationView1 = getActivity().findViewById(R.id.nav_view);


        activity = getActivity();
        sportListView = getActivity().findViewById(R.id.lvMain);
        //upCommingEventListView = getActivity().findViewById(R.id.events);


        // setUpcommingEventListView();
        setupSportListView();
    }

//    private void setUpcommingEventListView(){
//        String description = "No description";
//
//        EventItems_Adapter adapter = new EventItems_Adapter(activity,description);
//        upCommingEventListView.setAdapter(adapter);
//
//    }

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


//
//
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(){}
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        public Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }

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


//    public class EventItems_Adapter extends BaseAdapter{
//        private Context mContext;
//        private LayoutInflater layoutInflater;
//        private TextView description;
//        private String descriptionArray;
//        private ImageView imageView;
//
//
//        public EventItems_Adapter(Context context, String description){
//            mContext = context;
//            descriptionArray = description;
//            layoutInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                convertView = layoutInflater.inflate(R.layout.single_home_item2, null);
//            }
//
//            description = convertView.findViewById(R.id.event_description);
//            imageView = convertView.findViewById(R.id.ivMain);
//
//            description.setText("No description");
//
//
//            try
//            {
//                URL url = new URL("https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwiY99GkoIvhAhUM0qYKHRVTBr8QjRx6BAgBEAU&url=https%3A%2F%2Fwww.soccerladuma.co.za%2Fnews%2Farticles%2Fcategories%2Fmessi-ronaldo-neymar-watch-1%2F11-best-left-footed-players-in-football-right-now%2F283728&psig=AOvVaw3nLhSjlWVOraeW1Y1di0-3&ust=1552983410573526");
//                InputStream is = new BufferedInputStream(url.openStream());
//                img = BitmapFactory.decodeStream(is);
//            } catch(Exception e){}
//            imageView.setImageBitmap(img);
//            //imageView.setImageDrawable(image);
//
//
//            return convertView;
//
//        }
//
//    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
