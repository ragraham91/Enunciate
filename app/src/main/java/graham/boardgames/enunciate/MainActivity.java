package graham.boardgames.enunciate;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import layout.BlankFragment;
import layout.guessedFragment;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, guessedFragment.OnFragmentInteractionListener  {
    public static CountDownTimer aCounter;
    public static boolean inForeground;
    public static String[] mActionArray;
    public static String[] mPersonArray;
    public static String[] mObjectArray;
    public static String[] mWorldArray;
    public static String[] mRandomArray;
    public static String[] mNatureArray;
    public static List<String> guessedWords = new ArrayList<String>();
    public static List<String> missedWords = new ArrayList<String>();
    public final guessedFragment mGuessedWordsFragment = new guessedFragment();
    public final BlankFragment mTopCardFragment = new BlankFragment();
    public final BlankFragment mMiddleCardFragment = new BlankFragment();
    public final BlankFragment mBottomCardFragment = new BlankFragment();

    private FragmentManager mFragmentManager;
    private FragmentManager mFragmentManagerInitial;

    private LinearLayout guessedWordsContainer;
    private FrameLayout mTopCardFrameLayout, mMiddleCardFrameLayout, mBottomCardFrameLayout;
    public String currentCategory;
    public String getCurrentCategory(){
        return currentCategory;
    }
    public int roundScore = 0;
    public void incrementRoundScore() {
        roundScore++;
    }
    public int actionIndex = 0;
    public int getActionIndex(){
        return actionIndex++;
    }
    public int personIndex = 0;
    public int getPersonIndex(){
        return personIndex++;
    }
    public int objectIndex = 0;
    public int getObjectIndex(){
        return objectIndex++;
    }
    public int worldIndex = 0;
    public int getWorldIndex(){
        return worldIndex++;
    }
    public int randomIndex = 0;
    public int getRandomIndex(){
        return randomIndex++;
    }
    public int natureIndex = 0;
    public int getNatureIndex(){
        return natureIndex++;
    }
    public void onFragmentInteraction(Uri uri){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        guessedWordsContainer = (LinearLayout) findViewById(R.id.wholeCardArea);
        mTopCardFrameLayout = (FrameLayout) findViewById(R.id.topCardFrameLayout);
        mMiddleCardFrameLayout = (FrameLayout) findViewById(R.id.middleCardFrameLayout);
        mBottomCardFrameLayout = (FrameLayout) findViewById(R.id.bottomCardFrameLayout);

        final Button personButton = (Button) findViewById(R.id.personButton);
        final Button worldButton = (Button) findViewById(R.id.worldButton);
        final Button objectButton = (Button) findViewById(R.id.objectButton);
        final Button actionButton = (Button) findViewById(R.id.actionButton);
        final Button natureButton = (Button) findViewById(R.id.natureButton);
        final Button randomButton = (Button) findViewById(R.id.randomButton);
        final Button allPlayButton = (Button) findViewById(R.id.allPlayButton);

        mActionArray = getResources().getStringArray(R.array.Actions);
        mPersonArray = getResources().getStringArray(R.array.Persons);
        mObjectArray = getResources().getStringArray(R.array.Objects);
        mWorldArray = getResources().getStringArray(R.array.Worlds);
        mRandomArray = getResources().getStringArray(R.array.Randoms);
        mNatureArray = getResources().getStringArray(R.array.Natures);

        Collections.shuffle(Arrays.asList(mActionArray));
        Collections.shuffle(Arrays.asList(mPersonArray));
        Collections.shuffle(Arrays.asList(mObjectArray));
        Collections.shuffle(Arrays.asList(mWorldArray));
        Collections.shuffle(Arrays.asList(mRandomArray));
        Collections.shuffle(Arrays.asList(mNatureArray));

        mFragmentManagerInitial = getSupportFragmentManager();

        FragmentTransaction fragmentTransactionInitial = mFragmentManagerInitial.beginTransaction();
        fragmentTransactionInitial.add(R.id.wholeCardArea, mGuessedWordsFragment);
        fragmentTransactionInitial.add(R.id.topCardFrameLayout, mTopCardFragment);
        fragmentTransactionInitial.add(R.id.middleCardFrameLayout, mMiddleCardFragment);
        fragmentTransactionInitial.add(R.id.bottomCardFrameLayout, mBottomCardFragment);
        fragmentTransactionInitial.hide(mTopCardFragment);
        fragmentTransactionInitial.hide(mMiddleCardFragment);
        fragmentTransactionInitial.hide(mBottomCardFragment);
        fragmentTransactionInitial.show(mGuessedWordsFragment);

        fragmentTransactionInitial.commit();

        mFragmentManagerInitial.executePendingTransactions();



        actionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                actionButton.setBackgroundResource(R.drawable.action_button_pressed);

                mFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.hide(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "action";
                mTopCardFragment.showActionAtIndex(actionIndex++);
                mMiddleCardFragment.showActionAtIndex(actionIndex++);

            }


        });
        personButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                personButton.setBackgroundResource(R.drawable.person_button_pressed);

                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.show(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "person";
                mTopCardFragment.showPersonAtIndex(personIndex++);
                mMiddleCardFragment.showPersonAtIndex(personIndex++);
                mBottomCardFragment.showPersonAtIndex(personIndex++);

            }


        });
        objectButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                objectButton.setBackgroundResource(R.drawable.object_button_pressed);
                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.hide(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "object";
                mTopCardFragment.showObjectAtIndex(objectIndex++);
                mMiddleCardFragment.showObjectAtIndex(objectIndex++);

            }

        });
        worldButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                worldButton.setBackgroundResource(R.drawable.world_button_pressed);

                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.show(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "world";
                mTopCardFragment.showWorldAtIndex(worldIndex++);
                mMiddleCardFragment.showWorldAtIndex(worldIndex++);
                mBottomCardFragment.showWorldAtIndex(worldIndex++);
            }


        });
        randomButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                randomButton.setBackgroundResource(R.drawable.random_button_pressed);

                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.hide(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "random";
                mTopCardFragment.showRandomAtIndex(randomIndex++);
                mMiddleCardFragment.showRandomAtIndex(randomIndex++);

            }


        });
        natureButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                natureButton.setBackgroundResource(R.drawable.nature_button_pressed);

                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.hide(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "nature";
                mTopCardFragment.showNatureAtIndex(natureIndex++);
                mMiddleCardFragment.showNatureAtIndex(natureIndex++);

            }

        });
        allPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aCounter != null) {
                    aCounter.cancel();
                }
                invalidateOptionsMenu();

                roundScore = 0;
                guessedWords.clear();
                missedWords.clear();

                personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                allPlayButton.setBackgroundResource(R.drawable.allplay_button_pressed);
                //allPlayButton.getBackground().setColorFilter(0xFFe0e0e0, PorterDuff.Mode.MULTIPLY);

                mFragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                fragmentTransaction.show(mTopCardFragment);
                fragmentTransaction.show(mMiddleCardFragment);
                fragmentTransaction.hide(mBottomCardFragment);
                fragmentTransaction.hide(mGuessedWordsFragment);

                fragmentTransaction.commit();
                currentCategory = "allplay";

                Random r1 = new Random();
                int number1 = r1.nextInt(6) + 1;
                switch (number1) {
                    case 1: mTopCardFragment.showPersonAtIndex(personIndex++);
                        break;
                    case 2: mTopCardFragment.showWorldAtIndex(worldIndex++);
                        break;
                    case 3: mTopCardFragment.showObjectAtIndex(objectIndex++);
                        break;
                    case 4: mTopCardFragment.showActionAtIndex(actionIndex++);
                        break;
                    case 5: mTopCardFragment.showNatureAtIndex(natureIndex++);
                        break;
                    case 6: mTopCardFragment.showRandomAtIndex(randomIndex++);
                        break;
                    default: mTopCardFragment.showRandomAtIndex(randomIndex++);
                        break;
                }
                Random r2 = new Random();
                int number2 = r2.nextInt(6) + 1;
                switch (number2) {
                    case 1: mMiddleCardFragment.showPersonAtIndex(personIndex++);
                        break;
                    case 2: mMiddleCardFragment.showWorldAtIndex(worldIndex++);
                        break;
                    case 3: mMiddleCardFragment.showObjectAtIndex(objectIndex++);
                        break;
                    case 4: mMiddleCardFragment.showActionAtIndex(actionIndex++);
                        break;
                    case 5: mMiddleCardFragment.showNatureAtIndex(natureIndex++);
                        break;
                    case 6: mMiddleCardFragment.showRandomAtIndex(randomIndex++);
                        break;
                    default: mTopCardFragment.showRandomAtIndex(randomIndex++);
                        break;
                }

            }


        });
    }

    long timer;
    boolean isFirstOptionsMenuCreation = true;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        mGuessedWordsFragment.showWelcomeMessage();

        if (!isFirstOptionsMenuCreation) {

            timer = 30000;

            final MenuItem counter = menu.findItem(R.id.counter);
            aCounter = new CountDownTimer(timer, 1000) {

                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String hms = ("" + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));

                    counter.setTitle(hms);
                    timer = millis;

                }

                public void onFinish() {
                    if (roundScore == 1) {
                        counter.setTitle(roundScore + " point");
                    }
                    else {
                        counter.setTitle(roundScore + " points");
                    }
                    if (mTopCardFragment.isVisible()){
                        missedWords.add(mTopCardFragment.getText());
                    }
                    if (mMiddleCardFragment.isVisible()){
                        missedWords.add(mMiddleCardFragment.getText());
                    }
                    if (mBottomCardFragment.isVisible()){
                        missedWords.add(mBottomCardFragment.getText());
                    }

                    mGuessedWordsFragment.showGuessedWords();
                    mFragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.show(mGuessedWordsFragment);
                    fragmentTransaction.hide(mTopCardFragment);
                    fragmentTransaction.hide(mMiddleCardFragment);
                    fragmentTransaction.hide(mBottomCardFragment);
                    fragmentTransaction.commitAllowingStateLoss();

                    mFragmentManager.executePendingTransactions();

                    final Button personButton = (Button) findViewById(R.id.personButton);
                    final Button worldButton = (Button) findViewById(R.id.worldButton);
                    final Button objectButton = (Button) findViewById(R.id.objectButton);
                    final Button actionButton = (Button) findViewById(R.id.actionButton);
                    final Button natureButton = (Button) findViewById(R.id.natureButton);
                    final Button randomButton = (Button) findViewById(R.id.randomButton);
                    final Button allPlayButton = (Button) findViewById(R.id.allPlayButton);

                    personButton.setBackgroundResource(R.drawable.person_button_unpressed);
                    worldButton.setBackgroundResource(R.drawable.world_button_unpressed);
                    objectButton.setBackgroundResource(R.drawable.object_button_unpressed);
                    actionButton.setBackgroundResource(R.drawable.action_button_unpressed);
                    natureButton.setBackgroundResource(R.drawable.nature_button_unpressed);
                    randomButton.setBackgroundResource(R.drawable.random_button_unpressed);
                    allPlayButton.setBackgroundResource(R.drawable.allplay_button_unpressed);

                    if (inForeground){
                            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 600);
                    }

                }
            };
            aCounter.start();
        }

        isFirstOptionsMenuCreation = false;

        return  true;

    }
    public void onStop() {
        super.onStop();
        inForeground = false;
    }

    public void onResume(){
        super.onResume();
        inForeground = true;
    }


}



