package ru.nedoluzhko.homework_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentList.OnItemSelectedListener {

    static int START_LIST_LEN = 100;

    private boolean isFirstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            isFirstStart = savedInstanceState.getBoolean("isFirstStart");
        }

        if (isFirstStart == true){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentList fragmentList = FragmentList.newInstance(START_LIST_LEN);
            ft.replace(R.id.placeholder, fragmentList);
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFirstStart = false;
        outState.putBoolean("isFirstStart", isFirstStart);
    }


    @Override
    public void onItemClick(int numItem) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentNum fragmentNum = FragmentNum.newInstance(numItem);
        ft.replace(R.id.placeholder, fragmentNum);
        ft.addToBackStack(null);
        ft.commit();
    }
}
