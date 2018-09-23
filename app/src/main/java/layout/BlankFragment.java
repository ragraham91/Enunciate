package layout;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import graham.boardgames.enunciate.MainActivity;
import graham.boardgames.enunciate.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mCardView = null;
    private ImageButton gotItButton = null;
    private int mCurrIdx = -1;
    private int mActionArrLen;

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void showActionAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mActionArray[mCurrIdx]);
    }

    public void showPersonAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mPersonArray[mCurrIdx]);
    }
    public void showObjectAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mObjectArray[mCurrIdx]);
    }
    public void showWorldAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mWorldArray[mCurrIdx]);
    }
    public void showRandomAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mRandomArray[mCurrIdx]);
    }
    public void showNatureAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mActionArrLen)
            return;
        mCurrIdx = newIndex;
        mCardView.setText(MainActivity.mNatureArray[mCurrIdx]);
    }
    public void showGuessedWords() {
        String guessedWordsString = "";
        for (String s : MainActivity.guessedWords) {
            guessedWordsString += s + ", ";
        }
        guessedWordsString = guessedWordsString.substring(0, guessedWordsString.length()-1);
        mCardView.setText(guessedWordsString);
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
    public String getText() {
        return mCardView.getText().toString();
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCardView = (TextView) getView().findViewById(R.id.fragmentText);
        gotItButton = (ImageButton) getView().findViewById(R.id.gotItButton);
        mActionArrLen = MainActivity.mActionArray.length;
        gotItButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity) BlankFragment.this.getActivity()).incrementRoundScore();
                ((MainActivity) BlankFragment.this.getActivity()).guessedWords.add(mCardView.getText().toString());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("action"))
                    BlankFragment.this.showActionAtIndex(((MainActivity) BlankFragment.this.getActivity()).getActionIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("person"))
                    BlankFragment.this.showPersonAtIndex(((MainActivity) BlankFragment.this.getActivity()).getPersonIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("object"))
                    BlankFragment.this.showObjectAtIndex(((MainActivity) BlankFragment.this.getActivity()).getObjectIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("world"))
                    BlankFragment.this.showWorldAtIndex(((MainActivity) BlankFragment.this.getActivity()).getWorldIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("random"))
                    BlankFragment.this.showRandomAtIndex(((MainActivity) BlankFragment.this.getActivity()).getRandomIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("nature"))
                    BlankFragment.this.showNatureAtIndex(((MainActivity) BlankFragment.this.getActivity()).getNatureIndex());
                if (((MainActivity) BlankFragment.this.getActivity()).getCurrentCategory().equals("allplay")) {
                    Random r1 = new Random();
                    int number1 = r1.nextInt(6) + 1;
                    switch (number1) {
                        case 1:
                            BlankFragment.this.showPersonAtIndex(((MainActivity) BlankFragment.this.getActivity()).getPersonIndex());
                            break;
                        case 2:
                            BlankFragment.this.showWorldAtIndex(((MainActivity) BlankFragment.this.getActivity()).getWorldIndex());
                            break;
                        case 3:
                            BlankFragment.this.showObjectAtIndex(((MainActivity) BlankFragment.this.getActivity()).getObjectIndex());
                            break;
                        case 4:
                            BlankFragment.this.showActionAtIndex(((MainActivity) BlankFragment.this.getActivity()).getActionIndex());
                            break;
                        case 5:
                            BlankFragment.this.showNatureAtIndex(((MainActivity) BlankFragment.this.getActivity()).getNatureIndex());
                            break;
                        case 6:
                            BlankFragment.this.showRandomAtIndex(((MainActivity) BlankFragment.this.getActivity()).getRandomIndex());
                            break;
                        default:
                            BlankFragment.this.showRandomAtIndex(((MainActivity) BlankFragment.this.getActivity()).getRandomIndex());
                            break;
                    }
                }
            }
        });
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
