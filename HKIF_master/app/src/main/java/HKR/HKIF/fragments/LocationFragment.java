package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import HKR.HKIF.R;
import HKR.HKIF.adapters.ViewPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/*import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;*/

public class LocationFragment extends Fragment implements OnMapReadyCallback {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    GoogleMap mGoogleMap;
    MapView mapView;
    View view;

    public LocationFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tablayout_id);
        viewPager = getActivity().findViewById(R.id.viewpager_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.AddFragment(new CampusFragment(), "Hkr Building 7");
        viewPagerAdapter.AddFragment(new OffCampusFragment(), "City Centre");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location, container, false);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(56.048688, 14.146171)).title("HKR HKIF").snippet("Sport for life"));
        CameraPosition university = CameraPosition.builder().target(new LatLng(56.048688, 14.146171)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(university));
    }
}
