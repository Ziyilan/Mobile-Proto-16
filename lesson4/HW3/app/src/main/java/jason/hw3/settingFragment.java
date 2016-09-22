package jason.hw3;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link settingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link settingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // Unneccessary and unused - delete
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // When copying a template, make sure to get rid of all the default code in there, e.g. mParam1, mParam2
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public settingFragment() {
        // Required empty public constructor
    }

    // Delete this as well - there's a lot of unnecessary code in this fragment
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingFragment newInstance(String param1, String param2) {
        settingFragment fragment = new settingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    /*
    This would have been a great place to put all of the color switching functionality in one place -
    the same 8 lines are written out three times, whereas you could have written a function that takes in a
    color parameter and does all of the color switching logic inside.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        // set the color buttons in this fragment
        Button myButton1 = (Button) view.findViewById(R.id.button2);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainActivityFragment();
                //get the view of the main activity
                View mainView = getActivity().findViewById(R.id.mainAct);
                //set the background color
                mainView.setBackgroundColor(Color.BLUE);
                //switch fragment
                ((MainActivity) getActivity()).transitionToFragment(fragment);
            }
        });

        Button myButton2 = (Button) view.findViewById(R.id.button3);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainActivityFragment();
                //get the view of the main activity
                View mainView = getActivity().findViewById(R.id.mainAct);
                //set the background color
                mainView.setBackgroundColor(Color.RED);
                //switch fragment
                ((MainActivity) getActivity()).transitionToFragment(fragment);
            }
        });

        Button myButton3 = (Button) view.findViewById(R.id.button4);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainActivityFragment();
                //get the view of the main activity
                View mainView = getActivity().findViewById(R.id.mainAct);
                //set the background color
                mainView.setBackgroundColor(Color.GREEN);
                //switch fragment
                ((MainActivity) getActivity()).transitionToFragment(fragment);
            }
        });


        return view;
    }

    // Again, unnecessary and unused
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
