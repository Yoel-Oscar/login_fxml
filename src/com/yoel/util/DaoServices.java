package com.yoel.util;

import java.sql.SQLException;
import java.util.List;

/**
 * author 1672063 Yoel Oscar
 */

public interface DaoServices<T> {

    List<T> fetchAll() throws ClassNotFoundException, SQLException;

    int addData(T t) throws ClassNotFoundException, SQLException;
    int deleteData(T t) throws ClassNotFoundException, SQLException;
    int updateData(T t) throws ClassNotFoundException, SQLException;
}
