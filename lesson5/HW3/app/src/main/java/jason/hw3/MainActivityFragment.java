package jason.hw3;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.provider.Settings;
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
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

// Get rid of boilerplate placeholder comments like the one below
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    // Unnecessary comment
//    bindview variables
    @BindView(R.id.list_task) ListView listView;
    @BindView(R.id.button) Button myButton;
    @BindView(R.id.buttonAdd) Button addButton;

    public MainActivityFragment() {
    }

    private ArrayList<String> tasks;
    private TaskAdapter taskAdapter;
    private toDoHelper todoTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Unnecessary level of commenting, only comment when it's unclear what the line is doing
//        initiate butter knife
        ButterKnife.bind(this, view);

        // To read
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        // Default value for when the file is unavilable
        String defaultValue = getResources().getString(R.string.backgroundColor_default);
        String background = sharedPref.getString(getString(R.string.backgroundColor_key), defaultValue);
        //get the view of the main activity
        View mainView = getActivity().findViewById(R.id.mainAct);
        if (background.equals("WHITE")){
            mainView.setBackgroundColor(Color.WHITE);
        } else if (background.equals("RED")){
            mainView.setBackgroundColor(Color.RED);
        } else if (background.equals("BLUE")){
            mainView.setBackgroundColor(Color.BLUE);
        } else if (background.equals("GREEN")){
            mainView.setBackgroundColor(Color.GREEN);
        }

        // Comments should be indented the same as other lines

//        tasks = new ArrayList<String>();
        todoTable = new toDoHelper(this.getContext());
        tasks = loadToDos();
//        tasks = new ArrayList<String>();
        taskAdapter = new TaskAdapter(getActivity(),tasks);
        listView.setAdapter(taskAdapter);

//        set the setting button
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new settingFragment();
//                switch between fragments
                ((MainActivity) getActivity()).transitionToFragment(fragment);
            }
        });

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
        SQLiteDatabase db = todoTable.getWritableDatabase();
        // Why is this line commented out?
//        todoTable.addToDo(db,str);
        ContentValues values = new ContentValues();
        values.put(todoSchema.todoEntry.columnOne, str);
        values.put(todoSchema.todoEntry.columnTwo, "notdone");
        db.insert(todoSchema.todoEntry.TABLE_NAME, null, values);
        taskAdapter.notifyDataSetChanged();
    }

    /*
     This functionality dealing with the database should all be inside the helper. It shouldn't be a fragment's
     responsibility to deal directly with the database and perform queries.
      */
    private ArrayList<String> loadToDos(){
        SQLiteDatabase readDb = todoTable.getReadableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        String[] projection = {
                todoSchema.todoEntry.columnOne,
                todoSchema.todoEntry.columnTwo
        };
        String selection = todoSchema.todoEntry.columnTwo + " = ?";
        String[] selectionArgs = {"notdone"};
        Cursor toDoCursor = readDb.query(
                todoSchema.todoEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        while (toDoCursor.moveToFirst() && !toDoCursor.isLast()){
            addItem(res, toDoCursor);
            toDoCursor.moveToNext();
        }
        return res;
    }

    /*
     Again, this shouldn't be in the fragment  (it should be in the TodoHelper
     You shouldn't be dealing with database queries here
     Also, it doesn't seem like this method is actually doing anything to add the todos to the database...
      */
    private void addItem(ArrayList<String> todos, Cursor cursor){
        String content = cursor.getString(cursor.getColumnIndexOrThrow(todoSchema.todoEntry.columnOne));
        String status = cursor.getString(cursor.getColumnIndexOrThrow(todoSchema.todoEntry.columnTwo));
        todos.add(content);
    }

}
