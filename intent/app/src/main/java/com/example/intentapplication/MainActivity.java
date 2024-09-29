package com.example.intentapplication;
  // Note
  // chuyen sang MVP
// testing su dung lifecycle
// data >> adapter >> activity
 //1. adapter co vai tro:
// - nhan data tu activity
// - tao view
// - set data cho view
// - tra ve view cho activity
 //2. view hodler
// - chua cac view
// - tim view
 //3. activity
// - tao adapter
// - set adapter cho recycleview
// - set layout cho recycleview
// implement RecycleView
// - tao layout cho item
// - tao layout cho recycleview
// - tao adapter
// - set adapter cho recycleview
// - set layout cho recycleview
// - tao data
// - truyen data cho adapter
// - hien thi data

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_main);

        //khởi tạo toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //cấu hình RecyclerView
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //lấy danh sách sản phẩm từ productData
        productList = ProductData.getProductList();

        //khởi tạo ProductAdapter và gắn vào RecyclerView
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
