package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import graham.boardgames.enunciate.MainActivity;
import graham.boardgames.enunciate.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link guessedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link guessedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class guessedFragment extends Fragment{

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

    public guessedFragment() {
        // Required empty public constructor
    }

    public static guessedFragment newInstance(String param1) {
        guessedFragment fragment = new guessedFragment();
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
        return inflater.inflate(R.layout.fragment_guessed_words, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void showGuessedWords() {
        String guessedWordsString = "<b>Scored</b><br>";
        int i = 1;
        for (String s : MainActivity.guessedWords) {
            guessedWordsString += String.valueOf(i) + ". "+  s + "<br>";
            i++;
        }
        if(MainActivity.guessedWords.size() == 0) {
            guessedWordsString += "None" + "<br>";
        }
        guessedWordsString += "<br><b>Missed</b><br>";
        i=1;
        for (String s : MainActivity.missedWords) {
            guessedWordsString += String.valueOf(i) + ". "+  s + "<br>";
            i++;
        }
        if (!"".equals(guessedWordsString)) {
            guessedWordsString = guessedWordsString.substring(0, guessedWordsString.length() - 1);
        }
        mCardView.setText(Html.fromHtml(guessedWordsString));
    }

    public void showWelcomeMessage() {
        String welcomeMessage = "";
        welcomeMessage = "Welcome to Enunciate!\n\nTake it in turns to describe people, places and things without saying the word itself.\n\nPick your category to start playing!";
        mCardView.setText(welcomeMessage);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCardView = (TextView) getView().findViewById(R.id.fragmentText);
        mActionArrLen = MainActivity.mActionArray.length;
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
