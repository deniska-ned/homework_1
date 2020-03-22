package ru.nedoluzhko.homework_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentList.OnItemSelectedListener {

    static int START_LIST_LEN = 100;
    FragmentList fragmentList;
    FragmentNum fragmentNum;

    private String isOpened;
    static String listIsOpened = "listIsOpened";
    static String itemIsOpened = "itemIsOpened";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int currListLen;
        int numOpenedItem;

        if (savedInstanceState == null){
            currListLen = START_LIST_LEN;
            numOpenedItem = -1;
        } else {
            currListLen = savedInstanceState.getInt("numCount");
            numOpenedItem = savedInstanceState.getInt("numOpenedItem");
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragmentList = FragmentList.newInstance(currListLen);
        ft.replace(R.id.placeholder, fragmentList);
        ft.commit();

        isOpened = listIsOpened; showMessage();

        if (numOpenedItem != -1){
            onItemClick(numOpenedItem);
            isOpened = itemIsOpened; showMessage();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("numCount", fragmentList.getListLen());
        if (isOpened.equals(itemIsOpened))
            outState.putInt("numOpenedItem", fragmentNum.getNum());
        else
            outState.putInt("numOpenedItem", -1);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isOpened = listIsOpened; showMessage();
    }

    @Override
    public void onItemClick(int numItem) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragmentNum = FragmentNum.newInstance(numItem);
        ft.replace(R.id.placeholder, fragmentNum);
        ft.addToBackStack(null);
        ft.commit();
        isOpened = itemIsOpened; showMessage();
    }

    private void showMessage(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        int len = fragmentManager.getBackStackEntryCount();
        Toast.makeText(getApplicationContext(), isOpened + " " + len, Toast.LENGTH_SHORT).show();
    }
}
