package HKR.HKIF.utilities;

import HKR.HKIF.R;
import HKR.HKIF.fragments.HomeFragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoadExtention extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_extention);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }
}
