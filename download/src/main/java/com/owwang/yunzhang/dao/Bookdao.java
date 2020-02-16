package com.owwang.yunzhang.dao;

import com.owwang.yunzhang.pojo.Book;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname Bookdao
 * @Description TODO
 * @Date 2020-02-16
 * @Created by WANG
 */
public interface Bookdao extends JpaRepository<Book,Long> {
    Book findByBookurl(String bookUrl);
}
