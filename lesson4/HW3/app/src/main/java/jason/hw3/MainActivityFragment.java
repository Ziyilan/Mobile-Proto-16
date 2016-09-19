package jason.hw3;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private ArrayList<String> tasks;
    private TaskAdapter taskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tasks = new ArrayList<String>();
        taskAdapter = new TaskAdapter(getActivity(),tasks);
        ListView listView = (ListView) view.findViewById(R.id.list_task);
        listView.setAdapter(taskAdapter);



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

        Button addButton = (Button) view.findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(v.getContext()).create();
                alert.setTitle("Add Task");
                // Set up the input
                final EditText input = new EditText(v.getContext());
                // Specify the type of input expected
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alert.setView(input);
                // Set up the buttons
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alert.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = input.getText().toString();
                        addTask(str);
                    }
                });
                alert.show();
            }
        });


        return view;
    }

    public void addTask(String str){
        tasks.add(str);
        taskAdapter.notifyDataSetChanged();
    }

}
