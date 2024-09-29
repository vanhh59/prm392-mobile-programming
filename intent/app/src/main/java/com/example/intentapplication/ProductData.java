package com.example.intentapplication;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    public static List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Pharmacy", R.drawable.pharmacy));
        productList.add(new Product("Beauty", R.drawable.beauty));
        productList.add(new Product("Fast food", R.drawable.fast_food_meal));
        productList.add(new Product("Ginsheng", R.drawable.ginseng));
        productList.add(new Product("Headphone", R.drawable.headphones));
        productList.add(new Product("Onion", R.drawable.onion));
        productList.add(new Product("Patchouli", R.drawable.patchouli));
        productList.add(new Product("Tshirt", R.drawable.tshirt));
        productList.add(new Product("Chains", R.drawable.chains));
        productList.add(new Product("Baby", R.drawable.baby));
        productList.add(new Product("Home", R.drawable.home));
        productList.add(new Product("Accessories", R.drawable.accessories));
        productList.add(new Product("Shoes", R.drawable.shoes));
        productList.add(new Product("Registry", R.drawable.registry));

        return productList;

    }
}
