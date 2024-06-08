package com.example.food_app.adapter;

import com.example.food_app.database.entity.comidaBebida;

public interface OnItemQuantityChangedListener {
    void onQuantityChanged(comidaBebida comida);

    void onQuantityChanged(comidaBebida comida, int newQuantity);
}
