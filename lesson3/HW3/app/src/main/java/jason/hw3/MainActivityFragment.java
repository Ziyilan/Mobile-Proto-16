package jason.hw3;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

//      instantiating all the textviews
        final TextView text1 = (TextView) view.findViewById(R.id.textView1);
        final TextView text2 = (TextView) view.findViewById(R.id.textView2);
        final TextView text3 = (TextView) view.findViewById(R.id.textView3);
        final TextView text4 = (TextView) view.findViewById(R.id.textView4);
        final TextView text5 = (TextView) view.findViewById(R.id.textView5);


        // Look how clean this is!
        text1.setOnClickListener(new CustomOnClick(text1));
        text2.setOnClickListener(new CustomOnClick(text2));
        text3.setOnClickListener(new CustomOnClick(text3));
        text4.setOnClickListener(new CustomOnClick(text4));
        text5.setOnClickListener(new CustomOnClick(text5));

//        set the setting button
        Button myButton = (Button) view.findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new settingFragment();
//                switch between fragments
                ((MainActivity) getActivity()).transitionToFragment(fragment);
            }
        });


        return view;
    }

    private class CustomOnClick implements View.OnClickListener {
        private TextView t;

        public CustomOnClick(TextView t) {
            this.t = t;
        }

        @Override
        public void onClick(View v) {
            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
            alertDialog.setTitle("Edit Task");
            // Set up the input
            final EditText input = new EditText(v.getContext());
            // Specify the type of input expected
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            alertDialog.setView(input);
            // Set up the buttons
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String str = input.getText().toString();
                    t.setText(str);
                }
            });
            alertDialog.show();
        }
    }
}
