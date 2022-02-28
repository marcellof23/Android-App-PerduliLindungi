package com.example.perludilindungi.repository

import com.example.perludilindungi.database.fakses.FaskesDAO
import javax.inject.Inject

class MainRepo  @Inject constructor(
    val faskesDAO: FaskesDAO,
) {
}