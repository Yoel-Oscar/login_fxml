package com.yoel.util;

import com.yoel.entity.User;

import java.sql.SQLException;

/**
 * author 1672063 Yoel Oscar
 */

public interface LoginServices<T> {

    T login(String email, String password) throws ClassNotFoundException, SQLException;
}
