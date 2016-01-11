package faisal.fragmentlifecycylesample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This class will show you which method will invoked and in which sequence when fragment changes his state.
 * You can check in Debug----Faisal Khan
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener ,FragmentTwo.OnFragmentTwoInteractionListener,FragmentOne.OnFragmentOneInteractionListener {

    private String TAG=MainActivity.class.getSimpleName();
    private Button btnFragmentOne;
    private Button btnFragmentTwo;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String dataFromFragmentOne;
    private String dataFromFragmentTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        btnFragmentOne=(Button)findViewById(R.id.btn_fragment_one);
        btnFragmentTwo=(Button)findViewById(R.id.btn_fragment_two);

        btnFragmentOne.setOnClickListener(this);
        btnFragmentTwo.setOnClickListener(this);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new FragmentOne());
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_fragment_one:

                fragmentTransaction=fragmentManager.beginTransaction();
                if(dataFromFragmentTwo!=null){
                    fragmentTransaction.replace(R.id.container, FragmentOne.newInstance(dataFromFragmentTwo));
                }else{
                    fragmentTransaction.replace(R.id.container, new FragmentOne());
                }
                fragmentTransaction.commit();
                break;

            case R.id.btn_fragment_two:
               fragmentTransaction=fragmentManager.beginTransaction();
                if(dataFromFragmentOne!=null){
                    fragmentTransaction.replace(R.id.container, FragmentTwo.newInstance(dataFromFragmentOne));
                }else{
                    fragmentTransaction.replace(R.id.container, new FragmentTwo());
                }
                fragmentTransaction.commit();
                break;

        }
    }

    @Override
    public void onSendDataFromFragmentTwo(String data) {
        dataFromFragmentOne=data;

    }

    @Override
    public void onSendDataFromFragmentOne(String data) {
        dataFromFragmentTwo=data;
    }
}
