package HKR.HKIF.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import HKR.HKIF.R;

public class MessageFragment extends Fragment {

    //private EditText editTextTo;
    private EditText editTextSub;
    private EditText editTextMsg;

    public MessageFragment() {
    }

    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        //editTextTo = getActivity().findViewById(R.id.email_to);
        editTextSub = getActivity().findViewById(R.id.email_sub);
        editTextMsg = getActivity().findViewById(R.id.email_msg);
        Button btn = getActivity().findViewById(R.id.email_button);
        sendEmail();
    }

    private void sendEmail() {
        String recipientList = "hkif@hotmail.se";
        String[] recipients = recipientList.split(",");
        String subject = editTextSub.getText().toString();
        String message = editTextMsg.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }
}
