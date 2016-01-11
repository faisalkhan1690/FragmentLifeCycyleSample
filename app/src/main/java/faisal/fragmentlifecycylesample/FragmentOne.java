package faisal.fragmentlifecycylesample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentOne extends Fragment {
    private static final String DATA = "data";

    private String mData;

    private OnFragmentOneInteractionListener mListener;

    public FragmentOne() {
    }

    public static FragmentOne newInstance(String data) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = getArguments().getString(DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        TextView textview=(TextView)view.findViewById(R.id.textview);
        if(mData!=null){
            textview.setText(mData);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOneInteractionListener) {
            mListener = (OnFragmentOneInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mListener.onSendDataFromFragmentOne("hello two");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentOneInteractionListener {
        void onSendDataFromFragmentOne(String data);
    }
}
