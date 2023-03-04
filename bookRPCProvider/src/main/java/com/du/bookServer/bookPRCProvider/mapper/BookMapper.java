package com.du.bookServer.bookPRCProvider.mapper;

import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from bookRPC where id = #{id} and inventory > 0")
    BookDomain queryById(@Param("id") int id);

    @Select("select * from bookRPC where name like concat('%',#{name},'%') and inventory > 0")
    List<BookDomain> queryByName(@Param("name") String name);

    @Select("select * from bookRPC where author like concat('%',#{author},'%') and inventory > 0")
    List<BookDomain> queryByAuthor(@Param("author") String author);

    @Select("select * from bookRPC where inventory > 0")
    List<BookDomain> queryAll();

    @Insert({"insert into bookRPC(name, price, inventory) values(#{name}, #{price}, #{inventory})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(BookDomain book);

    @Delete("delete from bookRPC where id = #{id}")
    int deleteById(int id);


    @Update("update bookRPC set name = #{name}, price = #{price}, inventory = #{inventory}, author = #{author}, source = #{source} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updateById(BookDomain book);

}
