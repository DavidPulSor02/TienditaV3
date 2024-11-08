package com.example.tienditav3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MainPageFragment();
            case 1:
                return new AllProductsFragment();
            case 2:
                return new BestSellersFragment();
            default:
                return new MainPageFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Número de fragmentos (tabs)
    }
}
