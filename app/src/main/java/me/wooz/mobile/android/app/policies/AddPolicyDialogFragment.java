package me.wooz.mobile.android.app.policies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.wooz.mobile.android.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAddPolicyListener} interface
 * to handle interaction events.
 * Use the {@link AddPolicyDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPolicyDialogFragment extends DialogFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddPolicyDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPolicyDialogFragment newInstance() {
        AddPolicyDialogFragment fragment = new AddPolicyDialogFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    private OnAddPolicyListener mListener;

    public AddPolicyDialogFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set up dialog
        getDialog().setCanceledOnTouchOutside(false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_policy_dialog, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPolicyAdded(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddPolicyListener) {
            mListener = (OnAddPolicyListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddPolicyListener");
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
    public interface OnAddPolicyListener {
        // TODO: Update argument type and name
        void onPolicyAdded(Uri uri);
    }
}
