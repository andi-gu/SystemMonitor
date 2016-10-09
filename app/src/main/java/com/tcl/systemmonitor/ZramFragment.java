package com.tcl.systemmonitor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ZramFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ll", "onCreate-Zram ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ll", "onCreateView-Zram ");

        return inflater.inflate(R.layout.fragment_zram, container, false);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.style2,menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("ll", "Zram "+item.getItemId());
        switch (item.getItemId()) {
            case R.id.ui_menu_help:
                Toast.makeText(getActivity(), "点击了第一个按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ui_menu_setting:
                Toast.makeText(getActivity(), "点击了第二个按钮", Toast.LENGTH_SHORT).show();
                break;
			/*case R.id.action_item3:
				Toast.makeText(this, "点击了第三个按钮", Toast.LENGTH_SHORT).show();
				break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
