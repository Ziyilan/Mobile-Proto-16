package jason.hw3;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zlan on 9/18/16.
 */
public class TaskAdapter extends ArrayAdapter<String> {

    private ArrayList<String> tasklist;

    public TaskAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
        tasklist = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final String task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_template, parent, false);
        }

        Button deleteBut = (Button) convertView.findViewById(R.id.buttonDel);
        Button completeBut = (Button) convertView.findViewById(R.id.buttonComplete);
        TextView text = (TextView) convertView.findViewById(R.id.template_content);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Edit Task");
                // Set up the input
                final EditText input = new EditText(view.getContext());
                // Specify the type of input expected;
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
                        tasklist.set(position,str);
                        notifyDataSetChanged();
                    }
                });
                alertDialog.show();
            }
        });

        deleteBut.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             tasklist.remove(position);
                                             notifyDataSetChanged();
                                         }
                                     });

                createTask(convertView, task);

        // Return the completed view to render on screen
        return convertView;
    }

    private void createTask(View convertView, String task){
        TextView Task = (TextView) convertView.findViewById(R.id.template_content);
        Task.setText(task);
    }

    public void edit(String str, int index){
        tasklist.set(index,str);
    }

}
