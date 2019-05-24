package HKR.HKIF.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;

import HKR.HKIF.R;

public class AboutFragment extends Fragment {

    private PDFView pdfView;
    private WebView webView;

    public AboutFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //pdfView = (PDFView) getActivity().findViewById(R.id.pdfView);
        //pdfView.fromAsset("OperatingSys_Report.pdf").load();

        webView = (WebView) getActivity().findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.hkif.se/sida/?ID=211544");
        //webView.getSettings().setJavaScriptEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
