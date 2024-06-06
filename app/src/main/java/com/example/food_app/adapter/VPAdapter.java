package com.example.food_app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.food_app.fragmentsMenu.EntradasFragment;
import com.example.food_app.fragmentsMenu.PrincipalFragment;
import com.example.food_app.fragmentsMenu.PostresFragment;
import com.example.food_app.fragmentsMenu.BebidaFragment;


public class VPAdapter extends FragmentStateAdapter {


    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 : return new EntradasFragment();
            case 1 : return new PrincipalFragment();
            case 2 : return new PostresFragment();
            case 3 : return new BebidaFragment();
            default: return new EntradasFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
