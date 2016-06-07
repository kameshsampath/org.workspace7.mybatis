package org.workspace7.mybatis2.application;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonMapper {

	@Select("SELECT * FROM PERSON where id = #{id}")
	Person selectPerson(long id) throws SQLException;
}
