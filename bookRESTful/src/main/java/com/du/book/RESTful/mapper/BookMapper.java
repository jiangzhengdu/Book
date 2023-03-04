package com.du.book.RESTful.mapper;


import com.du.book.RESTful.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from bookRESTful where id = #{id} and inventory > 0")
    Book queryById(@Param("id") int id);

    @Select("select * from bookRESTful where name like concat('%',#{name},'%') and inventory > 0")
    List<Book> queryByName(@Param("name") String name);

    @Select("select * from bookRESTful where author like concat('%',#{author},'%') and inventory > 0")
    List<Book> queryByAuthor(@Param("author") String author);

    @Select("select * from bookRESTful where inventory > 0")
    List<Book> queryAll();

    @Insert({"insert into bookRESTful(name, price, author, inventory, source) values(#{name}, #{price}, #{author}, #{inventory}, #{source})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(Book book);

    @Delete("delete from bookRESTful where id = #{id}")
    int deleteById(int id);

//    @Update("update bookRESTful set name = #{name}, price = #{price}, inventory = #{inventory} where id = #{id}")
    @Update("update bookRESTful set name = #{name}, price = #{price}, inventory = #{inventory}, author = #{author}, source = #{source} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updateById(Book book);

}
