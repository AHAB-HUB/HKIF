package HKR.HKIF.utilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import HKR.HKIF.R;


public class mapFragment extends Fragment {
    private WebView webView;

    public mapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        webView = (WebView) getActivity().findViewById(R.id.googelEarthWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://earth.google.com/web/@56.0485005,14.146265,10.54458947a,558.83792577d,35y,0h,45t,0r/data=ChMaEQoJL20vMDFmczBwGAIgASgC");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}



